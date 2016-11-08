package model;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.UUID;

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
    @Column(name = "RefToSL")
    protected UUID refToSL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }


    public UUID getRefToSL() {
        return refToSL;
    }

    public void setRefToSL(UUID refToSL) {
        this.refToSL = refToSL;
    }
}
