package model.plan;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import model.DriverTransportCompany;
import model.Enterprise;
import model.TransportCompany;
import model.UserDstl;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Gukov on 07.12.2016.
 */

@Entity
@Table(name = "GTK_DSTL_PlanShipmentRequestTransport")

public class PlanRequestTransport extends Model{
    public static final Model.Finder<Long,PlanRequestTransport> find = new Model.Finder<Long,PlanRequestTransport>(PlanRequestTransport.class);

    @Id
    @Column(name = "ID")
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "IDPLAN")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private PlanShipment planShipment;
    @ManyToOne
    @JoinColumn(name = "IdEnterprise")
    private Enterprise senderEnterprise;
    @Column(name = "TypeShipment")
    private String typeShipment;

    @Column(name = "PlaceShipment")
    private String placeShipment;
    @Column(name = "StatusDispatcher")
    private String statusDispatcher;
    @Column(name = "NumberOrderDispatcher")
    private String numberDispatcher;
    @Column(name = "DateCreateDispatcher")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm",
            timezone="Europe/Moscow")
    private Timestamp dateCreateDispatcher;

    @Column(name = "TypeTransport")
    private String typeTransport;
    @Column(name = "TimeForLoading")
    private Integer timeToLoad;
    @ManyToOne
    @JoinColumn(name = "DeliveryCompanyPlan")
    private TransportCompany transportCompanyPlan;

    @ManyToOne
    @JoinColumn(name = "DeliveryCompanyFact")
    private TransportCompany transportCompanyFact;
    @ManyToOne
    @JoinColumn(name = "Driver")
    private DriverTransportCompany driverTransportCompany;

    @Column(name = "TimeLoading")
    @Temporal(TemporalType.TIME)
    private Time timeLoad;
    @Column(name = "BackManager")
    private String managerBackOffice;
    @Column(name = "Note")
    private String note;

    @OneToMany(mappedBy = "planShipment",cascade = CascadeType.ALL)
    private List<PlanShipmentItem> planShipmentItems;


    @Column(name = "CreateRequestTransport")
    @CreatedTimestamp
    private Timestamp createRequestTransport;
    @ManyToOne
    @JoinColumn(name = "CreateBy")
    private UserDstl userCreate;
    @Column(name = "UpdateRequestTransport")
    @UpdatedTimestamp
    private Timestamp updateRequestTransport;
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

    public Time getTimeLoad() {
        return timeLoad;
    }

    public void setTimeLoad(Time timeLoad) {
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

    public List<PlanShipmentItem> getPlanShipmentItems() {
        return planShipmentItems;
    }

    public void setPlanShipmentItems(List<PlanShipmentItem> planShipmentItems) {
        this.planShipmentItems = planShipmentItems;
    }

    public Timestamp getCreateRequestTransport() {
        return createRequestTransport;
    }

    public void setCreateRequestTransport(Timestamp createRequestTransport) {
        this.createRequestTransport = createRequestTransport;
    }

    public UserDstl getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(UserDstl userCreate) {
        this.userCreate = userCreate;
    }

    public Timestamp getUpdateRequestTransport() {
        return updateRequestTransport;
    }

    public void setUpdateRequestTransport(Timestamp updateRequestTransport) {
        this.updateRequestTransport = updateRequestTransport;
    }

    public UserDstl getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(UserDstl userUpdate) {
        this.userUpdate = userUpdate;
    }
}
