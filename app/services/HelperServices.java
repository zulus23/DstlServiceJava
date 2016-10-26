package services;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Singleton;
import model.DeviationDelivery;
import model.DeviationShipment;
import model.Enterprise;
import model.NormaTimeLoading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhukov on 22.10.2016.
 */
@Singleton
public class HelperServices {
    private final List<Enterprise> enterpriseList = Arrays.asList(new Enterprise(0,"ЗАО ГОТЭК-ЦПУ","",true),
            new Enterprise(1,"ЗАО ГОТЭК-СЕВЕРО-ЗАПАД","",true),
            new Enterprise(2,"ГОТЭК","",false),
            new Enterprise(3,"ПРИНТ","",false),
            new Enterprise(4,"ПОЛИПАК","",false),
            new Enterprise(5,"ЛИТАР","",false),
            new Enterprise(6,"ГОФРО","",false)

    );


    public List<Enterprise> listEnterprise() {
        return enterpriseList;
    }
    public List<Enterprise> serviceDstl() {
        return enterpriseList.stream().filter(e -> e.isService()).collect(Collectors.toList());
    }


    public List<DeviationShipment> listDeviationShipment() {

        return Arrays.asList(new DeviationShipment(0,"Отсутствие свободных транспортных средств"),
                             new DeviationShipment(1,"Низкий тариф на доставку"),
                             new DeviationShipment(2,"Плохие погодные условия"),
                             new DeviationShipment(3,"Не обеспечено продукцией"),
                             new DeviationShipment(4,"Недостаточность технического ресурса (погрузчики)"),
                             new DeviationShipment(5,"Отсутствие основного персонала"),
                             new DeviationShipment(6,"Ошибки персонала предприятия"),
                             new DeviationShipment(7,"Ошибки персонала перевозчика"),
                             new DeviationShipment(8,"Отгрузка ранее плановой даты"),
                             new DeviationShipment(9,"Комплектация грузов"));
    }

    public List<DeviationDelivery> listDeviationDelivery() {
        return Arrays.asList(new DeviationDelivery(0,"Выход из строя ТС"),
                             new DeviationDelivery(1,"Плохие погодные условия"),
                             new DeviationDelivery(2,"Допущение отклонений от маршрута"),
                             new DeviationDelivery(3,"Задержка ТС органами надзора"),
                             new DeviationDelivery(4,"Некорректное оформление рабочей документации"),
                             new DeviationDelivery(5,"Проблема с системой на складе клиента"),
                             new DeviationDelivery(6,"Поздняя отгрузка"),
                             new DeviationDelivery(7,"Доставка ЗК ранее плановой даты"));
    }


    public List<NormaTimeLoading> normaTimeLoadingList(){
        return Ebean.find(NormaTimeLoading.class).findList();
    }
    public  Integer saveNormaTimeLoading(JsonNode value){
        NormaTimeLoading normaTimeLoading = new NormaTimeLoading(value.findValue("id").asInt(),
                                                                 enterpriseList.get(3),
                                                                 value.findValue("packageTime").asInt(),
                                                                 value.findValue("commissionTime").asInt(),
                                                                 value.findValue("placerTime").asInt()
                );


        Ebean.save(normaTimeLoading);
        return 0;
    }

}
