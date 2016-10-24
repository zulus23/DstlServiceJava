package modules;

import com.google.inject.AbstractModule;
import controllers.DemoHttpActionAdapter;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.play.ApplicationLogoutController;
import org.pac4j.play.CallbackController;
import org.pac4j.play.store.PlayCacheStore;
import org.pac4j.play.store.PlaySessionStore;

/**
 * Created by Zhukov on 23.10.2016.
 */
public class SecurityModule extends AbstractModule {
    public final static String JWT_SALT = "12345678901234567890123456789012";
    @Override
    protected void configure() {
        bind(PlaySessionStore.class).to(PlayCacheStore.class);

        FormClient formClient = new FormClient("/loginForm", new SimpleTestUsernamePasswordAuthenticator());
        IndirectBasicAuthClient basicAuthClient = new IndirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator());

        ParameterClient parameterClient = new ParameterClient("token", new JwtAuthenticator(JWT_SALT));

        Clients clients = new Clients("/callback", formClient,
                basicAuthClient, parameterClient);

        Config config = new Config(clients);
        config.setHttpActionAdapter(new DemoHttpActionAdapter());
        bind(Config.class).toInstance(config);

        // callback
        final CallbackController callbackController = new CallbackController();
        callbackController.setDefaultUrl("/");
        callbackController.setMultiProfile(true);
        bind(CallbackController.class).toInstance(callbackController);
        // logout
        final ApplicationLogoutController logoutController = new ApplicationLogoutController();
        logoutController.setDefaultUrl("/?defaulturlafterlogout");
        bind(ApplicationLogoutController.class).toInstance(logoutController);

    }
}
