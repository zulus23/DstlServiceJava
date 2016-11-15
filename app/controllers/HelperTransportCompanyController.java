package controllers;

import auth.AuthService;
import play.libs.Json;
import play.mvc.Controller;

import play.mvc.Result;
import services.HelperServices;

import javax.inject.Inject;

public class HelperTransportCompanyController extends Controller {
    @Inject
    WebJarAssets webJarAssets;

    @Inject
    private AuthService authService;


    @Inject
    private HelperServices helperServices;

    public  Result selectTransportCompanyByEnterprise(String enterprise) {
        return ok(Json.toJson(helperServices.transportCompanyByEnterprise(enterprise)));
    }

    public Result show(){
      return  ok(views.html.helper.helpTransportCompany.render("Транспортные компании",webJarAssets, authService.isLoggedIn(), authService.getUserInfo().orElse(null)));
  }

    public Result index(){

        return ok(Json.toJson(helperServices.driverTransportCompanyList()));
    }
}