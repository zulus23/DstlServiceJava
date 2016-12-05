import auth.AuthService;
import auth.AuthServiceImpl;
import auth.DstlProfile;
import com.google.common.collect.ImmutableMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.store.PlayCacheStore;
import org.pac4j.play.store.PlaySessionStore;
import play.Application;
import play.mvc.Call;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import services.HelperServices;

import java.util.LinkedHashMap;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;
import static org.pac4j.core.context.Pac4jConstants.USER_PROFILES;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.invokeWithContext;
import static play.test.Helpers.route;

/**
 * Created by Zhukov on 20.11.2016.
 */

public class PlanShipmentTest   {


    private Application fakeApp;


    private ProfileManager profileManager;

    @Before
    public void setup(){

        profileManager = new ProfileManager(mock(WebContext.class));
        fakeApp = Helpers.fakeApplication();

        Helpers.start(fakeApp);

    }



    @Test
    public void workTimeNotNull(){
        HelperServices planDayService = fakeApp.injector().instanceOf(HelperServices.class);
        long  aLong =  planDayService.countMinuteInWorkDay("ЗАО ГОТЭК-ЦПУ");
        assertEquals(570,aLong);

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

    @Test
    public void authenticateFailure(){

       Call call =  controllers.routes.JournalShipmentController.show();
       Result result =  route(fakeRequest(call));
       assertEquals(result.status(),Helpers.SEE_OTHER);
       assertEquals(result.redirectLocation().get(),("/loginForm"));

    }
    @Test
    public void authenticateSuccess(){

        LinkedHashMap<String,DstlProfile> dstlProfileLinkedHashMap = new LinkedHashMap<>();

        Http.RequestBuilder requestBuilder = new Http.RequestBuilder()
                .method("POST")
                .uri("/callback?client_name=ServiceDstlFormClient")
                .bodyForm(ImmutableMap.of("username","user","password","user","servicedstl","ЗАО ГОТЭК-ЦПУ"));

            Result result =   route(requestBuilder);
            Http.Context.current.set(new Http.Context(requestBuilder));
            final PlayWebContext _context = new PlayWebContext(Http.Context.current(),mock(PlayCacheStore.class));
            dstlProfileLinkedHashMap.put("DstlProfile",new DstlProfile());
            _context.setRequestAttribute(USER_PROFILES,dstlProfileLinkedHashMap);
            final ProfileManager<DstlProfile> profileManager = new ProfileManager(_context);
          //  assertEquals(profileManager.isAuthenticated(),true);
            Optional<DstlProfile> dstlProfile =  profileManager.get(false);

       assertEquals(true,profileManager.isAuthenticated());

    }

    @Test
    public void authenticateSuccessJournalShipmentControllerShow(){
        PlaySessionStore playSessionStore = fakeApp.injector().instanceOf(PlayCacheStore.class);

        LinkedHashMap<String,DstlProfile> dstlProfileLinkedHashMap = new LinkedHashMap<>();
        Call call =  controllers.routes.JournalShipmentController.show();

        invokeWithContext(fakeRequest(call), ()->{
            Http.Session session = Http.Context.current().session();

            final PlayWebContext _context = new PlayWebContext(Http.Context.current(),playSessionStore);
            dstlProfileLinkedHashMap.put("DstlProfile",new DstlProfile());
            _context.setRequestAttribute(USER_PROFILES,dstlProfileLinkedHashMap);

             Result result =  route(fakeRequest(call));
            assertEquals(OK,result.status());
            return result;
        });



    }




    @Test
    public void authenticateFailureWhenCreateItemPlanDay(){

        Call call =  controllers.routes.JournalShipmentController.create();
        Result result =  route(fakeRequest(call));
        assertEquals(result.status(),Helpers.UNAUTHORIZED);


    }






    @After
    public void teardown() {
        Helpers.stop(fakeApp);
    }

}

