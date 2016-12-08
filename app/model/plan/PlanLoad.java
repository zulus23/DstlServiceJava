package model.plan;

import com.avaje.ebean.Model;

/**
 * Created by Gukov on 06.12.2016.
 */


public class PlanLoad extends Model {

   private  Long id;


   public static final Finder<Integer,PlanLoad> find = new Finder<Integer, PlanLoad>(PlanLoad.class);


}
