package services;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import model.DeviationDelivery;
import model.DeviationShipment;
import model.Enterprise;
import model.plan.JournalShipment;
import model.plan.PlanShipment;
import model.plan.PlanShipmentItem;
import org.jetbrains.annotations.NotNull;
import utils.DbUtils;

import javax.inject.Singleton;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.Optional.ofNullable;
import static play.mvc.Results.TODO;

/**
 * Created by Gukov on 01.11.2016.
 */
@Singleton
public class PlanDayService {



    public List<JournalShipment> journalShipmentList(){

        List<JournalShipment> _journalShipments = new ArrayList<>();
        JournalShipment journalShipment = new JournalShipment();
        journalShipment.setId(1L);
        journalShipment.setSenderEnterprise("ГОТЭК");
        journalShipment.setTypeShipment("Доставка");
        journalShipment.setNumberDispatcher("5439");
        journalShipment.setInPlanDay(false);
        journalShipment.setDateCreateDispatcher(LocalDateTime.of(2016,3,21,14,25).format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));
        journalShipment.setDateShipmentDispatcher(LocalDate.of(2016,3,23).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipment.setDateDeliveryDispatcher(LocalDate.of(2016,3,24).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipment.setExistInStore(true);
        journalShipment.setDateToStore(LocalDateTime.of(2016,3,20,17,20).format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));
        journalShipment.setPlaceLoading("ПЖ");
        journalShipment.setStatusDispatcher("заказано");
        journalShipment.setNumberOrder("2715");
        journalShipment.setNumberItem("45");
        journalShipment.setNameOrder("Кофе - №3");
        journalShipment.setNameCustomer("Марс ООО");
        journalShipment.setCodeCustomer("П0000001");
        journalShipment.setPlaceDelivery("Россия Москва");
        journalShipment.setSizeOrder(2000);
        journalShipment.setSizePallet("800X1200");
        journalShipment.setPackingMethod("Поддон");
        journalShipment.setCountPlace(22);
        journalShipment.setCapacityOrder(7000);
        journalShipment.setTypeTransport("82");
        journalShipment.setManagerBackOffice("Тимофеев А.А.");
        journalShipment.setNote("");
     _journalShipments.add(journalShipment);
        JournalShipment journalShipment1 = new JournalShipment();
        journalShipment1.setId(2L);
        journalShipment1.setSenderEnterprise("ГОТЭК");
        journalShipment1.setTypeShipment("Самовывоз");
        journalShipment1.setNumberDispatcher("5438");
        journalShipment1.setInPlanDay(false);
        journalShipment1.setDateCreateDispatcher(LocalDateTime.of(2016,3,25,13,25).format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));
        journalShipment1.setDateShipmentDispatcher(LocalDate.of(2016,3,26).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipment1.setDateDeliveryDispatcher(LocalDate.of(2016,3,27).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipment1.setExistInStore(true);
        journalShipment1.setDateToStore(LocalDateTime.of(2016,3,24,16,21).format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));
        journalShipment1.setPlaceLoading("ПЖ");
        journalShipment1.setStatusDispatcher("заказано");
        journalShipment1.setNumberOrder("2714");
        journalShipment1.setNumberItem("43");
        journalShipment1.setNameOrder("Конфеты - №3");
        journalShipment1.setNameCustomer("Марс ООО");
        journalShipment1.setCodeCustomer("П0000001");
        journalShipment1.setPlaceDelivery("Россия Тула");
        journalShipment1.setSizeOrder(1000);
        journalShipment1.setSizePallet("800X1200");
        journalShipment1.setPackingMethod("Поддон");
        journalShipment1.setCountPlace(20);
        journalShipment1.setCapacityOrder(5000);
        journalShipment1.setTypeTransport("Компл.");
        journalShipment1.setManagerBackOffice("Иванов А.А.");
        journalShipment1.setNote("");
        _journalShipments.add(journalShipment1);



        return _journalShipments;
    }

    public List<PlanShipmentItem> selectItemPlan(Date datePlan, String nameServiceDstl){
        List<PlanShipmentItem> _items = ofNullable(Ebean.createQuery(PlanShipment.class).where().eq("datePlan",datePlan).findUnique())
                .map(e -> e.getPlanShipmentItems()).orElse(null);
        if (Objects.nonNull(_items)){
            _items.stream().forEach(e -> {
                if(Objects.isNull(e.getDeviationShipment())){

                    e.setDeviationShipment(new DeviationShipment(-1,""));
                }
                if(Objects.isNull(e.getDeviationDelivery())){
                    e.setDeviationDelivery(new DeviationDelivery(-1,""));
                }
            });
        }


        return _items;
    }



    public PlanShipment createPlan(Date datePlan,String nameServiceDstl){
        Enterprise _enterprise = DbUtils.enterpriseFromUser(nameServiceDstl);
       PlanShipment planShipment =  ofNullable(Ebean.createQuery(PlanShipment.class)
                                 .where()
                                 .eq("serviceDstl.id",_enterprise.getId())
                                 .eq("datePlan",datePlan).findUnique())
                 .orElseGet(() -> {
                     PlanShipment _planShipment = new PlanShipment();
                     _planShipment.setDatePlan(datePlan);
                     _planShipment.setServiceDstl(_enterprise);
                  //   _planShipment.setCreatePlan(Date.valueOf(LocalDate.now()));
                     _planShipment.setName("No name");
                     Ebean.save(_planShipment);
                     return _planShipment;
                 });


       return planShipment;
    }

