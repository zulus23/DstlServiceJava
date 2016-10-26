package controllers;

import auth.AuthService;
import org.pac4j.play.store.PlaySessionStore;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by Gukov on 26.10.2016.
 */
public class HelperShipmentController extends Controller {
    @Inject
    WebJarAssets webJarAssets;

    @Inject
    private AuthService authService;



    public  Result show() {

        return ok(views.html.helper.helperShipment.render("Справочники",webJarAssets,authService.isLoggedIn(),authService.getUserInfo().orElse(null)));
    }
}
