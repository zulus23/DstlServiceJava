package auth;

import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.store.PlaySessionStore;
import play.mvc.Http;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

import static javax.swing.text.html.CSS.getAttribute;

/**
 * Created by Gukov on 26.10.2016.
 */
@Singleton
public class AuthServiceImpl implements AuthService {
    @Inject
    private PlaySessionStore playSessionStore;

    @Override
    public Boolean isLoggedIn(){
        final PlayWebContext context = new PlayWebContext(Http.Context.current(), playSessionStore);
        final ProfileManager<DstlProfile> profileManager = new ProfileManager(context);
        return profileManager.isAuthenticated();
    }

    @Override
    public Optional<DstlProfile> getUserInfo(){
        final PlayWebContext context = new PlayWebContext(Http.Context.current(), playSessionStore);
        final ProfileManager<DstlProfile> profileManager = new ProfileManager(context);
        return profileManager.get(true);
    }
    @Override
    public String nameServiceDstl(){
       return getUserInfo().map(e -> e.getNameServiceDstl()).orElse("");
    }


}
