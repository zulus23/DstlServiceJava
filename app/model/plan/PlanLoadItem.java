package model.plan;

/**
 * Created by Gukov on 06.12.2016.
 */

import com.avaje.ebean.Model;

import javax.persistence.DiscriminatorValue;
import java.sql.Date;


public class PlanLoadItem extends Model {

    private Integer id;
    private PlanLoad planLoad;
    private PlanShipmentItem shipmentItem;
    private Date dateBeginLoad;
    private Date dateEndLoad;



    public final static Finder<Integer,PlanLoadItem> find = new Finder<Integer, PlanLoadItem>(PlanLoadItem.class);


}
