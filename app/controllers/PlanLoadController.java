package controllers;

import auth.AuthService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.PlanLoadService;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by Gukov on 22.11.2016.
 */
public class PlanLoadController extends Controller {
    @Inject
    WebJarAssets webJarAssets;

    @Inject
    PlanLoadService planLoadService;


    @Inject
    private AuthService authService;

    public CompletionStage<Result> showPlanShipmentFailure() {
        return CompletableFuture.supplyAsync(() -> planLoadService.shipmentForLoadPlanList("ЗАО ГОТЭК-ЦПУ"/*authService.nameServiceDstl()*/))
                .thenApply( e -> ok(Json.toJson(e)))
                .exceptionally(e -> ok());
    }


    public  Result show() {

        return ok(views.html.planload.planLoad.render("Планирование погрузки",webJarAssets, authService.isLoggedIn(), authService.getUserInfo().orElse(null)));
    }
}
