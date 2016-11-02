package model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Gukov on 28.10.2016.
 *
 */
@Entity
@Table(name = "GTK_DSTL_Deviation")
@DiscriminatorColumn(name = "TypeDeviation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public  abstract class BaseDeviation  extends Model {
    @Id
    @Column(name = "ID")
    protected int id;
    @Column(name = "Description")
    protected String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDecription() {
        return description;
    }

    public void setDecription(String decription) {
        this.description = decription;
    }


}
