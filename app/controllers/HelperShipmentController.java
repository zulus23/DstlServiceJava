package controllers;

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
    private PlaySessionStore playSessionStore;



    public  Result show() {
        return play.mvc.Results.TODO;
    }
}
