package model.plan;

import com.avaje.ebean.annotation.CreatedTimestamp;
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
public class PlanShipmentItem {
    @Id
    @Column(name = "ID")
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "IDPLAN")
    private PlanShipment planShipment;
    @ManyToOne
    @JoinColumn(name = "IdEnterprise")
    private Enterprise senderEnterprise;
    @Column(name = "TypeShipment")
    private String typeShipment;
    /*ССылка на план погрузки*/
    private Boolean PlanLoad;
    @Column(name = "DateShipmentDispatcher")
    private Date dateShipmentDispatcher;
    @Column(name = "DeviationShipment")
    private DeviationShipment deviationShipment;
    @Column(name = "DateDeliveryDispatcher")
    private Date dateDeliveryDispatcher;
    @Column(name = "DateDeliveryFact")
    private String dateDeliveryFact;
    @Column(name = "DeviationDelivery")
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
    @Column(name = "UpdatePlan")
    @UpdatedTimestamp
    private Timestamp updatePlanItem;
    @ManyToOne
    @JoinColumn(name = "UpdateByItem")
    private UserDstl userUpdate;


}
