package controllers;

import auth.AuthService;
import model.Enterprise;
import model.WorkTime;
import play.cache.CacheApi;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.HelperServices;

import javax.inject.Inject;
import java.time.Duration;
import java.util.Optional;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

/**
 * Created by Gukov on 26.10.2016.
 */
public class HelperWorkTimeController extends Controller {

    @Inject
    private CacheApi cache;


    @Inject
    WebJarAssets webJarAssets;

    @Inject
    private AuthService authService;


    @Inject
    private HelperServices helperServices;


    public Result show(){
        return ok(views.html.helper.helperWorkTime.render("Рабочие время",webJarAssets, authService.isLoggedIn(), authService.getUserInfo().orElse(null)));
    }


    public Result index(){

        return ok(Json.toJson(helperServices.workTimeList(authService.nameServiceDstl())));
    }

    public Result create(){

        return ok(Json.toJson(helperServices.saveWorkTime(request().body().asJson(), authService.nameServiceDstl())));
    }
    public Result update(Integer id){
        return ok(Json.toJson(helperServices.updateWorkTime(request().body().asJson(), authService.nameServiceDstl())));
    }
    public Result delete(Integer id){

        return ok(Json.toJson(helperServices.deleteWorkTime(id, authService.nameServiceDstl())));
    }

    public Result read(Integer id){
        return TODO;
    }

    public Result  minuteWorkDay(){
        String dstlService =  authService.nameServiceDstl();
        long count =   cache.getOrElse(authService.nameServiceDstl(),() -> helperServices.countMinuteInWorkDay(dstlService), 60*25) ;
        return ok(Json.toJson(count));
    }


}
