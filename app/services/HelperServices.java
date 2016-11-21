package services;

import com.avaje.ebean.Ebean;

import com.avaje.ebean.Query;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Singleton;

import model.*;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import utils.DbUtils;

import javax.inject.Inject;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.avaje.ebean.Ebean.find;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Created by Zhukov on 22.10.2016.
 */
@Singleton
public class HelperServices {
    @Inject
    private DstlService dstlService;

    private static java.time.LocalDateTime timeUpdateDeviation = java.time.LocalDateTime.now();

    private final List<Enterprise> enterpriseList = Arrays.asList(new Enterprise(0, "ЗАО ГОТЭК-ЦПУ", "SL_CPU", true),
            new Enterprise(0, "ЗАО ГОТЭК-СЕВЕРО-ЗАПАД", "SL_SPB", true),
            new Enterprise(0, "ГОТЭК", "SL_GOTEK", false),
            new Enterprise(0, "ПРИНТ", "SL_PRINT", false),
            new Enterprise(0, "ПОЛИПАК", "SL_POLYPACK", false),
            new Enterprise(0, "ЛИТАР", "SL_LITAR", false),
            new Enterprise(0, "ЦЕНТР", "SL_CENTER", false)

    );


    public List<Enterprise> listEnterprise(Optional<Enterprise> serviceDstl) {
        Query<Enterprise> enterpriseQuery = find(Enterprise.class);

        if (enterpriseQuery.findList().size() == 0) {
            Ebean.saveAll(enterpriseList);
        }

        return serviceDstl.map(e ->enterpriseQuery.where().eq("BelongToService",e.getId()).findList()).orElse(enterpriseQuery.findList());

    }

    public List<Enterprise> serviceDstl() {
        return enterpriseList.stream().filter(e -> e.isService()).collect(toList());
    }


    public List<DeviationShipment> listDeviationShipment() {
        return null;
    }

    public List<Deviation> listDeviationDelivery() {
        return null;
    }


    public List<NormaTimeLoading> normaTimeLoadingList() {
        return find(NormaTimeLoading.class).findList();
    }

    public NormaTimeLoading saveNormaTimeLoading(JsonNode value) {
        Enterprise _enterprise = Ebean.find(Enterprise.class, value.findValue("enterprise").findValue("id").asInt());
        NormaTimeLoading normaTimeLoading = new NormaTimeLoading(value.findValue("id").asInt(),
                _enterprise,
                value.findValue("packageTime").asInt(),
                value.findValue("commissionTime").asInt(),
                value.findValue("placerTime").asInt()
        );


        Ebean.save(normaTimeLoading);
        return normaTimeLoading;
    }

    public NormaTimeLoading updateNormaTimeLoading(JsonNode value) {
        NormaTimeLoading updateNormaTimeLoading = Ebean.find(NormaTimeLoading.class, value.findValue("id"));
        JsonNode updateEnterprise = value.findValue("enterprise");
        Enterprise enterprise = Ebean.find(Enterprise.class, updateEnterprise.findValue("id").asInt());
        updateNormaTimeLoading.setEnterprise(enterprise);
        updateNormaTimeLoading.setPackageTime(value.findValue("packageTime").asInt());
        updateNormaTimeLoading.setCommissionTime(value.findValue("commissionTime").asInt());
        updateNormaTimeLoading.setPlacerTime(value.findValue("placerTime").asInt());
        Ebean.update(updateNormaTimeLoading);


        return updateNormaTimeLoading;
    }

    public Integer deleteNormaTimeLoading(Integer value) {
        return Ebean.delete(NormaTimeLoading.class, value);
    }

    public List<Deviation> listDeviation() {
        String _query = "SELECT TypeName,Value,Description,RowPointer FROM UserDefinedTypeValues\n" +
                "  WHERE TypeName IN ( 'UDTOtmena','UDTOtmenaDost')";
        List<SqlRow> sqlRowList =  DbUtils.connectToSLGotek().map(e -> e.createSqlQuery(_query).findList()).orElse(null);

        return sqlRowList.stream().map(this::mapSqlRowToDeviation).collect(toList());
    }

    public List<WorkTime> workTimeList(String enterpriseName){
           Enterprise serviceDstl  = DbUtils.enterpriseFromUser(enterpriseName);
          List<WorkTime> _list  = Ebean.createQuery(WorkTime.class).fetch("serviceDstl").where().eq("serviceDstl.id",serviceDstl.getId()).findList();
          return  _list;
    }




    private Deviation mapSqlRowToDeviation(SqlRow row) {
        Deviation deviation = new Deviation(0, row.getString("Value"), "",row.getUUID("RowPointer"));
        if(row.getString("TypeName").equals("UDTOtmena")){
            deviation.setType("Отгрузка");
        }else {
            deviation.setType("Доставка");
        }
        return deviation;
    }

