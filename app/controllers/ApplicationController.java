package controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import modules.SecurityModule;
import org.pac4j.core.config.Config;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.profile.JwtGenerator;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.store.PlaySessionStore;
import play.mvc.Controller;
import play.mvc.Result;


import javax.inject.Inject;
import javax.xml.ws.ServiceMode;
import java.util.List;
import java.util.Optional;

import static java.security.KeyRep.Type.SECRET;

/**
 * Created by Zhukov on 22.10.2016.
 */
public class ApplicationController extends Controller {

    @Inject
    private Config config;

    @Inject
    private PlaySessionStore playSessionStore;

    @Inject
    WebJarAssets webJarAssets;


    private List<CommonProfile> getProfiles() {
        final PlayWebContext context = new PlayWebContext(ctx(), playSessionStore);
        final ProfileManager<CommonProfile> profileManager = new ProfileManager(context);
        return profileManager.getAll(true);
    }

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
    public  Result loginForm() {
        final FormClient formClient = (FormClient) config.getClients().findClient("FormClient");
        return ok(views.html.loginForm.render(formClient.getCallbackUrl(),webJarAssets,isLoggedIn(),getUserInfo().orElse(null)));
    }

    public Result jwt() {
        final Optional<CommonProfile> profiles = getUserInfo();
        final JwtGenerator generator = new JwtGenerator(new SecretSignatureConfiguration(SecurityModule.JWT_SALT), new SecretEncryptionConfiguration(SecurityModule.JWT_SALT));
        String token = "";
        if (profiles.isPresent()) {
            token = generator.generate(profiles.get());
        }
        return ok(token);
    }


    public Result index() {

        return ok(views.html.index.render("Your new application is ready.",webJarAssets,isLoggedIn(),getUserInfo().orElse(null)));
    }


}
