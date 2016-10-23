package controllers;

import com.google.inject.Inject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.HelperServices;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class HelperController extends Controller{

  @Inject
  private HelperServices helperServices;


    public Result serviceDstl() {
        return ok(Json.toJson(helperServices.serviceDstl()));
    }
}