    public WorkTime  saveWorkTime(JsonNode jsonNode,String nameServiceDstl) {
        Enterprise _enterprise = DbUtils.enterpriseFromUser(nameServiceDstl);
        WorkTime saveWorkTime = new WorkTime();
         saveWorkTime.setServiceDstl(_enterprise);
       // LocalDateTime localStartDateTime =  LocalDateTime.parse(jsonNode.findValue("startTime").asText());
        DateTime dateTime =   DateTime.parse(jsonNode.findValue("startTime").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
        LocalTime localTime = LocalTime.of(dateTime.getHourOfDay(),dateTime.getMinuteOfHour());
        saveWorkTime.setStartTime(Time.valueOf(localTime));
        //LocalDateTime localEndDateTime =  LocalDateTime.parse(jsonNode.findValue("endTime").asText());
        dateTime =   DateTime.parse(jsonNode.findValue("endTime").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
        localTime = LocalTime.of(dateTime.getHourOfDay(),dateTime.getMinuteOfHour());
        saveWorkTime.setEndTime(Time.valueOf(localTime));
        saveWorkTime.setName(jsonNode.findValue("name").asText());
        saveWorkTime.setWorkTime(jsonNode.findValue("workTime").asBoolean());
        Ebean.save(saveWorkTime);
        return saveWorkTime;
    }

    public WorkTime updateWorkTime(JsonNode jsonNode, String nameServiceDstl) {
        Enterprise serviceDstl  = DbUtils.enterpriseFromUser(nameServiceDstl);
        WorkTime updateWorkTime = Ebean.createQuery(WorkTime.class).fetch("serviceDstl")
                                                                   .where().eq("serviceDstl.id",serviceDstl.getId())
                                                                   .eq("id",jsonNode.findValue("id").intValue()).findUnique();
        updateWorkTime.setServiceDstl(serviceDstl);
        updateWorkTime.setName(jsonNode.findValue("name").asText());
        DateTime dateTime =   DateTime.parse(jsonNode.findValue("startTime").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
        LocalTime localTime = LocalTime.of(dateTime.getHourOfDay(),dateTime.getMinuteOfHour());
        updateWorkTime.setStartTime(Time.valueOf(localTime));
        //LocalDateTime localEndDateTime =  LocalDateTime.parse(jsonNode.findValue("endTime").asText());
        dateTime =   DateTime.parse(jsonNode.findValue("endTime").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
        localTime = LocalTime.of(dateTime.getHourOfDay(),dateTime.getMinuteOfHour());
        updateWorkTime.setEndTime(Time.valueOf(localTime));
        updateWorkTime.setWorkTime(jsonNode.findValue("workTime").asBoolean());


        Ebean.update(updateWorkTime);
        return  updateWorkTime;
    }

    public Integer deleteWorkTime(Integer id, String nameServiceDstl) {
         Enterprise serviceDstl  = DbUtils.enterpriseFromUser(nameServiceDstl);
         return Ebean.createQuery(WorkTime.class).fetch("serviceDstl").where().eq("serviceDstl.id",serviceDstl.getId()).eq("id",id).delete();
    }

    /* Транспортные компании и водители*/

    public List<TransportCompanyWithDriver> driverTransportCompanyList(){

        //String sqlsp = "exec dbo.gtk_rpt_logist_www :v_startdate, :v_enddate, :v_site,:v_type_rep";
        /*List<DriverTransportCompany> result =   Ebean.createSqlQuery(sqlsp).setParameter("v_startdate",dateBegin)
                .setParameter("v_enddate",dateEnd)
                .setParameter("v_site",site)
                .setParameter("v_type_rep",typeReport)
                .findList().stream().map(this::mapSqlRowToReportPrecisionOrder).collect(toList());*/

        return   Ebean.createSqlQuery("select * from GTK_ALL_CAR").findList()
                .stream()
                .map(this::mapSqlRowToTransportCompanyWithDriver)
                .collect(toList());
    }

    public List<DriverTransportCompany> driverTransportCompany(String refCompany){

        return  DriverTransportCompany.find.where().eq("transportCompany",refCompany).findList();


        //String sqlsp = "exec dbo.gtk_rpt_logist_www :v_startdate, :v_enddate, :v_site,:v_type_rep";
        /*List<DriverTransportCompany> result =   Ebean.createSqlQuery(sqlsp).setParameter("v_startdate",dateBegin)
                .setParameter("v_enddate",dateEnd)
                .setParameter("v_site",site)
                .setParameter("v_type_rep",typeReport)
                .findList().stream().map(this::mapSqlRowToReportPrecisionOrder).collect(toList());*/
//
//        return   Ebean.createSqlQuery("select * from GTK_ALL_CAR").findList()
//                .stream()
//                .map(this::mapSqlRowToTransportCompanyWithDriver)
//                .collect(toList());


    }


    public List<TransportCompany> transportCompanyByEnterprise(String enterpriseName){

        return TransportCompany.find.where().eq("enterpriseName",enterpriseName).findList();
        /*return   Ebean.createSqlQuery("select distinct EnterpriseName, vend_num, name from GTK_ALL_CAR where enterpriseName = :enterpriseName")
                      .setParameter("enterpriseName",enterpriseName).findList()
                      .stream()
                      .map(this::mapSqlRowToTransportCompany)
                      .collect(toList());*/
    }

    private  TransportCompany mapSqlRowToTransportCompany(SqlRow sqlRow) {
        TransportCompany transportCompany = new TransportCompany();

        transportCompany.setCode(sqlRow.getString("vend_num"));
        transportCompany.setName(sqlRow.getString("name"));
        transportCompany.setAddress(sqlRow.getString("address"));
        transportCompany.setContactPerson(sqlRow.getString("contact"));
        transportCompany.setPhoneContactPerson(sqlRow.getString("phone"));

        return transportCompany;
    }

    private  TransportCompanyWithDriver mapSqlRowToTransportCompanyWithDriver(SqlRow sqlRow) {
         TransportCompanyWithDriver transportCompanyWithDriver = new TransportCompanyWithDriver();
         transportCompanyWithDriver.setId(sqlRow.getString("id"));
         transportCompanyWithDriver.setCodeCompany(sqlRow.getString("vend_num"));
         transportCompanyWithDriver.setNameCompany(sqlRow.getString("name"));
         transportCompanyWithDriver.setAddressCompany(sqlRow.getString("address"));
         transportCompanyWithDriver.setContactPersonCompany(sqlRow.getString("contact"));
         transportCompanyWithDriver.setPhoneContactPersonCompany(sqlRow.getString("phone"));
         transportCompanyWithDriver.setDriverFullName(sqlRow.getString("driver"));
         transportCompanyWithDriver.setDriverPhone(sqlRow.getString("driverPhone"));
         transportCompanyWithDriver.setEnterpriseName(sqlRow.getString("EnterpriseName"));

        return transportCompanyWithDriver;
    }

    public List<DeviationShipment> deviationShipmentList(){
       /* if(java.time.Duration.between(timeUpdateDeviation,LocalTime.now()).toMinutes() > 60){
            updateTimeUpdateDeviation();
        }*/
        List<DeviationShipment> _deviationShipmentList = Ebean.find(DeviationShipment.class).findList();
        DeviationShipment nullDeviation =  new DeviationShipment();
         nullDeviation.setId(-1);
         nullDeviation.setDescription(" ");
        _deviationShipmentList.add(nullDeviation);
        return  _deviationShipmentList.stream().sorted((o1, o2) ->  o1.getId() - o2.getId() ).collect(toList());
    }

    private void updateTimeUpdateDeviation() {
        timeUpdateDeviation = java.time.LocalDateTime.now();
        listDeviation().stream().filter(d -> d.getType() == "Отгрузка")
                       .collect(toList());


    }


    public List<DeviationDelivery> deviationDeliveryList() {
        List<DeviationDelivery> _deviationDeliveriList = Ebean.find(DeviationDelivery.class).findList();
        DeviationDelivery nullDeviationDelivery = new DeviationDelivery();
        nullDeviationDelivery.setId(-1);
        nullDeviationDelivery.setDescription("");
        _deviationDeliveriList.add(nullDeviationDelivery);
        return  _deviationDeliveriList.stream().sorted((o1, o2) ->  o1.getId() - o2.getId() ).collect(toList());
    }


    public long countMinuteInWorkDay(String serviceDstlName){
        Enterprise serviceDstl = dstlService.getEnterprise(serviceDstlName);
        Long allworkTime =   ofNullable(WorkTime.find.where().eq("serviceDstl",serviceDstl).eq("workTime",true).findList())
                .map(e -> e.stream().mapToLong(t -> java.time.Duration.between(t.getStartTime().toLocalTime(),t.getEndTime().toLocalTime()).toMinutes()).sum()).orElse(0L);
         long weekTime =  Optional.ofNullable(WorkTime.find.where().eq("serviceDstl",serviceDstl).ne("workTime",true).findList())
                .map(e -> e.stream().mapToLong(t -> java.time.Duration.between(t.getStartTime().toLocalTime(),t.getEndTime().toLocalTime()).toMinutes()).sum()).orElse(0L);

        return  allworkTime - weekTime;
    }

}