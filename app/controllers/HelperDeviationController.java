package controllers;

import auth.AuthService;
import play.mvc.Controller;
import play.mvc.Result;
import services.HelperServices;

import javax.inject.Inject;

/**
 * Created by Gukov on 26.10.2016.
 */
public class HelperDeviationController extends Controller {
    @Inject
    WebJarAssets webJarAssets;

    @Inject
    private AuthService authService;


    @Inject
    private HelperServices helperServices;


    public Result show(){
        return ok(views.html.helper.helperDeviation.render("Класификаторы отклонений",webJarAssets, authService.isLoggedIn(), authService.getUserInfo().orElse(null)));
    }




}
