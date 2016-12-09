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
        journalShipment.setCostTrip(24000D);
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
        journalShipment1.setCostTrip(24030D);
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
        journalShipmentP.setCostTrip(34780D);
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
        journalShipmentSp.setCostTrip(45000D);
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
        }

        PlanRequestTransport planRequestTransports =  _temp.stream().distinct().findFirst().get();
        // TODO: 08.12.2016  Выбрать данные из источника по номеру заявки и предприятию

        List<PlanShipmentItem>  _planShipmentItems = journalShipmentList(nameServiceDstl)
                                                     .stream()
                                                     .filter(e -> e.getSenderEnterprise().equals(_enterprise) && e.getNumberDispatcher().equals(planRequestTransports.getNumberDispatcher()))
                                                     .map(this::createPlanShipmentItem)
                                                     .collect(toList());

        planRequestTransports.getPlanShipmentItems().addAll(_planShipmentItems);

        planShipment.getPlanRequestTransports().add(planRequestTransports);


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
    public PlanShipmentItem createPlanShipmentItem(JournalShipment journalShipment){
        PlanShipmentItem _temp = new PlanShipmentItem();
        _temp.setDateToStore(journalShipment.getDateToStore());
        _temp.setExistInStore(journalShipment.getExistInStore());
        _temp.setSizeOrder(journalShipment.getSizeOrder());
        _temp.setCodeCustomer(journalShipment.getCodeCustomer());
        _temp.setCostTrip(journalShipment.getCostTrip());
        _temp.setCountPlace(journalShipment.getCountPlace());
        _temp.setDistanceDelivery(journalShipment.getDistanceDelivery());
        _temp.setLineOrder(journalShipment.getLineOrder());
        _temp.setNameCustomer(journalShipment.getNameCustomer());
        _temp.setNameOrder(journalShipment.getNameOrder());
        _temp.setNumberOrder(journalShipment.getNumberOrder());
        _temp.setNumberItem(journalShipment.getNumberItem());
        _temp.setPlaceDelivery(journalShipment.getPlaceDelivery());

        _temp.setSizePallet(journalShipment.getSizePallet());
        return  _temp;
    }





    public List<PlanRequestTransport>  updatesPlanShipment(JsonNode value){
        List<PlanRequestTransport> planRequestTransports = new ArrayList<>();
        Iterator<JsonNode> nodeIterator =  value.findValue("data").findValue("models").elements();
        while(nodeIterator.hasNext()){
            JsonNode valueUpdate = nodeIterator.next();
            PlanRequestTransport _updatePlanRequestTransport  = PlanRequestTransport.find.where().eq("id",valueUpdate.get("id").asInt()).findUnique();
            planRequestTransports.add(updatePlanShipment(valueUpdate,_updatePlanRequestTransport));
        }
        planRequestTransports.stream().forEach(e -> e.save());
        return planRequestTransports;


    }


    public PlanRequestTransport updatePlanShipment(JsonNode value,PlanRequestTransport planRequestTransport){

        Optional.ofNullable(value.findValue("deviationShipment")).ifPresent(e -> {
         if (!e.equals(NullNode.getInstance())){
              DeviationShipment deviationShipment =   Optional.ofNullable(find(DeviationShipment.class).where().eq("id", e
                    .findValue("id").asInt())
                    .findUnique()).orElse(null);
             planRequestTransport.setDeviationShipment(deviationShipment);
         }
        });
        Optional.ofNullable(value.findValue("deviationDelivery")).ifPresent(e -> {
            if (!e.equals(NullNode.getInstance())){
                DeviationDelivery deviationDelivery =   Optional.ofNullable(find(DeviationDelivery.class).where().eq("id", e
                    .findValue("id").asInt())
                    .findUnique()).orElse(null);
                planRequestTransport.setDeviationDelivery(deviationDelivery);
            }
        });



       /* Optional.ofNullable(value.findValue("costTrip")).ifPresent(e -> {
            planRequestTransport.setCostTrip(e.asDouble());
        });*/
        Optional.ofNullable(value.findValue("note")).ifPresent(e -> {
            planRequestTransport.setNote(e.asText());
        });

       /* Optional.ofNullable(value.findValue("numberGate")).ifPresent(e -> {
            planRequestTransport.setNumberGate(e.asInt());
        });*/

        Optional.ofNullable(value.findValue("dateDeliveryFact")).ifPresent(e -> {
            if (!e.equals(NullNode.getInstance())){
                planRequestTransport.setDateDeliveryFact(dateFromStringInFormat_dd_MM_yyyy(e.asText()));
              }
            }
        );

        Optional.ofNullable(value.findValue("transportCompanyPlan")).ifPresent(e -> {
            if (!e.equals(NullNode.getInstance())) {
                planRequestTransport.setTransportCompanyPlan(TransportCompany.find.byId(e.findValue("rowPointer").asText()));
            }
        });
        Optional.ofNullable(value.findValue("transportCompanyFact")).ifPresent(e -> {
            if (!e.equals(NullNode.getInstance())) {
                planRequestTransport.setTransportCompanyFact(TransportCompany.find.byId(e.findValue("rowPointer").asText()));
            }
        });

        Optional.ofNullable(value.get("driverTransportCompany")).ifPresent(e -> {
            if(!e.equals(NullNode.getInstance())){
                DriverTransportCompany driverTransportCompany = DriverTransportCompany.find.where().eq("id",e.get("id").asText()).findUnique();
                planRequestTransport.setDriverTransportCompany(driverTransportCompany);
            }
        });


        Optional.ofNullable(value.get("timeLoad")).ifPresent( e-> {
            if(!e.equals(NullNode.getInstance())){
                DateTime dateTime = DateTime.parse(e.asText()).plusHours(3);
               //DateTime dateTime =   DateTime.parse(e.asText(), DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
               LocalTime localTime = LocalTime.of(dateTime.getHourOfDay(),dateTime.getMinuteOfHour());
                planRequestTransport.setTimeLoad(Time.valueOf(localTime));
            }
        });
        Optional.ofNullable(value.get("planShipmentItems")).ifPresent( e-> {

            Iterator<JsonNode> temp = value.get("planShipmentItems").elements();
            while (temp.hasNext()){
                JsonNode jsonNode = temp.next();
                planRequestTransport.getPlanShipmentItems().stream()
                                                           .filter(p -> p.getId().equals((jsonNode.findValue("id").asLong())))
                                                           .findFirst()
                                                           .get().setCostTrip(jsonNode.findValue("costTrip").asDouble());
            }



        });


        return  planRequestTransport;
    }


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
            List<PlanRequestTransport> planRequestTransports =  PlanRequestTransport.find.where().eq("senderEnterprise.id",_delete.get("senderEnterprise").get("id").asInt())
                                          .eq("numberDispatcher",_delete.get("numberDispatcher").textValue()).findList();
            i = planRequestTransports.size();
            planRequestTransports.stream().forEach(e -> Ebean.delete(e));

        }

        //PlanShipmentItem planShipmentItem =  Ebean.find(PlanShipmentItem.class,id);
        return i;
    }




}
