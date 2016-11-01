package model.plan;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Gukov on 01.11.2016.
 */
public class JournalShipment {

    private Long id;
    private String senderEnterprise;
    private String kindShipment;
    private String numberDispatcher;
    private Boolean inPlanDay;
    private String dateCreateDispatcher;
    private String dateShipmentDispatcher;
    private String dateDeliveryDispatcher;
    private Boolean existInStore;
    private String dateToStore;
    private String placeLoading;
    private String statusDispatcher;
    private String numberOrder;
    private String numberItem;
    private String nameOrder;
    private String nameCustomer;
    private String codeCustomer;
    //private String consignee; //грузополучатель
    private String placeDelivery;
    private Integer sizeOrder;
    private String sizePallet;
    private String packingMethod;
    private Integer countPlace;
    private Integer capacityOrder;
    private String typeTransport;
    private String managerBackOffice;
    private String note;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderEnterprise() {
        return senderEnterprise;
    }

    public void setSenderEnterprise(String senderEnterprise) {
        this.senderEnterprise = senderEnterprise;
    }

    public String getKindShipment() {
        return kindShipment;
    }

    public void setKindShipment(String kindShipment) {
        this.kindShipment = kindShipment;
    }

    public String getNumberDispatcher() {
        return numberDispatcher;
    }

    public void setNumberDispatcher(String numberDispatcher) {
        this.numberDispatcher = numberDispatcher;
    }

    public Boolean getInPlanDay() {
        return inPlanDay;
    }

    public void setInPlanDay(Boolean inPlanDay) {
        this.inPlanDay = inPlanDay;
    }

    public String getDateCreateDispatcher() {
        return dateCreateDispatcher;
    }

    public void setDateCreateDispatcher(String dateCreateDispatcher) {
        this.dateCreateDispatcher = dateCreateDispatcher;
    }

    public String getDateShipmentDispatcher() {
        return dateShipmentDispatcher;
    }

    public void setDateShipmentDispatcher(String dateShipmentDispatcher) {
        this.dateShipmentDispatcher = dateShipmentDispatcher;
    }

    public String getDateDeliveryDispatcher() {
        return dateDeliveryDispatcher;
    }

    public void setDateDeliveryDispatcher(String dateDeliveryDispatcher) {
        this.dateDeliveryDispatcher = dateDeliveryDispatcher;
    }

    public Boolean getExistInStore() {
        return existInStore;
    }

    public void setExistInStore(Boolean existInStore) {
        this.existInStore = existInStore;
    }

    public String getDateToStore() {
        return dateToStore;
    }

    public void setDateToStore(String dateToStore) {
        this.dateToStore = dateToStore;
    }

    public String getPlaceLoading() {
        return placeLoading;
    }

    public void setPlaceLoading(String placeLoading) {
        this.placeLoading = placeLoading;
    }

    public String getStatusDispatcher() {
        return statusDispatcher;
    }

    public void setStatusDispatcher(String statusDispatcher) {
        this.statusDispatcher = statusDispatcher;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getNumberItem() {
        return numberItem;
    }

    public void setNumberItem(String numberItem) {
        this.numberItem = numberItem;
    }

    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getCodeCustomer() {
        return codeCustomer;
    }

    public void setCodeCustomer(String codeCustomer) {
        this.codeCustomer = codeCustomer;
    }

  /*  public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
*/
    public String getPlaceDelivery() {
        return placeDelivery;
    }

    public void setPlaceDelivery(String placeDelivery) {
        this.placeDelivery = placeDelivery;
    }

    public Integer getSizeOrder() {
        return sizeOrder;
    }

    public void setSizeOrder(Integer sizeOrder) {
        this.sizeOrder = sizeOrder;
    }

    public String getSizePallet() {
        return sizePallet;
    }

    public void setSizePallet(String sizePallet) {
        this.sizePallet = sizePallet;
    }

    public String getPackingMethod() {
        return packingMethod;
    }

    public void setPackingMethod(String packingMethod) {
        this.packingMethod = packingMethod;
    }

    public Integer getCountPlace() {
        return countPlace;
    }

    public void setCountPlace(Integer countPlace) {
        this.countPlace = countPlace;
    }

    public Integer getCapacityOrder() {
        return capacityOrder;
    }

    public void setCapacityOrder(Integer capacityOrder) {
        this.capacityOrder = capacityOrder;
    }

    public String getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(String typeTransport) {
        this.typeTransport = typeTransport;
    }

    public String getManagerBackOffice() {
        return managerBackOffice;
    }

    public void setManagerBackOffice(String managerBackOffice) {
        this.managerBackOffice = managerBackOffice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JournalShipment)) return false;

        JournalShipment that = (JournalShipment) o;

        return getId().equals(that.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}