package services;

import auth.DstlProfile;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import model.*;
import model.plan.JournalShipment;
import model.plan.PlanRequestTransport;
import model.plan.PlanShipment;
import model.plan.PlanShipmentItem;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import utils.DbUtils;
import utils.PlanShipmentItemException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.*;

import static com.avaje.ebean.Ebean.find;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Created by Gukov on 01.11.2016.
 */
@Singleton
public class PlanDayService {

    @Inject
    private DstlService dstlService;

    public List<JournalShipment> journalShipmentList(String nameServiceDstl){

        List<JournalShipment> _journalShipments = new ArrayList<>();
        JournalShipment journalShipment = new JournalShipment();
        journalShipment.setId(1L);
        journalShipment.setSenderEnterprise( dstlService.getEnterprise("ГОТЭК"));
        journalShipment.setTypeShipment("Доставка");
        journalShipment.setNumberDispatcher("5439");
        journalShipment.setInPlanDay(false);
        journalShipment.setDateCreateDispatcher(Timestamp.valueOf(LocalDateTime.of(2016,3,21,14,25,0,0)));
        journalShipment.setDateShipmentDispatcher(LocalDate.of(2016,3,23).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipment.setDateDeliveryDispatcher(LocalDate.of(2016,3,24).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipment.setExistInStore(true);
        journalShipment.setDateToStore(Timestamp.valueOf(LocalDateTime.of(2016,3,20,17,20)));
        journalShipment.setPlaceLoading("ПЖ");
        journalShipment.setStatusDispatcher("заказано");
        journalShipment.setNumberOrder("2715");
        journalShipment.setNumberItem("45");
        journalShipment.setNameOrder("Шоколад - №3");
        journalShipment.setNameCustomer("Звезда ООО");
        journalShipment.setCodeCustomer("П0000001");
        journalShipment.setPlaceDelivery("Россия Москва1");
        journalShipment.setSizeOrder(2000);
        journalShipment.setSizePallet("800X1200");
        journalShipment.setPackingMethod("Россыпь");
        journalShipment.setCountPlace(22);
        journalShipment.setCapacityOrder(7000);
        journalShipment.setTypeTransport("82");
        journalShipment.setManagerBackOffice("Петров А.А.");
        journalShipment.setNote("");
       /* TransportCompany transportCompany =  TransportCompany.find.byId("A7ED6ABB-F607-438D-84BE-56BDDA07A192");
        journalShipment.setTransportCompanyPlan(transportCompany);*/


     _journalShipments.add(journalShipment);
        JournalShipment journalShipment1 = new JournalShipment();
        journalShipment1.setId(2L);
        journalShipment1.setSenderEnterprise(dstlService.getEnterprise("ГОТЭК"));
        journalShipment1.setTypeShipment("Самовывоз");
        journalShipment1.setNumberDispatcher("5438");
        journalShipment1.setInPlanDay(false);
        journalShipment1.setDateCreateDispatcher(Timestamp.valueOf(LocalDateTime.of(2016,3,25,13,25,0,0)));
        journalShipment1.setDateShipmentDispatcher(LocalDate.of(2016,11,26).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipment1.setDateDeliveryDispatcher(LocalDate.of(2016,3,27).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipment1.setExistInStore(true);
        journalShipment1.setDateToStore(Timestamp.valueOf(LocalDateTime.of(2016,3,24,16,21)));
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
        journalShipment1.setPackingMethod("Пакет");
        journalShipment1.setCountPlace(20);
        journalShipment1.setCapacityOrder(5000);
        journalShipment1.setTypeTransport("Компл.");
        journalShipment1.setManagerBackOffice("Иванов А.А.");
        journalShipment1.setNote("");
        _journalShipments.add(journalShipment1);
        JournalShipment journalShipmentP = new JournalShipment();
        journalShipmentP.setId(3L);
        journalShipmentP.setSenderEnterprise( dstlService.getEnterprise("ПРИНТ"));
        journalShipmentP.setTypeShipment("Доставка");
        journalShipmentP.setNumberDispatcher("5439");
        journalShipmentP.setInPlanDay(false);
        journalShipmentP.setDateCreateDispatcher(Timestamp.valueOf(LocalDateTime.of(2016,3,21,14,25,0,0)));
        journalShipmentP.setDateShipmentDispatcher(LocalDate.of(2016,3,23).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipmentP.setDateDeliveryDispatcher(LocalDate.of(2016,3,24).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipmentP.setExistInStore(true);
        journalShipmentP.setDateToStore(Timestamp.valueOf(LocalDateTime.of(2016,3,20,17,20)));
        journalShipmentP.setPlaceLoading("ПЖ");
        journalShipmentP.setStatusDispatcher("заказано");
        journalShipmentP.setNumberOrder("2715");
        journalShipmentP.setNumberItem("45");
        journalShipmentP.setNameOrder("Кофе - №3");
        journalShipmentP.setNameCustomer("Марс ООО");
        journalShipmentP.setCodeCustomer("П0000001");
        journalShipmentP.setPlaceDelivery("Россия Москва");
        journalShipmentP.setSizeOrder(2000);
        journalShipmentP.setSizePallet("800X1200");
        journalShipmentP.setPackingMethod("Россыпь");
        journalShipmentP.setCountPlace(22);
        journalShipmentP.setCapacityOrder(7000);
        journalShipmentP.setTypeTransport("82");
        journalShipmentP.setManagerBackOffice("Тимофеев А.А.");
        journalShipmentP.setNote("");
        TransportCompany transportCompanyP =  TransportCompany.find.byId("A7ED6ABB-F607-438D-84BE-56BDDA07A192");
        journalShipmentP.setTransportCompanyPlan(transportCompanyP);
        _journalShipments.add(journalShipmentP);


        JournalShipment journalShipmentSp = new JournalShipment();
        journalShipmentSp.setId(4L);
        journalShipmentSp.setSenderEnterprise( dstlService.getEnterprise("СЕВЕР"));
        journalShipmentSp.setTypeShipment("Доставка");
        journalShipmentSp.setNumberDispatcher("54392");
        journalShipmentSp.setInPlanDay(false);
        journalShipmentSp.setDateCreateDispatcher(Timestamp.valueOf(LocalDateTime.of(2016,3,21,14,25,0,0)));
        journalShipmentSp.setDateShipmentDispatcher(LocalDate.of(2016,3,23).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipmentSp.setDateDeliveryDispatcher(LocalDate.of(2016,3,24).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipmentSp.setExistInStore(true);
        journalShipmentSp.setDateToStore(Timestamp.valueOf(LocalDateTime.of(2016,3,20,17,20)));
        journalShipmentSp.setPlaceLoading("ПЖ");
        journalShipmentSp.setStatusDispatcher("заказано");
        journalShipmentSp.setNumberOrder("27152");
        journalShipmentSp.setNumberItem("452");
        journalShipmentSp.setNameOrder("Кофе - №22222");
        journalShipmentSp.setNameCustomer("Марс ООО 222222222222");
        journalShipmentSp.setCodeCustomer("П0000001");
        journalShipmentSp.setPlaceDelivery("Россия Москва");
        journalShipmentSp.setSizeOrder(2000);
        journalShipmentSp.setSizePallet("800X1200");
        journalShipmentSp.setPackingMethod("Россыпь");
        journalShipmentSp.setCountPlace(22);
        journalShipmentSp.setCapacityOrder(7000);
        journalShipmentSp.setTypeTransport("82");
        journalShipmentSp.setManagerBackOffice("Тимофеев А.А.");
        journalShipmentSp.setNote("");
        //TransportCompany transportCompanySp =  TransportCompany.find.byId("A7ED6ABB-F607-438D-84BE-56BDDA07A192");
        journalShipmentSp.setTransportCompanyPlan(null);



        _journalShipments.add(journalShipmentSp);

        JournalShipment journalShipmentGotek = new JournalShipment();
        journalShipmentGotek.setId(5L);
        journalShipmentGotek.setSenderEnterprise( dstlService.getEnterprise("ГОТЭК"));
        journalShipmentGotek.setTypeShipment("Доставка");
        journalShipmentGotek.setNumberDispatcher("5439");
        journalShipmentGotek.setInPlanDay(false);
        journalShipmentGotek.setDateCreateDispatcher(Timestamp.valueOf(LocalDateTime.of(2016,3,21,14,25,0,0)));
        journalShipmentGotek.setDateShipmentDispatcher(LocalDate.of(2016,3,23).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipmentGotek.setDateDeliveryDispatcher(LocalDate.of(2016,3,24).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        journalShipmentGotek.setExistInStore(true);
        journalShipmentGotek.setDateToStore(Timestamp.valueOf(LocalDateTime.of(2016,3,20,17,20)));
        journalShipmentGotek.setPlaceLoading("ПЖ");
        journalShipmentGotek.setStatusDispatcher("заказано");
        journalShipmentGotek.setNumberOrder("3715");
        journalShipmentGotek.setNumberItem("44");
        journalShipmentGotek.setNameOrder("Белочка Шоколадная - №3");
        journalShipmentGotek.setNameCustomer("Звезда-Востока ООО");
        journalShipmentGotek.setCodeCustomer("П0000002");
        journalShipmentGotek.setPlaceDelivery("Россия Московская область село Переделкино");
        journalShipmentGotek.setSizeOrder(1000);
        journalShipmentGotek.setSizePallet("1000X1000");
        journalShipmentGotek.setPackingMethod("Россыпь");
        journalShipmentGotek.setCountPlace(20);
        journalShipmentGotek.setCapacityOrder(4000);
        journalShipmentGotek.setTypeTransport("82");
        journalShipmentGotek.setManagerBackOffice("Звездун А.А.");
        journalShipmentGotek.setNote("");
       /* TransportCompany transportCompany =  TransportCompany.find.byId("A7ED6ABB-F607-438D-84BE-56BDDA07A192");
        journalShipment.setTransportCompanyPlan(transportCompany);*/
        _journalShipments.add(journalShipmentGotek);


        return _journalShipments.stream().filter(e -> e.getSenderEnterprise().getBelongToService() == dstlService.getEnterprise(nameServiceDstl).getId()).collect(toList());
    }

    public List<PlanRequestTransport> selectItemPlan(Date datePlan, String nameServiceDstl){
        return Optional.ofNullable(PlanShipment.find.where()
                                                    .eq("datePlan",datePlan)
                                                    .eq("serviceDstl",dstlService.getEnterprise(nameServiceDstl)).findUnique()).map(e ->e.getPlanRequestTransports()).orElse(null);
    }



    public PlanShipment createPlan(Date datePlan,Enterprise serviceDstl){

       PlanShipment planShipment =  PlanShipment.find
                                   .where()
                                   .eq("serviceDstl.id",serviceDstl.getId())
                                   .eq("datePlan",datePlan).findUnique();

        if(Objects.isNull(planShipment)){
            planShipment = new PlanShipment();
            planShipment.setDatePlan(datePlan);
            planShipment.setServiceDstl(serviceDstl);
            planShipment.setPlanRequestTransports(null);
            planShipment.setName("No name");
            planShipment.save();
         }

       return planShipment;
    }

    public List<PlanRequestTransport> savePlanRequestTransports(JsonNode value,String nameServiceDstl) throws PlanShipmentItemException{
        Enterprise _serviceDstl = dstlService.getEnterprise(nameServiceDstl);
        Enterprise  _enterprise = dstlService.getEnterprise(value.findValue("senderEnterprise").findValue("name").asText());
        Date _datePlan =  dateFromStringInFormat_dd_MM_yyyy(value.findValue("datePlan").asText());
        PlanShipment planShipment =  createPlan(_datePlan,_serviceDstl);
        Long maxIdInPLan =  planShipment.getPlanRequestTransports().stream().max((p,n) -> p.getId().compareTo( n.getId())).map(e -> e.getId()).orElse(0L);

        Iterator<JsonNode> nodeIterator =  value.get("data").get("models").elements();
        List<PlanRequestTransport> _temp = new ArrayList<>();

        while(nodeIterator.hasNext()){
            JsonNode valueInsert = nodeIterator.next();
            PlanRequestTransport planRequestTransport = createPlanShipmentRequestTransportItemFromJson(valueInsert,planShipment);
            _temp.add(planRequestTransport);
          // PlanShipmentItem planShipmentItem = savePlanShipmentItem(valueInsert,planShipment);
            // TODO: 07.12.2016 Необходимо добавлять только уникальные элементы
          // planShipment.getPlanRequestTransports().add(planRequestTransport);

        }

        List<PlanRequestTransport> planRequestTransports =  _temp.stream().distinct().collect(toList());
        planShipment.getPlanRequestTransports().addAll(planRequestTransports);


        try {
       //TODO Need uses transaction
      //     Ebean.beginTransaction();
            Ebean.save(planShipment);
      //      Ebean.commitTransaction();
        } catch (Exception e){
            if ( ((SQLServerException)((PersistenceException) e).getCause()).getErrorCode() == 2627 ){
                throw new PlanShipmentItemException("Такая запись уже существует");
            };


        } finally {
            if (Objects.nonNull(Ebean.currentTransaction())){
                Ebean.rollbackTransaction();
            }
        }

        return planShipment.getPlanRequestTransports().stream(). filter(e -> e.getId() > maxIdInPLan).collect(toList());


    }

    private PlanRequestTransport createPlanShipmentRequestTransportItemFromJson(JsonNode value, PlanShipment planShipment) {
        Enterprise  enterprise = dstlService.getEnterprise(value.findValue("senderEnterprise").findValue("name").asText());
        PlanRequestTransport _newPlanRequestTransport = new PlanRequestTransport();
        Optional.ofNullable(value.findValue("senderEnterprise")).ifPresent(e -> {

            _newPlanRequestTransport.setSenderEnterprise(enterprise);
        });

        _newPlanRequestTransport.setPlanShipment(planShipment);
        _newPlanRequestTransport.setTypeShipment(value.findValue("typeShipment").asText());
        // Необходимо держать ссылку на план отрузки
        //_newPlanShipmentItem.setPlanLoad(value.findValue("planLoad").asBoolean(false));
        _newPlanRequestTransport.setDateShipmentDispatcher(dateFromStringInFormat_dd_MM_yyyy(value.findValue("dateShipmentDispatcher").asText()));

        _newPlanRequestTransport.setDeviationShipment(null);
        _newPlanRequestTransport.setDateDeliveryDispatcher(dateFromStringInFormat_dd_MM_yyyy(value.findValue("dateDeliveryDispatcher").asText()));

        Optional.ofNullable(value.findValue("dateDeliveryFact")).ifPresent(e -> {
            _newPlanRequestTransport.setDateDeliveryFact(dateFromStringInFormat_dd_MM_yyyy(e.asText()));
                }
        );

        _newPlanRequestTransport.setDeviationDelivery(null);


        Optional.ofNullable(value.findValue("placeShipment")).ifPresent(e -> {
            _newPlanRequestTransport.setPlaceShipment(e.asText());
        });
        Optional.ofNullable(value.findValue("statusDispatcher")).ifPresent( e -> {
            _newPlanRequestTransport.setStatusDispatcher(e.asText());
        });
        Optional.ofNullable(value.findValue("numberDispatcher")).ifPresent( e ->{
            _newPlanRequestTransport.setNumberDispatcher(e.asText());
        } );

        Optional.ofNullable(value.findValue("dateCreateDispatcher")).ifPresent(e -> {
            DateTime dateTime =   DateTime.parse(value.findValue("dateCreateDispatcher").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
            LocalDateTime localTime = LocalDateTime.of(dateTime.getYear(),
                    dateTime.getMonthOfYear(),
                    dateTime.getDayOfMonth(),
                    dateTime.getHourOfDay(),
                    dateTime.getMinuteOfHour());
            _newPlanRequestTransport.setDateCreateDispatcher(Timestamp.valueOf(localTime));
        });


        Optional.ofNullable(value.findValue("typeTransport")).ifPresent(e -> {
            _newPlanRequestTransport.setTypeTransport(e.asText());
        });
        Optional.ofNullable(value.findValue("transportCompanyPlan")).ifPresent(e -> {
            //TODO При создании в плане фактическая компания доставки = плановой
            if(!e.equals(NullNode.getInstance())){
                TransportCompany transportCompany =  TransportCompany.find.byId(e.get("rowPointer").asText());
                _newPlanRequestTransport.setTransportCompanyPlan(transportCompany);
                _newPlanRequestTransport.setTransportCompanyFact(transportCompany);
            }

        });


        _newPlanRequestTransport.setDriverTransportCompany(null);
        /*Optional.ofNullable(value.findValue("numberGate")).ifPresent(e -> {
            _newPlanRequestTransport.setNumberGate(e.asInt());
        });*/

        Optional.ofNullable(value.findValue("timeLoad")).ifPresent( e -> {
            DateTime dateTime =   DateTime.parse(value.findValue("timeLoad").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
            LocalTime localTime = LocalTime.of(
                    dateTime.getHourOfDay(),
                    dateTime.getMinuteOfHour());

            _newPlanRequestTransport.setTimeLoad(Time.valueOf(localTime));
        });
        Optional.ofNullable(value.findValue("managerBackOffice")).ifPresent(e -> {
            _newPlanRequestTransport.setManagerBackOffice(e.asText());
        });
        Optional.ofNullable(value.findValue("note")).ifPresent(e -> {
            _newPlanRequestTransport.setNote(e.asText());
        });
        Optional.ofNullable(value.findValue("packingMethod")).ifPresent( e -> {
            _newPlanRequestTransport.setPackingMethod(e.asText());
            _newPlanRequestTransport.setTimeToLoad(dstlService.timeForLoadingByTypePacking(e.asText(),enterprise));
                }
        );
        return _newPlanRequestTransport;
    }
   /* public PlanShipmentItem savePlanShipmentItem(JsonNode value,PlanShipment planShipment) throws PlanShipmentItemException{
        Enterprise  enterprise = dstlService.getEnterprise(value.findValue("senderEnterprise").findValue("name").asText());
        PlanShipmentItem _newPlanShipmentItem = new PlanShipmentItem();
        Optional.ofNullable(value.findValue("senderEnterprise")).ifPresent(e -> {

            _newPlanShipmentItem.setSenderEnterprise(enterprise);
        });

        _newPlanShipmentItem.setPlanShipment(planShipment);
        _newPlanShipmentItem.setTypeShipment(value.findValue("typeShipment").asText());
        // Необходимо держать ссылку на план отрузки
        _newPlanShipmentItem.setPlanLoad(value.findValue("planLoad").asBoolean(false));
        _newPlanShipmentItem.setDateShipmentDispatcher(dateFromStringInFormat_dd_MM_yyyy(value.findValue("dateShipmentDispatcher").asText()));

//        Optional.ofNullable(value.findValue("deviationShipment")).ifPresent(e ->{
//            DeviationShipment deviationShipment =   Optional.ofNullable(find(DeviationShipment.class).where().eq("id", e.findValue("id").asInt())
//                    .findUnique()).orElse(null);
//            _newPlanShipmentItem.setDeviationShipment(deviationShipment);
//        });

        _newPlanShipmentItem.setDeviationShipment(null);
        _newPlanShipmentItem.setDateDeliveryDispatcher(dateFromStringInFormat_dd_MM_yyyy(value.findValue("dateDeliveryDispatcher").asText()));

        Optional.ofNullable(value.findValue("dateDeliveryFact")).ifPresent(e -> {
                    _newPlanShipmentItem.setDateDeliveryFact(dateFromStringInFormat_dd_MM_yyyy(e.asText()));
                }
        );

//        Optional.ofNullable(value.findValue("deviationDelivery")).ifPresent(e -> {
//            DeviationDelivery deviationDelivery =   Optional.ofNullable(find(DeviationDelivery.class).where().eq("id", e.findValue("id").asInt())
//                    .findUnique()).orElse(null);
//            _newPlanShipmentItem.setDeviationDelivery(deviationDelivery);
//        });
        _newPlanShipmentItem.setDeviationDelivery(null);
        _newPlanShipmentItem.setExistInStore(value.findValue("existInStore").asBoolean(false));
        Optional.ofNullable(value.findValue("dateToStore")).ifPresent(e -> {
            DateTime dateTime =   DateTime.parse(value.findValue("dateToStore").asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
            //DateTime dateTime =    new DateTime(value.findValue("dateToStore").asText());
            LocalDateTime localTime = LocalDateTime.of(dateTime.getYear(),
                    dateTime.getMonthOfYear(),
                    dateTime.getDayOfMonth(),
                    dateTime.getHourOfDay(),
                    dateTime.getMinuteOfHour());
            _newPlanShipmentItem.setDateToStore(Timestamp.valueOf(localTime));
            //_newPlanShipmentItem.setDateToStore(localTime);

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
        Optional.ofNullable(value.findValue("packingMethod")).ifPresent( e -> {
                    _newPlanShipmentItem.setPackingMethod(e.asText());
                    _newPlanShipmentItem.setTimeToLoad(dstlService.timeForLoadingByTypePacking(e.asText(),enterprise));
                }
        );

        Optional.ofNullable(value.findValue("countPlace")).ifPresent(e -> {
            _newPlanShipmentItem.setCountPlace(e.asInt());
        });
        Optional.ofNullable(value.findValue("capacityOrder")).ifPresent( e -> {
            _newPlanShipmentItem.setCapacityOrder(e.asText());
        });
        Optional.ofNullable(value.findValue("typeTransport")).ifPresent(e -> {
            _newPlanShipmentItem.setTypeTransport(e.asText());
        });
        *//*Optional.ofNullable(value.findValue("timeToLoad")).ifPresent(e -> {

        });*//*

        // ???????
        Optional.ofNullable(value.findValue("transportCompanyPlan")).ifPresent(e -> {
            //TODO При создании в плане фактическая компания доставки = плановой
            if(!e.equals(NullNode.getInstance())){
                TransportCompany transportCompany =  TransportCompany.find.byId(e.get("rowPointer").asText());
                _newPlanShipmentItem.setTransportCompanyPlan(transportCompany);
                _newPlanShipmentItem.setTransportCompanyFact(transportCompany);
            }

        });


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
            LocalTime localTime = LocalTime.of(
                    dateTime.getHourOfDay(),
                    dateTime.getMinuteOfHour());

            _newPlanShipmentItem.setTimeLoad(Time.valueOf(localTime));
        });
        Optional.ofNullable(value.findValue("managerBackOffice")).ifPresent(e -> {
            _newPlanShipmentItem.setManagerBackOffice(e.asText());
        });
        Optional.ofNullable(value.findValue("note")).ifPresent(e -> {
            _newPlanShipmentItem.setNote(e.asText());
        });



        return _newPlanShipmentItem;
    }*/



/*

    public List<PlanShipmentItem>  updatesPlanShipment(JsonNode value){
        List<PlanShipmentItem> planShipmentItems = new ArrayList<>();
        Iterator<JsonNode> nodeIterator =  value.findValue("data").findValue("models").elements();
        while(nodeIterator.hasNext()){
            JsonNode valueUpdate = nodeIterator.next();
            PlanShipmentItem _updatePlanShipmentItem  = PlanShipmentItem.find.where().eq("id",valueUpdate.get("id").asInt()).findUnique();
            planShipmentItems.add(updatePlanShipment(valueUpdate,_updatePlanShipmentItem));
        }
        planShipmentItems.stream().forEach(e -> e.save());
        return planShipmentItems;


    }
*/

   /* public PlanShipmentItem updatePlanShipment(JsonNode value,PlanShipmentItem planShipmentItem){

        Optional.ofNullable(value.findValue("deviationShipment")).ifPresent(e -> {
         if (!e.equals(NullNode.getInstance())){
              DeviationShipment deviationShipment =   Optional.ofNullable(find(DeviationShipment.class).where().eq("id", e
                    .findValue("id").asInt())
                    .findUnique()).orElse(null);
               planShipmentItem.setDeviationShipment(deviationShipment);
         }
        });
        Optional.ofNullable(value.findValue("deviationDelivery")).ifPresent(e -> {
            if (!e.equals(NullNode.getInstance())){
                DeviationDelivery deviationDelivery =   Optional.ofNullable(find(DeviationDelivery.class).where().eq("id", e
                    .findValue("id").asInt())
                    .findUnique()).orElse(null);
               planShipmentItem.setDeviationDelivery(deviationDelivery);
            }
        });



        Optional.ofNullable(value.findValue("costTrip")).ifPresent(e -> {
            planShipmentItem.setCostTrip(e.asDouble());
        });
        Optional.ofNullable(value.findValue("note")).ifPresent(e -> {
            planShipmentItem.setNote(e.asText());
        });

        Optional.ofNullable(value.findValue("numberGate")).ifPresent(e -> {
            planShipmentItem.setNumberGate(e.asInt());
        });

        Optional.ofNullable(value.findValue("dateDeliveryFact")).ifPresent(e -> {
            if (!e.equals(NullNode.getInstance())){
            planShipmentItem.setDateDeliveryFact(dateFromStringInFormat_dd_MM_yyyy(e.asText()));
              }
            }
        );

        Optional.ofNullable(value.findValue("transportCompanyPlan")).ifPresent(e -> {
            if (!e.equals(NullNode.getInstance())) {
                planShipmentItem.setTransportCompanyPlan(TransportCompany.find.byId(e.findValue("rowPointer").asText()));
            }
        });
        Optional.ofNullable(value.findValue("transportCompanyFact")).ifPresent(e -> {
            if (!e.equals(NullNode.getInstance())) {
                planShipmentItem.setTransportCompanyFact(TransportCompany.find.byId(e.findValue("rowPointer").asText()));
            }
        });

        Optional.ofNullable(value.get("driverTransportCompany")).ifPresent(e -> {
            if(!e.equals(NullNode.getInstance())){
                DriverTransportCompany driverTransportCompany = DriverTransportCompany.find.where().eq("id",e.get("id").asText()).findUnique();
                planShipmentItem.setDriverTransportCompany(driverTransportCompany);
            }
        });


        Optional.ofNullable(value.get("timeLoad")).ifPresent( e-> {
            if(!e.equals(NullNode.getInstance())){
                DateTime dateTime = DateTime.parse(e.asText()).plusHours(3);
               //DateTime dateTime =   DateTime.parse(e.asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
               LocalTime localTime = LocalTime.of(dateTime.getHourOfDay(),dateTime.getMinuteOfHour());
               planShipmentItem.setTimeLoad(Time.valueOf(localTime));
            }
        });



        Ebean.save(planShipmentItem);


        *//*if(Objects.isNull(planShipmentItem.getDeviationShipment())){

            planShipmentItem.setDeviationShipment(new DeviationShipment(-1,""));
        }
        if(Objects.isNull(planShipmentItem.getDeviationDelivery())){
            planShipmentItem.setDeviationDelivery(new DeviationDelivery(-1,""));
        }*//*

        return  planShipmentItem;
    }
*/

    @NotNull
    private Date dateFromStringInFormat_dd_MM_yyyy(String dateValue) {
        DateTime dateTime;
        if(dateValue.length() < 11){
            org.joda.time.format.DateTimeFormatter formatter =  new org.joda.time.format.DateTimeFormatterBuilder().appendDayOfMonth(2)
                                                                                                         .appendLiteral("-")
                                                                                                         .appendMonthOfYear(2).appendLiteral("-").appendYear(4,4).toFormatter();
            dateTime = DateTime.parse(dateValue, formatter);
        }else {
            dateTime = DateTime.parse(dateValue);
        }
        LocalDate localDate = LocalDate.of(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth());
        //Date.valueOf(LocalDate.parse(dateValue, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return Date.valueOf(localDate);
    }


    public Integer deletePlandDayItem(JsonNode valueDelete, DstlProfile dstlProfile) {
        Iterator<JsonNode> nodeIterator =  valueDelete.get("data").get("models").elements();
         //  Iterator<JsonNode> nodeIterator =  valueDelete.get("models").elements();

        int i = 0;
        while(nodeIterator.hasNext()){

            JsonNode _delete = nodeIterator.next();
            //PlanShipmentItem planShipmentItem =  PlanShipmentItem.find.where().eq("id",_delete.get("id").asInt()).findUnique();
            List<PlanShipmentItem> planShipmentItems =  PlanShipmentItem.find.where().eq("senderEnterprise.id",_delete.get("senderEnterprise").get("id").asInt())
                                          .eq("numberDispatcher",_delete.get("numberDispatcher").textValue()).findList();
            i = planShipmentItems.size();
            planShipmentItems.stream().forEach(e -> Ebean.delete(e));

        }

        //PlanShipmentItem planShipmentItem =  Ebean.find(PlanShipmentItem.class,id);
        return i;
    }




}
