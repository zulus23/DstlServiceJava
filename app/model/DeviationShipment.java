package model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Zhukov on 22.10.2016.
 */
@Entity
@DiscriminatorValue(value = "Отгрузка")
public class DeviationShipment extends BaseDeviation {

    public DeviationShipment() {
    }

    public DeviationShipment(Integer id,String description) {
        super();
        this.setId(id);
        this.setDescription(description);
    }
}
