package controllers;

import auth.AuthService;
import org.pac4j.play.java.Secure;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.HelperServices;

import javax.inject.Inject;

/**
 * Created by Gukov on 26.10.2016.
 */
public class HelperShipmentController extends Controller {
    @Inject
    WebJarAssets webJarAssets;

    @Inject
    private AuthService authService;


    @Inject
    private HelperServices helperServices;


    public Result show(){
        return ok(views.html.helper.helpNormTimeLoad.render("Норматив времени погрузки",webJarAssets, authService.isLoggedIn(), authService.getUserInfo().orElse(null)));
    }

    /*public  Result show() {

        return ok(views.html.helper.helperShipment.render("Справочники",webJarAssets,authService.isLoggedIn(),authService.getUserInfo().orElse(null)));
    }*/
    public Result index(){
        return ok(Json.toJson(helperServices.normaTimeLoadingList()));
    }

    @Secure(clients = "HeaderClient")
    public Result create(){
        return ok(Json.toJson(helperServices.saveNormaTimeLoading(request().body().asJson())));
    }

    @Secure(clients = "HeaderClient")
    public Result update(Integer id){
        return ok(Json.toJson(helperServices.updateNormaTimeLoading(request().body().asJson())));
    }
    @Secure(clients = "HeaderClient")
    public Result delete(Integer id){

        return ok(Json.toJson(helperServices.deleteNormaTimeLoading(id)));
    }
    public Result read(Integer id){
        return TODO;
    }


}
