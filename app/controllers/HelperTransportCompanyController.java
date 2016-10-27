package controllers;

import auth.AuthService;
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

  public Result show(){
      return  ok(views.html.helper.helpTransportCompany.render("Транспортные компании",webJarAssets,authService.isLoggedIn(),authService.getUserInfo().orElse(null)));
  }
}