package controllers;

import akka.stream.impl.io.OutputStreamSourceStage;
import auth.AuthService;
import model.DriverTransportCompany;
import play.libs.Json;
import play.mvc.Controller;

import play.mvc.Result;
import services.HelperServices;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class HelperTransportCompanyController extends Controller {
    @Inject
    WebJarAssets webJarAssets;

    @Inject
    private AuthService authService;


    @Inject
    private HelperServices helperServices;

    public Result selectDriverByTransportCompany(String refcompany) {

        List<DriverTransportCompany> driverTransportCompanies =helperServices.driverTransportCompany(refcompany);

        return ok(Json.toJson(driverTransportCompanies));
    }

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