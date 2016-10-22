package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Zhukov on 22.10.2016.
 */

@Entity
@Table(name = "gtk_DeviationDelivery")
public class DeviationDelivery {

    @Id
    @Column(name = "id")
    private int id;
    private String decription;

    public DeviationDelivery(int id, String decription) {
        this.id = id;
        this.decription = decription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
