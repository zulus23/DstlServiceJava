package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by Zhukov on 22.10.2016.
 */
@Entity
@Table(name = "gtk_PlanShipment")
public class PlanShipment extends Model{

   private int id;
   private String name;
   private Date planDate;


    public PlanShipment(int id, String name,Date planDate){
        this.id = id;
        this.name = name;
        this.planDate = planDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }
}
