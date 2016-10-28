import java.util.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.config.ServerConfig;
import com.fasterxml.jackson.databind.JsonNode;
import org.avaje.datasource.DataSourceConfig;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;
import services.HelperServices;
import utils.DbUtils;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;
import static play.test.Helpers.*;
import static org.junit.Assert.*;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
public class ApplicationTest {
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
