package controllers;

import auth.AuthService;
import com.google.inject.Inject;
import model.Enterprise;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.HelperServices;
import utils.DbUtils;

import java.util.Base64;
import java.util.Optional;

import static utils.DbUtils.enterpriseFromUser;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class HelperController extends Controller{

    @Inject
    private HelperServices helperServices;

    @Inject
    private AuthService authService;

    public Result deviations() {
        return ok(Json.toJson(helperServices.listDeviation()));
    }


    public Result serviceDstl() {
        return ok(Json.toJson(helperServices.serviceDstl()));
    }

    public Result listEnterprise(){
        return ok(Json.toJson(helperServices.listEnterprise(Optional.ofNullable(DbUtils.enterpriseFromUser(authService.nameServiceDstl())))));
    }

}
