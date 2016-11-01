package services;

import model.plan.JournalShipment;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gukov on 01.11.2016.
 */
public class PlanDayService {

    public List<JournalShipment> journalShipmentList(){

        List<JournalShipment> _journalShipments = new ArrayList<>();
        JournalShipment journalShipment = new JournalShipment();
        journalShipment.setId(1L);
        journalShipment.setSenderEnterprise("ГОТЭК");
        journalShipment.setKindShipment("Доставка");
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
        journalShipment1.setKindShipment("Самовывоз");
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




}
