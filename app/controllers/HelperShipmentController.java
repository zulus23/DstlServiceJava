package controllers;

import akka.stream.impl.io.OutputStreamSourceStage;
import auth.AuthService;

import com.fasterxml.jackson.databind.JsonNode;
import model.NormaTimeLoading;
import play.libs.Json;
import play.mvc.BodyParser;
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
        return ok(views.html.helper.helpNormTimeLoad.render("Норматив времени погрузки",webJarAssets,authService.isLoggedIn(),authService.getUserInfo().orElse(null)));
    }

    /*public  Result show() {

        return ok(views.html.helper.helperShipment.render("Справочники",webJarAssets,authService.isLoggedIn(),authService.getUserInfo().orElse(null)));
    }*/
    public Result index(){
        return ok(Json.toJson(helperServices.normaTimeLoadingList()));
    }

    public Result create(){
        helperServices.saveNormaTimeLoading(request().body().asJson());
        return ok();
    }
    public Result update(Integer id){
        return ok(Json.toJson(helperServices.updateNormaTimeLoading(request().body().asJson())));
    }
    public Result delete(Integer id){

        return ok(Json.toJson(helperServices.deleteNormaTimeLoading(id)));
    }
    public Result read(Integer id){
        return TODO;
    }


}
