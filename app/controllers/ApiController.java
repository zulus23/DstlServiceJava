package controllers;

import org.pac4j.play.java.Secure;
import org.pac4j.play.store.PlaySessionStore;

import play.mvc.Controller;
import play.mvc.Result;
import services.HelperServices;
import play.libs.Json;
import javax.inject.Inject;



/**
 * Created by Gukov on 24.10.2016.
 */
public class ApiController extends Controller {

    @Inject
    WebJarAssets webJarAssets;

    @Inject
    private PlaySessionStore playSessionStore;

    @Inject
    private HelperServices helperServices;


    @Secure(clients = "HeaderClient")
    public Result listTest(){
        return  ok(Json.toJson(helperServices.serviceDstl()));
    }
}
