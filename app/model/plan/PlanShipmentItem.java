package model.plan;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.JsonIgnore;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import com.avaje.ebean.config.JsonConfig;
import com.fasterxml.jackson.annotation.JsonFormat;
import model.*;
import org.joda.time.DateTime;
import play.db.NamedDatabase;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Gukov on 02.11.2016.
 */
@Entity
@Table(name = "GTK_DSTL_PlanShipmentItem")
public class PlanShipmentItem  extends Model {

    public static final Finder<Long,PlanShipmentItem> find = new Finder<Long,PlanShipmentItem>(PlanShipmentItem.class);

    @Id
    @Column(name = "ID")
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "IDRequestTransport")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private PlanRequestTransport planRequestTransport;



    @Column(name = "ExistInStore")
    private Boolean existInStore;
    @Column(name = "DateToStore")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm",
            timezone="Europe/Moscow")
    private Timestamp dateToStore;

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
    private Integer seqCustomer;

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

    @Column(name = "DistanceDeliver")
    private Double distanceDelivery;
    @Column(name = "CostDelivery")
    private Double costTrip;




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

    public PlanRequestTransport getPlanRequestTransport() {
        return planRequestTransport;
    }

    public void setPlanRequestTransport(PlanRequestTransport planRequestTransport) {
        this.planRequestTransport = planRequestTransport;
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

    public Integer getSeqCustomer() {
        return seqCustomer;
    }

    public void setSeqCustomer(Integer seqCustomer) {
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
