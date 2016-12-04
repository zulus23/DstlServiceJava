import auth.DstlProfile;
import com.google.common.collect.ImmutableMap;
import controllers.routes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.cas.logout.PlayCacheLogoutHandler;
import org.pac4j.play.store.PlayCacheStore;
import org.pac4j.play.store.PlaySessionStore;
import play.Application;
import play.cache.CacheApi;
import play.core.j.JavaHelpers;
import play.mvc.Call;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import services.HelperServices;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.routeAndCall;

/**
 * Created by Zhukov on 20.11.2016.
 */
public class PlanShipmentTest   {


    private Application fakeApp;

    private PlayCacheStore store;
    private PlayWebContext context;
    private CacheApi cacheApiMock;


    @Before
    public void setup(){
        //UtilsTest.getObjectLocal();
        fakeApp = Helpers.fakeApplication();

        Helpers.start(fakeApp);
        Http.Context context = mock(Http.Context.class);
        Http.Context.current.set(context);

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
        cacheApiMock = mock(CacheApi.class);
        store = new PlayCacheStore(cacheApiMock);
        context = mock(PlayWebContext.class);
        // PlaySessionStore playSessionStore =  playSessionStore.getMock();
        /*
        AuthService authService = fakeApp.injector().instanceOf(AuthServiceImpl.class);*/


       //  Http.Session session = mock(Http.Session.class);

        Http.RequestBuilder requestBuilder = new Http.RequestBuilder()
                .method("POST")
                .uri("/callback?client_name=ServiceDstlFormClient")
                .bodyForm(ImmutableMap.of("username","user","password","user","servicedstl","ЗАО ГОТЭК-ЦПУ"));
         //
        Result result =  route(requestBuilder);
        when(context.getJavaSession()).thenReturn(result.session());

        final PlayWebContext _context = new PlayWebContext(Http.Context.current(),store );

        final ProfileManager<DstlProfile> profileManager = new ProfileManager(context);
        assertEquals(profileManager.isAuthenticated(),true);






        //final PlayWebContext _context = new PlayWebContext(context, store);
//        final ProfileManager<DstlProfile> profileManager = new ProfileManager(context);
//        assertEquals(profileManager.isAuthenticated(),true);
        /*
         Helpers.invokeWithContext(requestBuilder, new Callable<Object>() {
             @Override
             public Object call() throws Exception {

                 final PlayWebContext _context = new PlayWebContext(Http.Context.current(), store);
                 final ProfileManager<DstlProfile> profileManager = new ProfileManager(_context);
                 assertEquals(profileManager.isAuthenticated(),true);
                 return  _context;
             }
         });
            */

       // final PlayWebContext context = new PlayWebContext(_context, store);






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

