import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.test.Helpers;

import services.DstlService;
import services.PlanDayService;
import static org.junit.Assert.*;
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
        PlanDayService planDayService = fakeApp.injector().instanceOf(PlanDayService.class);
        long  aLong =  planDayService.countMinuteInWorkDay("ЗАО ГОТЭК-ЦПУ");
        assertEquals(570,aLong);
       // assertEquals(569,aLong);
    }

    @After
    public void teardown() {
        Helpers.stop(fakeApp);
    }

}

