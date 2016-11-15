import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.config.ServerConfig;
import com.fasterxml.jackson.databind.JsonNode;
import model.*;
import model.plan.PlanShipment;
import model.plan.PlanShipmentItem;
import org.avaje.datasource.DataSourceConfig;
import org.junit.*;

import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;
import services.DstlService;
import services.HelperServices;
import services.PlanDayService;
import utils.DbUtils;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;
import static play.test.Helpers.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */

public class ApplicationTest {

    private Application app;

    public void startApp() throws Exception {
        Map<String, String> settings = new HashMap<String, String>();
        settings.put("db.default.driver", "org.h2.DriverTransportCompany");
        settings.put("db.default.user", "sa");
        settings.put("db.default.password", "");
        settings.put("db.default.url", "jdbc:h2:mem:play-test-351881363;MODE=MySQL"); // TODO: use config for url
        settings.put("db.default.jndiName", "DefaultDS");
        app = new GuiceApplicationBuilder().build();
        Helpers.start(app);
/*
        databaseTester = new JndiDatabaseTester("DefaultDS");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(play.Play.application()
                .resourceAsStream("/resources/dataset.xml"));
        databaseTester.setDataSet(expectedDataSet);
        databaseTester.onSetup();*/
    }



    @After
    public void stopApp() throws Exception {
         //Helpers.stop(app);
    }

    public EbeanServer getObject() throws Exception {

        ServerConfig config = new ServerConfig();
        config.setName("slgotek");
        config.loadFromProperties();
        // load test-ebean.properties if present for running tests
        // typically using H2 in memory database
        config.loadTestProperties();

        // set as default and register so that Model can be
        // used if desired for save() and update() etc
        config.setDefaultServer(false);
        config.setRegister(true);
        DataSourceConfig ds = new DataSourceConfig();
        ds.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://SRV-SLDB:1433;databaseName=SL_GOTEK");
        ds.setUsername("report");
        ds.setPassword("report");
        config.setDataSourceConfig(ds);


        return EbeanServerFactory.create(config);
    }
    public EbeanServer getObjectLocalWork() {

        ServerConfig config = new ServerConfig();
        config.setName("default");
        config.loadFromProperties();
        // load test-ebean.properties if present for running tests
        // typically using H2 in memory database
        config.loadTestProperties();

        // set as default and register so that Model can be
        // used if desired for save() and update() etc
        config.setDefaultServer(true);
        config.setRegister(true);
        config.addPackage("model");
        /*config.addClass(WorkTime.class);
        config.addClass(Enterprise.class);*/

        DataSourceConfig ds = new DataSourceConfig();
        ds.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://SRV-SLDB:1433;databaseName=GTK_DSTL");
        ds.setUsername("report");
        ds.setPassword("report");
        config.setDataSourceConfig(ds);



        return EbeanServerFactory.create(config);
    }

    public EbeanServer getObjectLocalHome() {

        ServerConfig config = new ServerConfig();
        config.setName("default");
        config.loadFromProperties();
        // load test-ebean.properties if present for running tests
        // typically using H2 in memory database
        config.loadTestProperties();

        // set as default and register so that Model can be
        // used if desired for save() and update() etc
        config.setDefaultServer(true);
        config.setRegister(true);
        config.addPackage("model");
        /*config.addClass(WorkTime.class);
        config.addClass(Enterprise.class);*/

        DataSourceConfig ds = new DataSourceConfig();
        ds.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://localhost\\MSSQL2014_DEV;databaseName=GTK_DSTL");
        ds.setUsername("sa");
        ds.setPassword("415631234");
        config.setDataSourceConfig(ds);



        return EbeanServerFactory.create(config);
    }
    public EbeanServer getObjectLocal() {
        if(System.getProperty("user.name").toLowerCase().equals("gukov")){
            return  getObjectLocalWork();
        }else{
            return  getObjectLocalHome();
        }

    }

