package model.plan;

import javax.persistence.DiscriminatorValue;

/**
 * Created by Gukov on 06.12.2016.
 */
@DiscriminatorValue(value = "customer")
public class PlanLoadCutomerItem  extends BaseItem{

    public final static Finder<Integer,PlanLoadCutomerItem> find = new Finder<Integer, PlanLoadCutomerItem>(PlanLoadCutomerItem.class);


}
