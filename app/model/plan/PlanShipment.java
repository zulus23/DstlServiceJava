package model.plan;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import com.avaje.ebean.annotation.WhenModified;
import model.Enterprise;
import model.UserDstl;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Gukov on 02.11.2016.
 */
@Entity
@Table(name = "GTK_DSTL_PlanShipment")
public class PlanShipment extends Model {
    @Id
    private Long id;
    private String name;
    @Column(name = "DatePlan")
    private Date datePlan;
    @Transient
    List<PlanShipmentDetail> planShipmentDetails;
    @ManyToOne
    @JoinColumn(name = "idService")
    private Enterprise serviceDstl;

    @Column(name = "CreatePlan")
    @CreatedTimestamp
    private Timestamp createPlan;
    @ManyToOne
    @JoinColumn(name = "CreateBy")
    private UserDstl userCreate;
    @Column(name = "UpdatePlan")
    @UpdatedTimestamp
    private Timestamp updatePlan;
    @ManyToOne
    @JoinColumn(name = "UpdateBy")
    private UserDstl userUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(Date datePlan) {
        this.datePlan = datePlan;
    }

    public List<PlanShipmentDetail> getPlanShipmentDetails() {
        return planShipmentDetails;
    }

    public void setPlanShipmentDetails(List<PlanShipmentDetail> planShipmentDetails) {
        this.planShipmentDetails = planShipmentDetails;
    }

    public Enterprise getServiceDstl() {
        return serviceDstl;
    }

    public void setServiceDstl(Enterprise serviceDstl) {
        this.serviceDstl = serviceDstl;
    }

    public Timestamp  getCreatePlan() {
        return createPlan;
    }

    public void setCreatePlan(Timestamp  createPlan) {
        this.createPlan = createPlan;
    }

    public Timestamp  getUpdatePlan() {
        return updatePlan;
    }

    public void setUpdatePlan(Timestamp  updatePlan) {
        this.updatePlan = updatePlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanShipment)) return false;

        PlanShipment that = (PlanShipment) o;

        return getId().equals(that.getId());

    }



    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
