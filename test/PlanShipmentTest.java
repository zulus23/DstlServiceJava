import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;

import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;

import services.DstlService;
import services.HelperServices;
import services.PlanDayService;
import static org.junit.Assert.*;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.NO_CONTENT;
import static play.test.Helpers.route;

/**
 * Created by Zhukov on 20.11.2016.
 */
public class PlanShipmentTest   {

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
    public void workTimeNotNull(){
        HelperServices planDayService = fakeApp.injector().instanceOf(HelperServices.class);
        long  aLong =  planDayService.countMinuteInWorkDay("ЗАО ГОТЭК-ЦПУ");
        assertEquals(570,aLong);
       // assertEquals(569,aLong);
    }

    @Test
    public void RequestToWorkTimeCount(){
        Http.RequestBuilder requestBuilder = new Http.RequestBuilder()
                                                     .method("GET")
                                                     .uri("/api/minuteworkday");
        Result result =  route(requestBuilder);

        assertNotEquals(NOT_FOUND,result.status());
        result =  route(requestBuilder);

    }


    @After
    public void teardown() {
        Helpers.stop(fakeApp);
    }

}

