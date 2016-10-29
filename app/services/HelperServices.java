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
import java.time.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.avaje.ebean.Ebean.createSqlQuery;
import static com.avaje.ebean.Ebean.find;
import static java.util.stream.Collectors.toList;
import static utils.DbUtils.connectToSLGotek;

/**
 * Created by Zhukov on 22.10.2016.
 */
@Singleton
public class HelperServices {
    private final List<Enterprise> enterpriseList = Arrays.asList(new Enterprise(0, "ЗАО ГОТЭК-ЦПУ", "SL_CPU", true),
            new Enterprise(0, "ЗАО ГОТЭК-СЕВЕРО-ЗАПАД", "SL_SPB", true),
            new Enterprise(0, "ГОТЭК", "SL_GOTEK", false),
            new Enterprise(0, "ПРИНТ", "SL_PRINT", false),
            new Enterprise(0, "ПОЛИПАК", "SL_POLYPACK", false),
            new Enterprise(0, "ЛИТАР", "SL_LITAR", false),
            new Enterprise(0, "ЦЕНТР", "SL_CENTER", false)

    );


    public List<Enterprise> listEnterprise() {
        Query<Enterprise> enterpriseQuery = find(Enterprise.class);

        if (enterpriseQuery.findList().size() > 0) {
            return enterpriseQuery.findList();
        } else {
            Ebean.saveAll(enterpriseList);
            return enterpriseQuery.findList();
        }

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
        String _query = "SELECT TypeName,Value,Description FROM UserDefinedTypeValues\n" +
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
        Deviation deviation = new Deviation(0, row.getString("Value"), "");
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
}