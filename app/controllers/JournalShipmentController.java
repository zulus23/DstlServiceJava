package controllers;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.java.Secure;
import org.pac4j.play.store.PlaySessionStore;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

import java.util.Optional;

import static play.mvc.Results.ok;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class JournalShipmentController extends Controller {
    @Inject
    WebJarAssets webJarAssets;

    @Inject
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


    @Secure(clients = "FormClient")
    public Result show() {
        return ok(views.html.planShipment.render("",webJarAssets,isLoggedIn(),getUserInfo().orElse(null)));
    }
}
