package controllers;

import auth.AuthService;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by Gukov on 22.11.2016.
 */
public class PlanLoadController extends Controller {
    @Inject
    WebJarAssets webJarAssets;


    @Inject
    private AuthService authService;


    public  Result show() {
        return play.mvc.Results.TODO;
    }
}
