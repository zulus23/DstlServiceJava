package controllers;

import auth.AuthService;
import model.plan.PlanShipmentItem;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Option;
import services.PlanDayService;

import javax.inject.Inject;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;
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

    public Result deletePlanDayItem(Integer id) {
        return play.mvc.Results.TODO;
    }

    public  Result updatePlanDayItem(Integer id) {
        System.out.println(request().body().asJson());
        return play.mvc.Results.TODO;
    }



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
    public Result create() {
        System.out.println(request().body().asJson());
        planDayService.savePlanShipmentItem(request().body().asJson(),"ЗАО ГОТЭК-СЕВЕРО-ЗАПАД");

        return play.mvc.Results.TODO;
    }
    public Result planDayIndex(Option<String> dateValue){
        LocalDate localDate = LocalDate.now();
        if(dateValue.isDefined()){
           localDate =  LocalDate.parse(dateValue.get(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        List<PlanShipmentItem> result =  planDayService.selectItemPlan(Date.valueOf(localDate),authService.nameServiceDstl());
        if (Objects.nonNull(result)){
            return ok(Json.toJson(result));
        }
        return  ok();
    }

}
