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
         Optional<NormaTimeLoading> normaTimeLoading =  Optional.ofNullable(Ebean.find(NormaTimeLoading.class).where().eq("enterprise",enterprise).findUnique());

        timeLoad =  normaTimeLoading.map(e -> {
            int _timeLoad = 0;
            if (typePacking.equals(TypePacking.PACKING.getNameTypePacking())) {
                _timeLoad =  e.getPackageTime();
            }
            if (typePacking.equals(TypePacking.COMMISSION.getNameTypePacking())) {
                _timeLoad =  e.getCommissionTime();
            }
            if (typePacking.equals(TypePacking.PLACER.getNameTypePacking())) {
                _timeLoad =  e.getPlacerTime();
            }
            return _timeLoad;
        }).orElse(0);
        return    timeLoad;
    }

}
