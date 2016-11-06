package controllers;

import auth.AuthService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Option;
import services.PlanDayService;

import javax.inject.Inject;

import java.sql.Date;

import static play.mvc.Results.ok;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class JournalShipmentController extends Controller {



    @Inject
    WebJarAssets webJarAssets;


    @Inject
    private AuthService authService;

    @Inject
    private PlanDayService planDayService;

    /*@Inject
    private PlaySessionStore playSessionStore;

    private Boolean isLoggedIn(){
        final PlayWebContext context = new PlayWebContext(ctx(), playSessionStore);
        final ProfileManager<CommonProfile> profileManager = new ProfileManager(context);
        return profileManager.isAuthenticated();
    }

    private Optional<CommonProfile> getUserInfo(){
        final PlayWebContext context = new PlayWebContext(ctx(), playSessionStore);
        final ProfileManager<CommonProfile> profileManager = new ProfileManager(context);
        return profileManager.get(true);
    }
*/


   // @Secure(clients = "ServiceDstlFormClient")
    public Result show() {
        return ok(views.html.planShipment.render("",webJarAssets, authService.isLoggedIn(), authService.getUserInfo().orElse(null)));
    }

    public Result index(){
        return ok(Json.toJson(planDayService.journalShipmentList()));
    }
    public Result planDayIndex(String dateValue){
        return ok(Json.toJson(planDayService.journalShipmentList()));
    }

}
