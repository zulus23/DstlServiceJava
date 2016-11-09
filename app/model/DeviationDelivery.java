package model;

import javax.persistence.*;

/**
 * Created by Zhukov on 22.10.2016.
 */
@Entity
@DiscriminatorValue(value = "Доставка")
public class DeviationDelivery extends BaseDeviation {

    public DeviationDelivery() {
    }

    public DeviationDelivery(Integer id,String description) {
        super();
        this.setId(id);
        this.setDescription(description);
    }

}
