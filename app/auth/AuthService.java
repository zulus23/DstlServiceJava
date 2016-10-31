package auth;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.store.PlaySessionStore;
import play.mvc.Http;

import javax.inject.Inject;
import java.util.Optional;

import static javax.swing.text.html.CSS.getAttribute;

/**
 * Created by Gukov on 26.10.2016.
 */
public class AuthService {
    @Inject
    private PlaySessionStore playSessionStore;

    public Boolean isLoggedIn(){
        final PlayWebContext context = new PlayWebContext(Http.Context.current(), playSessionStore);
        final ProfileManager<CommonProfile> profileManager = new ProfileManager(context);
        return profileManager.isAuthenticated();
    }

    public Optional<CommonProfile> getUserInfo(){
        final PlayWebContext context = new PlayWebContext(Http.Context.current(), playSessionStore);
        final ProfileManager<CommonProfile> profileManager = new ProfileManager(context);
        return profileManager.get(true);
    }
    public String nameServiceDstl(){
       return getUserInfo().map(e -> e.getAttribute(AuthConstants.SERVICE_DSTL).toString()).orElse("");
    }


}
