package services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import javaslang.control.Try;
import model.Enterprise;

import java.util.Optional;

/**
 * Created by Zhukov on 02.11.2016.
 */
public class DstlService {

    public Enterprise getEnterprise(String companyName){
        return Ebean.createQuery(Enterprise.class).where()
                    .eq("name",companyName).findUnique();
    }

}
