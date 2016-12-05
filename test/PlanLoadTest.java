import controllers.routes;
import dto.PlanShipmentForLoadPlan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.mvc.Call;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import services.PlanLoadService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;

/**
 * Created by Gukov on 25.11.2016.
 */
public class PlanLoadTest {
    private Application fakeApp;
    @Before
    public void setup(){
        //UtilsTest.getObjectLocal();
        fakeApp = Helpers.fakeApplication();
       /* fakeApp.injector().instanceOf(DstlService.class);
        fakeApp.injector().instanceOf(PlanDayService.class);*/
        Helpers.start(fakeApp);

    }

    @Test
    public void loadListPlan(){
        PlanLoadService planLoadService = fakeApp.injector().instanceOf(PlanLoadService.class);
        List<PlanShipmentForLoadPlan> planShipmentForLoadPlans =  planLoadService.shipmentForLoadPlanList("ЗАО ГОТЭК-ЦПУ");
        assertNotNull(planShipmentForLoadPlans);
        assertEquals(4,planShipmentForLoadPlans.size());

    }

    @Test
    public void mustReturnFullJSon(){

        Call call = controllers.routes.PlanLoadController.showPlanShipmentFailure();
        Result result =  route(fakeRequest(call));

        assertEquals(OK,result.status());
    }



    @After
    public void teardown() {
        Helpers.stop(fakeApp);
    }
}
