package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Gukov on 25.11.2016.
 */
public class PlanShipmentForLoadPlan {

    private Long id;
    private Date datePlan;
    private String enterprise;
    private String typeShipment;
    private String numberDispatcher;
    private Date dateShipmentDispatcher;
    private Boolean existInStore;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm")

    private Timestamp dateToStore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(Date datePlan) {
        this.datePlan = datePlan;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getTypeShipment() {
        return typeShipment;
    }

    public void setTypeShipment(String typeShipment) {
        this.typeShipment = typeShipment;
    }

    public String getNumberDispatcher() {
        return numberDispatcher;
    }

    public void setNumberDispatcher(String numberDispatcher) {
        this.numberDispatcher = numberDispatcher;
    }

    public Date getDateShipmentDispatcher() {
        return dateShipmentDispatcher;
    }

    public void setDateShipmentDispatcher(Date dateShipmentDispatcher) {
        this.dateShipmentDispatcher = dateShipmentDispatcher;
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
}
