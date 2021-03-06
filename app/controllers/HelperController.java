package controllers;

import auth.AuthService;
import com.google.inject.Inject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.DstlService;
import services.HelperServices;
import utils.DbUtils;

import java.util.Optional;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class HelperController extends Controller{

    @Inject
    private HelperServices helperServices;

    @Inject
    private AuthService authService;

    @Inject
    DstlService dstlService;



    public Result deviations() {
        return ok(Json.toJson(helperServices.listDeviation()));
    }
    public Result deviationShipment() {
        return ok(Json.toJson(helperServices.deviationShipmentList()));
    }
    public  Result deviationDelivery() {
        return ok(Json.toJson(helperServices.deviationDeliveryList()));
    }

    public Result serviceDstl() {
        return ok(Json.toJson(helperServices.serviceDstl()));
    }

    public Result listEnterprise(){
        return ok(Json.toJson(helperServices.listEnterprise(Optional.ofNullable(dstlService.getEnterprise(authService.nameServiceDstl())))));
    }



}
