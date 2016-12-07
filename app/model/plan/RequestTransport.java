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

/**
 * Created by Gukov on 07.12.2016.
 */
public class RequestTransport extends Model{
    public static final Model.Finder<Long,RequestTransport> find = new Model.Finder<Long,RequestTransport>(RequestTransport.class);

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




}
