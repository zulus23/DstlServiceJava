package controllers;

import auth.AuthService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.HelperServices;

import javax.inject.Inject;

/**
 * Created by Gukov on 26.10.2016.
 */
public class HelperWorkTimeController extends Controller {
    @Inject
    WebJarAssets webJarAssets;

    @Inject
    private AuthService authService;


    @Inject
    private HelperServices helperServices;


    public Result show(){
        return ok(views.html.helper.helperWorkTime.render("Рабочие время",webJarAssets,authService.isLoggedIn(),authService.getUserInfo().orElse(null)));
    }

   /*
    public Result index(){
        return ok(Json.toJson(helperServices.normaTimeLoadingList()));
    }

    public Result create(){
        return ok(Json.toJson(helperServices.saveNormaTimeLoading(request().body().asJson())));
    }
    public Result update(Integer id){
        return ok(Json.toJson(helperServices.updateNormaTimeLoading(request().body().asJson())));
    }
    public Result delete(Integer id){

        return ok(Json.toJson(helperServices.deleteNormaTimeLoading(id)));
    }


*/
}
