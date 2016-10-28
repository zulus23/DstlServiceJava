package model;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Zhukov on 22.10.2016.
 */


public class DeviationShipment extends Deviation {

    public DeviationShipment(int id, String decription) {
        super(id,decription,"Отгрузка");

    }

}
