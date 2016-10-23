package controllers;

import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class JournalShipmentController {
    @Inject
    WebJarAssets webJarAssets;


    public Result show() {
        return ok(views.html.planShipment.render("",webJarAssets));
    }
}
