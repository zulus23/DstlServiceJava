package controllers;

import play.mvc.Controller;
import play.mvc.Result;


import javax.inject.Inject;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class ApplicationController extends Controller {

    @Inject
    WebJarAssets webJarAssets;

    public Result index() {

        return ok(views.html.index.render("Your new application is ready.",webJarAssets));
    }


}
