package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Zhukov on 22.10.2016.
 */

public class DeviationDelivery extends Deviation {

    public DeviationDelivery(int id, String decription) {
        super(id,decription,"Доставка" );
    }

}