    public PlanShipmentItem savePlanShipmentItem(JsonNode value,String nameServiceDstl){
        Enterprise _enterprise = DbUtils.enterpriseFromUser(nameServiceDstl);
        Date _datePlan =  dateFromStringInFormat_dd_MM_yyyy(value.findValue("datePlan").asText());
        PlanShipment planShipment =
                  Optional.ofNullable(Ebean.find(PlanShipment.class)
                                           .where().eq("datePlan",_datePlan).eq("serviceDstl",_enterprise)
                                           .findUnique())
                          .orElse(createPlan(_datePlan,_enterprise.getName()));
        PlanShipmentItem _newPlanShipmentItem = new PlanShipmentItem();
        _newPlanShipmentItem.setSenderEnterprise(_enterprise);
        _newPlanShipmentItem.setPlanShipment(planShipment);
        _newPlanShipmentItem.setTypeShipment(value.findValue("typeShipment").asText());
        // Необходимо держать ссылку на план отрузки
        _newPlanShipmentItem.setPlanLoad(value.findValue("planLoad").asBoolean(false));
       _newPlanShipmentItem.setDateShipmentDispatcher(dateFromStringInFormat_dd_MM_yyyy(value.findValue("dateShipmentDispatcher").asText()));
        //проверка причины
       _newPlanShipmentItem.setDeviationShipment(null);
       _newPlanShipmentItem.setDateDeliveryDispatcher(dateFromStringInFormat_dd_MM_yyyy(value.findValue("dateDeliveryDispatcher").asText()));
       _newPlanShipmentItem.setDateDeliveryFact(dateFromStringInFormat_dd_MM_yyyy(value.findValue("dateDeliveryFact").asText()));
        //проверка причины
       _newPlanShipmentItem.setDeviationDelivery(null);
       _newPlanShipmentItem.setExistInStore(value.findValue("existInStore").asBoolean(false));
       _newPlanShipmentItem.setDateToStore(Timestamp.valueOf(LocalDateTime.parse(value.findValue("dateToStore").asText(),DateTimeFormatter.ofPattern("dd-MM-yy hh:mm"))));
       _newPlanShipmentItem.setPlaceShipment(value.findValue("placeShipment").asText());
       _newPlanShipmentItem.setStatusDispatcher(value.findValue("statusDispatcher").asText());
       _newPlanShipmentItem.setNumberDispatcher(value.findValue("numberDispatcher").asText());
       _newPlanShipmentItem.setDateCreateDispatcher(Timestamp.valueOf(LocalDateTime.parse(value.findValue("statusDispatcher").asText(),DateTimeFormatter.ofPattern("dd-MM-yy hh:mm"))));
       _newPlanShipmentItem.setNumberOrder(value.findValue("numberOrder").asText());
       _newPlanShipmentItem.setLineOrder(value.findValue("lineOrder").asInt());
       _newPlanShipmentItem.setNumberItem(value.findValue("numberItem").asText());
       _newPlanShipmentItem.setNameOrder(value.findValue("nameOrder").asText());
       _newPlanShipmentItem.setCodeCustomer(value.findValue("codeCustomer").asText());
       _newPlanShipmentItem.setSeqCustomer(value.findValue("seqCustomer").asInt());
       _newPlanShipmentItem.setNameCustomer(value.findValue("nameCustomer").asText());
       _newPlanShipmentItem.setPlaceDelivery(value.findValue("placeDelivery").asText());
       _newPlanShipmentItem.setSizeOrder(value.findValue("sizeOrder").asInt());
       _newPlanShipmentItem.setSizePallet(value.findValue("sizePallet").asText());
       _newPlanShipmentItem.setCountPlace(value.findValue("countPlace").asInt());
       _newPlanShipmentItem.setCapacityOrder(value.findValue("capacityOrder").asText());
       _newPlanShipmentItem.setTypeTransport(value.findValue("typeTransport").asText());
       _newPlanShipmentItem.setTimeToLoad(value.findValue("timeToLoad").asInt());
        // ???????
        _newPlanShipmentItem.setTransportCompanyPlan(null);
        _newPlanShipmentItem.setTransportCompanyFact(null);
        _newPlanShipmentItem.setDriverTransportCompany(null);

        _newPlanShipmentItem.setNumberGate(value.findValue("numberGate").asInt());
        _newPlanShipmentItem.setDistanceDelivery(value.findValue("distanceDelivery").asDouble());
        _newPlanShipmentItem.setCostTrip(value.findValue("costTrip").asDouble());
        _newPlanShipmentItem.setTimeLoad(Timestamp.valueOf(LocalDateTime.parse(value.findValue("timeLoad").asText(),DateTimeFormatter.ofPattern("dd-MM-yy hh:mm"))));
        _newPlanShipmentItem.setManagerBackOffice(value.findValue("managerBackOffice").asText());
        _newPlanShipmentItem.setNote(value.findValue("note").asText());

        return null;
    }

    @NotNull
    private Date dateFromStringInFormat_dd_MM_yyyy(String dateValue) {
        return Date.valueOf(LocalDate.parse(dateValue, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }


}
