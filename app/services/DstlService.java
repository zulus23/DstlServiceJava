package services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import javaslang.control.Try;
import model.Enterprise;
import model.NormaTimeLoading;
import model.TypePacking;

import java.util.Optional;

/**
 * Created by Zhukov on 02.11.2016.
 */
public class DstlService {

    public Enterprise getEnterprise(String companyName){
        return Ebean.createQuery(Enterprise.class).where()
                    .eq("name",companyName).findUnique();
    }

    public Integer timeForLoadingByTypePacking(String typePacking,Enterprise enterprise){
        Integer timeLoad = 0;
         NormaTimeLoading normaTimeLoading =  Ebean.find(NormaTimeLoading.class).where().eq("enterprise",enterprise).findUnique();


        if (typePacking.equals(TypePacking.PACKING.getNameTypePacking())){
          timeLoad = normaTimeLoading.getPackageTime();
        }
        if (typePacking.equals(TypePacking.COMMISSION.getNameTypePacking())){
            timeLoad = normaTimeLoading.getCommissionTime();
        }
        if (typePacking.equals(TypePacking.PLACER.getNameTypePacking())){
            timeLoad = normaTimeLoading.getPlacerTime();
        }

        return    timeLoad;
    }

}
