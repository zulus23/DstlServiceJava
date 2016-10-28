package services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Singleton;
import model.*;
import play.db.DB;
import play.db.DBApi;
import play.db.Database;
import play.db.NamedDatabase;
import utils.DbUtils;

import javax.inject.Inject;
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
    private final List<Enterprise> enterpriseList = Arrays.asList(new Enterprise(0, "ЗАО ГОТЭК-ЦПУ", "", true),
            new Enterprise(0, "ЗАО ГОТЭК-СЕВЕРО-ЗАПАД", "", true),
            new Enterprise(0, "ГОТЭК", "", false),
            new Enterprise(0, "ПРИНТ", "", false),
            new Enterprise(0, "ПОЛИПАК", "", false),
            new Enterprise(0, "ЛИТАР", "", false),
            new Enterprise(0, "ГОФРО", "", false)

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


        return Arrays.asList(new DeviationShipment(0, "Отсутствие свободных транспортных средств"),
                new DeviationShipment(1, "Низкий тариф на доставку"),
                new DeviationShipment(2, "Плохие погодные условия"),
                new DeviationShipment(3, "Не обеспечено продукцией"),
                new DeviationShipment(4, "Недостаточность технического ресурса (погрузчики)"),
                new DeviationShipment(5, "Отсутствие основного персонала"),
                new DeviationShipment(6, "Ошибки персонала предприятия"),
                new DeviationShipment(7, "Ошибки персонала перевозчика"),
                new DeviationShipment(8, "Отгрузка ранее плановой даты"),
                new DeviationShipment(9, "Комплектация грузов"));
    }

    public List<Deviation> listDeviationDelivery() {
        return Arrays.asList(new DeviationDelivery(0, "Выход из строя ТС"),
                new DeviationDelivery(1, "Плохие погодные условия"),
                new DeviationDelivery(2, "Допущение отклонений от маршрута"),
                new DeviationDelivery(3, "Задержка ТС органами надзора"),
                new DeviationDelivery(4, "Некорректное оформление рабочей документации"),
                new DeviationDelivery(5, "Проблема с системой на складе клиента"),
                new DeviationDelivery(6, "Поздняя отгрузка"),
                new DeviationDelivery(7, "Доставка ЗК ранее плановой даты"));
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

    private Deviation mapSqlRowToDeviation(SqlRow row) {
        Deviation deviation = new Deviation(0, row.getString("Value"), "");
        if(row.getString("TypeName").equals("UDTOtmena")){
            deviation.setType("Отгрузка");
        }else {
            deviation.setType("Доставка");
        }
        return deviation;
    }
}