    @Test
    public void createDeviationDelivery(){
        getObjectLocal();
        DeviationDelivery deviationDelivery = new DeviationDelivery();
        deviationDelivery.setDescription("TEst");
        Ebean.save(deviationDelivery);
        assertNotEquals(-1,deviationDelivery.getId());

    }
    @Test
    public void getListTrasportCompony(){
        getObjectLocal();
        TransportCompany transportCompany =  TransportCompany.find.byId("A7ED6ABB-F607-438D-84BE-56BDDA07A192");

        assertEquals("П000032",transportCompany.getCode());

    }
    @Test
    public void getPlayItem(){
        getObjectLocal();
        PlanShipmentItem planShipmentItem =  PlanShipmentItem.find.byId(93L);

        assertEquals("П000032",planShipmentItem.getTransportCompanyPlan().getCode());

    }




    @Test
    public void createDeviationShipment(){
        getObjectLocal();
        DeviationShipment deviationDelivery = new DeviationShipment();
        deviationDelivery.setDescription("Тест отгрузкм");
        Ebean.save(deviationDelivery);
        assertNotEquals(-1,deviationDelivery.getId());
        //Ebean.delete(deviationDelivery);
    }


    @Test
    public void mustCreatePlanShipment(){
        getObjectLocal();
        PlanDayService planDayService = new PlanDayService();
        DstlService dstlService = new DstlService();
        PlanShipment planShipment =  planDayService.createPlan(java.sql.Date.valueOf(LocalDate.now().plusDays(1)),"ЗАО ГОТЭК-ЦПУ");
        assertNotNull(planShipment);
        PlanShipmentItem planShipmentItem = new PlanShipmentItem();
        planShipmentItem.setCodeCustomer("Test");
        planShipmentItem.setSenderEnterprise(dstlService.getEnterprise("ГОТЭК"));
        planShipment.getPlanShipmentItems().add(planShipmentItem);
        Ebean.save(planShipment);
        assertNotEquals(0,planShipment.getPlanShipmentItems().size());
        assertEquals(planShipment.getId(),planShipment.getPlanShipmentItems().get(0).getPlanShipment().getId());
    }

    @Test
    public void getListPlanitemFromPlan(){
        getObjectLocal();
        PlanDayService planDayService = new PlanDayService();
        LocalDate.of(2016,11,8);
        List<PlanShipmentItem> planShipmentItems =  planDayService.selectItemPlan(Date.valueOf(LocalDate.of(2016,11,8)),"");
        assertNotNull(planShipmentItems);
        assertNotNull(Json.toJson(planShipmentItems));
    }


    @Test
    public void listTimeWorkHaveOneRecord () throws Exception {
        getObjectLocal();

        Enterprise enterprise = DbUtils.enterpriseFromUser("ЗАО ГОТЭК-ЦПУ");

        assertNotNull(enterprise);
        HelperServices helperServices = new HelperServices();
        assertEquals(1,helperServices.workTimeList("ЗАО ГОТЭК-ЦПУ").size());

    }


    @Test
    public void nameMustEquelEnterprise() throws Exception {
        getObjectLocal();
        assertEquals("SL_SPB",DbUtils.enterpriseFromUser("ЗАО ГОТЭК-СЕВЕРО-ЗАПАД").getNameInDb());
        assertEquals("ЗАО ГОТЭК-ЦПУ",Optional.ofNullable(DbUtils.enterpriseFromUser("ЗАО ГОТЭК-ЦПУ").getBelongToService()));
    }

    @Test
    public void selectDriverWithTransportCompony(){
        getObjectLocal();
        HelperServices helperServices = new HelperServices();
        assertNotEquals(0,helperServices.driverTransportCompanyList().size());
        assertEquals("П004641",helperServices.driverTransportCompanyList().stream().filter(e -> e.getCodeCompany().equals("П004641")).findFirst().get().getCodeCompany());


    }


    @Test
    public void serviceDstl() {
        HelperServices helperServices = new HelperServices();
        assertEquals(1, helperServices.serviceDstl().get(1).getId());
    }


    @Test
    public void deviation() throws Exception {

        HelperServices helperServices = new HelperServices();
        List sqlRowList =  helperServices.listDeviation();
        assertNotEquals(0,sqlRowList.size());
    }


}
