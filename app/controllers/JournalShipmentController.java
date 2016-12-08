package controllers;

import auth.AuthService;
import model.plan.PlanRequestTransport;
import model.plan.PlanShipmentItem;
import org.pac4j.play.java.Secure;
import play.cache.CacheApi;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Option;
import services.PlanDayService;
import utils.PlanShipmentItemException;

import javax.inject.Inject;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class JournalShipmentController extends Controller {



    @Inject
    private CacheApi cache;

    @Inject
    WebJarAssets webJarAssets;


    @Inject
    private AuthService authService;

    @Inject
    private PlanDayService planDayService;

    @Secure(clients = "HeaderClient")
    public Result deletePlanDayItem() {
        return ok(Json.toJson(planDayService.deletePlandDayItem(request().body().asJson(),authService.getUserInfo().orElse(null))));
    }
    @Secure(clients = "HeaderClient")
    public  Result updatePlanDayItem() {
        //System.out.println(request().body().asJson());
        return ok(Json.toJson(planDayService.updatesPlanShipment(request().body().asJson())));
        //return TODO;
    }




    @Secure(clients = "ServiceDstlFormClient")
    public Result show() {
        return ok(views.html.planShipment.render("",webJarAssets, authService.isLoggedIn(), authService.getUserInfo().orElse(null)));
    }

    public Result index(){
        return ok(Json.toJson(planDayService.journalShipmentList(authService.nameServiceDstl())));
    }

    @Secure(clients = "HeaderClient")
    public Result create() {
        //System.out.println(request().body().asJson());

        try {
            //planShipmentItem = planDayService.savePlanShipmentItem(request().body().asJson(), "ЗАО ГОТЭК-СЕВЕРО-ЗАПАД");
            return ok(Json.toJson(planDayService.savePlanRequestTransports(request().body().asJson(), authService.nameServiceDstl())));
           // return TODO;
        } catch (PlanShipmentItemException e ){
           return internalServerError("Данная запись уже есть в плане");
        }

    }

    public Result planDayIndex(Option<String> dateValue){
        LocalDate localDate = LocalDate.now();
        if(dateValue.isDefined()){
           localDate =  LocalDate.parse(dateValue.get(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        List<PlanRequestTransport> result =  planDayService.selectItemPlan(Date.valueOf(localDate),authService.nameServiceDstl());
       // List<PlanShipmentItem> result = null;
        if (Objects.nonNull(result)){
            return ok(Json.toJson(result));
        }
        return  ok(Json.toJson("No Data"));
    }




}
