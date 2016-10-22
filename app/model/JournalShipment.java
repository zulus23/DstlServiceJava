package model;

import java.sql.Date;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class JournalShipment {
    private Long id;
    private String kindShipment;
    private String numberDispatcher;
    private Date dateCreateDispatcher;
    private Date dateShipDispatcher;
    private Date dateDeliveryDispatcher;
    private Boolean existInStore;
    private Date dateToStore;
    private String placeLoading;
    private String statusDispatcher;
    private String numberOrder;
    private String numberItem;
    private String nameOrder;
    /*Грузополучатель*/
    private String consignee;
    private String placeDelivery;
    private Integer sizeOrder;
    private String sizePallet;
    private String packingMethod;
    private Integer countPlace;
    private Integer sizeOrder1;
    private String typeTransport;
    private String managerBackOffice;
    private String note;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateCreateDispatcher() {
        return dateCreateDispatcher;
    }

    public void setDateCreateDispatcher(Date dateCreateDispatcher) {
        this.dateCreateDispatcher = dateCreateDispatcher;
    }

    public Date getDateShipDispatcher() {
        return dateShipDispatcher;
    }

    public void setDateShipDispatcher(Date dateShipDispatcher) {
        this.dateShipDispatcher = dateShipDispatcher;
    }

    public Date getDateDeliveryDispatcher() {
        return dateDeliveryDispatcher;
    }

    public void setDateDeliveryDispatcher(Date dateDeliveryDispatcher) {
        this.dateDeliveryDispatcher = dateDeliveryDispatcher;
    }

    public Boolean getExistInStore() {
        return existInStore;
    }

    public void setExistInStore(Boolean existInStore) {
        this.existInStore = existInStore;
    }

    public Date getDateToStore() {
        return dateToStore;
    }

    public void setDateToStore(Date dateToStore) {
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

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

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

    public Integer getSizeOrder1() {
        return sizeOrder1;
    }

    public void setSizeOrder1(Integer sizeOrder1) {
        this.sizeOrder1 = sizeOrder1;
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
}
