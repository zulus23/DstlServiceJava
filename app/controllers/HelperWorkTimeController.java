package controllers;

import auth.AuthConstants;
import auth.AuthService;
import model.Enterprise;
import org.pac4j.core.profile.CommonProfile;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.HelperServices;
import utils.DbUtils;

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


    public Result index(){
        Enterprise enterprise = DbUtils.enterpriseFromUser("ЗАО ГОТЭК-ЦПУ");
        return ok(Json.toJson(helperServices.workTimeList(enterprise)));
    }

    public Result create(){
        CommonProfile commonProfile =  authService.getUserInfo().orElse(null);
        return ok(Json.toJson(helperServices.saveWorkTime(request().body().asJson(),
                                                          commonProfile.getAttribute(AuthConstants.SERVICE_DSTL).toString())));
    }
  /*  public Result update(Integer id){
        return ok(Json.toJson(helperServices.updateNormaTimeLoading(request().body().asJson())));
    }
    public Result delete(Integer id){

        return ok(Json.toJson(helperServices.deleteNormaTimeLoading(id)));
    }


*/
}
