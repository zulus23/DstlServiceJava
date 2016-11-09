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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import utils.DbUtils;

import javax.inject.Singleton;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        DeviationShipment deviationShipment =   Optional.ofNullable(Ebean.find(DeviationShipment.class).where().eq("id", value.findValue("deviationShipment")
                                                                .findValue("id").asInt())
                                                                .findUnique()).orElse(null);
       _newPlanShipmentItem.setDeviationShipment(deviationShipment);
       _newPlanShipmentItem.setDateDeliveryDispatcher(dateFromStringInFormat_dd_MM_yyyy(value.findValue("dateDeliveryDispatcher").asText()));

        Optional.ofNullable(value.findValue("dateDeliveryFact")).ifPresent(e -> {
                    _newPlanShipmentItem.setDateDeliveryFact(dateFromStringInFormat_dd_MM_yyyy(e.asText()));
                }
        );


        DeviationDelivery deviationDelivery =   Optional.ofNullable(Ebean.find(DeviationDelivery.class).where().eq("id", value.findValue("deviationDelivery")
                                                                                                                              .findValue("id").asInt())
                                      .findUnique()).orElse(null);
       _newPlanShipmentItem.setDeviationDelivery(deviationDelivery);
       _newPlanShipmentItem.setExistInStore(value.findValue("existInStore").asBoolean(false));
        Optional.ofNullable(value.findValue("dateToStore")).ifPresent(e -> {
            DateTime dateTime =   DateTime.parse(value.findValue("dateToStore").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
            LocalDateTime localTime = LocalDateTime.of(dateTime.getYear(),
                                                       dateTime.getMonthOfYear(),
                                                       dateTime.getDayOfMonth(),
                                                       dateTime.getHourOfDay(),
                                                       dateTime.getMinuteOfHour());
           _newPlanShipmentItem.setDateToStore(Timestamp.valueOf(localTime));
        });
        Optional.ofNullable(value.findValue("placeShipment")).ifPresent(e -> {
            _newPlanShipmentItem.setPlaceShipment(e.asText());
        });
        Optional.ofNullable(value.findValue("statusDispatcher")).ifPresent( e -> {
            _newPlanShipmentItem.setStatusDispatcher(e.asText());
        });
        Optional.ofNullable(value.findValue("numberDispatcher")).ifPresent( e ->{
            _newPlanShipmentItem.setNumberDispatcher(e.asText());
        } );

        Optional.ofNullable(value.findValue("dateCreateDispatcher")).ifPresent(e -> {
            DateTime dateTime =   DateTime.parse(value.findValue("dateCreateDispatcher").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
            LocalDateTime localTime = LocalDateTime.of(dateTime.getYear(),
                    dateTime.getMonthOfYear(),
                    dateTime.getDayOfMonth(),
                    dateTime.getHourOfDay(),
                    dateTime.getMinuteOfHour());
            _newPlanShipmentItem.setDateCreateDispatcher(Timestamp.valueOf(localTime));
         });

       _newPlanShipmentItem.setNumberOrder(value.findValue("numberOrder").asText());
        Optional.ofNullable(value.findValue("lineOrder")).ifPresent(e -> {
            _newPlanShipmentItem.setLineOrder(e.asInt());
        });
        Optional.ofNullable(value.findValue("numberItem")).ifPresent(e -> {
            _newPlanShipmentItem.setNumberItem(e.asText());
        });
        Optional.ofNullable(value.findValue("nameOrder")).ifPresent(e -> {
            _newPlanShipmentItem.setNameOrder(e.asText());
        });
        Optional.ofNullable(value.findValue("codeCustomer")).ifPresent(e ->{
            _newPlanShipmentItem.setCodeCustomer(e.asText());
        });
        Optional.ofNullable(value.findValue("seqCustomer")).ifPresent(e->{
            _newPlanShipmentItem.setSeqCustomer(e.asInt());
        });
        Optional.ofNullable(value.findValue("nameCustomer")).ifPresent(e -> {
            _newPlanShipmentItem.setNameCustomer(e.asText());
        });
        Optional.ofNullable(value.findValue("placeDelivery")).ifPresent(e -> {
            _newPlanShipmentItem.setPlaceDelivery(e.asText());
        });
        Optional.ofNullable(value.findValue("sizeOrder")).ifPresent(e -> {
            _newPlanShipmentItem.setSizeOrder(e.asInt());
        });
        Optional.ofNullable(value.findValue("sizePallet")).ifPresent(e -> {
            _newPlanShipmentItem.setSizePallet(e.asText());
        });
        Optional.ofNullable(value.findValue("countPlace")).ifPresent(e -> {
            _newPlanShipmentItem.setCountPlace(e.asInt());
        });
        Optional.ofNullable(value.findValue("capacityOrder")).ifPresent( e -> {
            _newPlanShipmentItem.setCapacityOrder(e.asText());
        });
        Optional.ofNullable(value.findValue("typeTransport")).ifPresent(e -> {
            _newPlanShipmentItem.setTypeTransport(e.asText());
        });
        Optional.ofNullable(value.findValue("timeToLoad")).ifPresent(e -> {
            _newPlanShipmentItem.setTimeToLoad(e.asInt());
        });

        // ???????
        _newPlanShipmentItem.setTransportCompanyPlan(null);
        _newPlanShipmentItem.setTransportCompanyFact(null);
        _newPlanShipmentItem.setDriverTransportCompany(null);
        Optional.ofNullable(value.findValue("numberGate")).ifPresent(e -> {
            _newPlanShipmentItem.setNumberGate(e.asInt());
        });
        Optional.ofNullable(value.findValue("distanceDelivery")).ifPresent(e -> {
            _newPlanShipmentItem.setDistanceDelivery(e.asDouble());
        });
        Optional.ofNullable(value.findValue("costTrip")).ifPresent(e -> {
            _newPlanShipmentItem.setCostTrip(e.asDouble());
        });

        Optional.ofNullable(value.findValue("timeLoad")).ifPresent( e -> {
            DateTime dateTime =   DateTime.parse(value.findValue("timeLoad").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
            LocalDateTime localTime = LocalDateTime.of(dateTime.getYear(),
                    dateTime.getMonthOfYear(),
                    dateTime.getDayOfMonth(),
                    dateTime.getHourOfDay(),
                    dateTime.getMinuteOfHour());

           _newPlanShipmentItem.setTimeLoad(Timestamp.valueOf(localTime));
        });
        Optional.ofNullable(value.findValue("managerBackOffice")).ifPresent(e -> {
            _newPlanShipmentItem.setManagerBackOffice(e.asText());
        });
        Optional.ofNullable(value.findValue("note")).ifPresent(e -> {
            _newPlanShipmentItem.setNote(e.asText());
        });

        Ebean.save(_newPlanShipmentItem);
        return _newPlanShipmentItem;
    }

    @NotNull
    private Date dateFromStringInFormat_dd_MM_yyyy(String dateValue) {
        return Date.valueOf(LocalDate.parse(dateValue, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }


}
