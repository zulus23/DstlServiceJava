package model.plan;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.JsonIgnore;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import model.*;
import play.db.NamedDatabase;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Gukov on 02.11.2016.
 */
@Entity
@Table(name = "GTK_DSTL_PlanShipmentItem")
public class PlanShipmentItem  extends Model {
    @Id
    @Column(name = "ID")
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "IDPLAN")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private PlanShipment planShipment;
    @ManyToOne
    @JoinColumn(name = "IdEnterprise")
    private Enterprise senderEnterprise;
    @Column(name = "TypeShipment")
    private String typeShipment;
    /*ССылка на план погрузки*/
    @Transient
    private Boolean PlanLoad;
    @Column(name = "DateShipmentDispatcher")
    private Date dateShipmentDispatcher;
    @ManyToOne
    @JoinColumn(name = "IdDeviationShipment")
    private DeviationShipment deviationShipment;
    @Column(name = "DateDeliveryDispatcher")
    private Date dateDeliveryDispatcher;
    @Column(name = "DateDeliveryFact")
    private String dateDeliveryFact;
    @ManyToOne
    @JoinColumn(name = "IdDeviationDelivery")
    private DeviationDelivery deviationDelivery;
    @Column(name = "ExistInStore")
    private Boolean existInStore;
    @Column(name = "DateToStore")
    private Timestamp dateToStore;
    @Column(name = "PlaceShipment")
    private String placeShipment;
    @Column(name = "StatusDispatcher")
    private String statusDispatcher;
    @Column(name = "NumberOrderDispatcher")
    private String numberDispatcher;
    @Column(name = "DateCreateDispatcher")
    private Timestamp dateCreateDispatcher;
    @Column(name = "Co_Num")
    private String numberOrder;
    @Column(name = "Co_Line")
    private Integer lineOrder;
    @Column(name = "Item")
    private String numberItem;
    @Column(name = "Co_Name")
    private String nameOrder;
    @Column(name = "Cust_Num")
    private String codeCustomer;
    @Column(name = "Cust_Seq")
    private String seqCustomer;

    @Column(name = "Cust_Name")
    private String nameCustomer;

    //private String consignee; //грузополучатель
    @Column(name = "PlaceDelivery")
    private String placeDelivery;
    @Column(name = "Co_Count")
    private Integer sizeOrder;
    @Column(name = "SizePallet")
    private String sizePallet;
    @Column(name = "KindPackage")
    private String packingMethod;
    @Column(name = "CountPlace")
    private Integer countPlace;
    @Column(name = "Co_Capacity")
    private String capacityOrder;
    @Column(name = "TypeTransport")
    private String typeTransport;
    @Column(name = "TimeForLoading")
    private Integer timeToLoad;
    @Column(name = "DeliveryCompanyPlan")
    private TransportCompany transportCompanyPlan;
    @Column(name = "DeliveryCompanyFact")
    private TransportCompany transportCompanyFact;
    @Column(name = "Driver")
    private DriverTransportCompany driverTransportCompany;
    @Column(name = "NumberGate")
    private Integer numberGate;
    @Column(name = "DistanceDeliver")
    private Double distanceDelivery;
    @Column(name = "CostDelivery")
    private Double costTrip;
    @Column(name = "TimeLoading")
    private Timestamp  timeLoad;
    @Column(name = "BackManager")
    private String managerBackOffice;
    @Column(name = "Note")
    private String note;




    @Column(name = "CreatePlanItem")
    @CreatedTimestamp
    private Timestamp createPlanItem;
    @ManyToOne
    @JoinColumn(name = "CreateBy")
    private UserDstl userCreate;
    @Column(name = "UpdatePlanItem")
    @UpdatedTimestamp
    private Timestamp updatePlanItem;
    @ManyToOne
    @JoinColumn(name = "UpdateBy")
    private UserDstl userUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanShipment getPlanShipment() {
        return planShipment;
    }

    public void setPlanShipment(PlanShipment planShipment) {
        this.planShipment = planShipment;
    }

    public Enterprise getSenderEnterprise() {
        return senderEnterprise;
    }

    public void setSenderEnterprise(Enterprise senderEnterprise) {
        this.senderEnterprise = senderEnterprise;
    }

    public String getTypeShipment() {
        return typeShipment;
    }

    public void setTypeShipment(String typeShipment) {
        this.typeShipment = typeShipment;
    }

    public Boolean getPlanLoad() {
        return PlanLoad;
    }

    public void setPlanLoad(Boolean planLoad) {
        PlanLoad = planLoad;
    }

    public Date getDateShipmentDispatcher() {
        return dateShipmentDispatcher;
    }

    public void setDateShipmentDispatcher(Date dateShipmentDispatcher) {
        this.dateShipmentDispatcher = dateShipmentDispatcher;
    }

    public DeviationShipment getDeviationShipment() {
        return deviationShipment;
    }

    public void setDeviationShipment(DeviationShipment deviationShipment) {
        this.deviationShipment = deviationShipment;
    }

    public Date getDateDeliveryDispatcher() {
        return dateDeliveryDispatcher;
    }

    public void setDateDeliveryDispatcher(Date dateDeliveryDispatcher) {
        this.dateDeliveryDispatcher = dateDeliveryDispatcher;
    }

    public String getDateDeliveryFact() {
        return dateDeliveryFact;
    }

    public void setDateDeliveryFact(String dateDeliveryFact) {
        this.dateDeliveryFact = dateDeliveryFact;
    }

    public DeviationDelivery getDeviationDelivery() {
        return deviationDelivery;
    }

    public void setDeviationDelivery(DeviationDelivery deviationDelivery) {
        this.deviationDelivery = deviationDelivery;
    }

    public Boolean getExistInStore() {
        return existInStore;
    }

    public void setExistInStore(Boolean existInStore) {
        this.existInStore = existInStore;
    }

    public Timestamp getDateToStore() {
        return dateToStore;
    }

    public void setDateToStore(Timestamp dateToStore) {
        this.dateToStore = dateToStore;
    }

    public String getPlaceShipment() {
        return placeShipment;
    }

    public void setPlaceShipment(String placeShipment) {
        this.placeShipment = placeShipment;
    }

    public String getStatusDispatcher() {
        return statusDispatcher;
    }

    public void setStatusDispatcher(String statusDispatcher) {
        this.statusDispatcher = statusDispatcher;
    }

    public String getNumberDispatcher() {
        return numberDispatcher;
    }

    public void setNumberDispatcher(String numberDispatcher) {
        this.numberDispatcher = numberDispatcher;
    }

    public Timestamp getDateCreateDispatcher() {
        return dateCreateDispatcher;
    }

    public void setDateCreateDispatcher(Timestamp dateCreateDispatcher) {
        this.dateCreateDispatcher = dateCreateDispatcher;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public Integer getLineOrder() {
        return lineOrder;
    }

    public void setLineOrder(Integer lineOrder) {
        this.lineOrder = lineOrder;
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

    public String getCodeCustomer() {
        return codeCustomer;
    }

    public void setCodeCustomer(String codeCustomer) {
        this.codeCustomer = codeCustomer;
    }

    public String getSeqCustomer() {
        return seqCustomer;
    }

    public void setSeqCustomer(String seqCustomer) {
        this.seqCustomer = seqCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
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

    public String getCapacityOrder() {
        return capacityOrder;
    }

    public void setCapacityOrder(String capacityOrder) {
        this.capacityOrder = capacityOrder;
    }

    public String getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(String typeTransport) {
        this.typeTransport = typeTransport;
    }

    public Integer getTimeToLoad() {
        return timeToLoad;
    }

    public void setTimeToLoad(Integer timeToLoad) {
        this.timeToLoad = timeToLoad;
    }

    public TransportCompany getTransportCompanyPlan() {
        return transportCompanyPlan;
    }

    public void setTransportCompanyPlan(TransportCompany transportCompanyPlan) {
        this.transportCompanyPlan = transportCompanyPlan;
    }

    public TransportCompany getTransportCompanyFact() {
        return transportCompanyFact;
    }

    public void setTransportCompanyFact(TransportCompany transportCompanyFact) {
        this.transportCompanyFact = transportCompanyFact;
    }

    public DriverTransportCompany getDriverTransportCompany() {
        return driverTransportCompany;
    }

    public void setDriverTransportCompany(DriverTransportCompany driverTransportCompany) {
        this.driverTransportCompany = driverTransportCompany;
    }

    public Integer getNumberGate() {
        return numberGate;
    }

    public void setNumberGate(Integer numberGate) {
        this.numberGate = numberGate;
    }

    public Double getDistanceDelivery() {
        return distanceDelivery;
    }

    public void setDistanceDelivery(Double distanceDelivery) {
        this.distanceDelivery = distanceDelivery;
    }

    public Double getCostTrip() {
        return costTrip;
    }

    public void setCostTrip(Double costTrip) {
        this.costTrip = costTrip;
    }

    public Timestamp getTimeLoad() {
        return timeLoad;
    }

    public void setTimeLoad(Timestamp timeLoad) {
        this.timeLoad = timeLoad;
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

    public Timestamp getCreatePlanItem() {
        return createPlanItem;
    }

    public void setCreatePlanItem(Timestamp createPlanItem) {
        this.createPlanItem = createPlanItem;
    }

    public UserDstl getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(UserDstl userCreate) {
        this.userCreate = userCreate;
    }

    public Timestamp getUpdatePlanItem() {
        return updatePlanItem;
    }

    public void setUpdatePlanItem(Timestamp updatePlanItem) {
        this.updatePlanItem = updatePlanItem;
    }

    public UserDstl getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(UserDstl userUpdate) {
        this.userUpdate = userUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanShipmentItem)) return false;

        PlanShipmentItem that = (PlanShipmentItem) o;

        return getId().equals(that.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
