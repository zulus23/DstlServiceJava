package modules;


import auth.DbUsernamePasswordAuthenticator;
import auth.ServiceDstlFormClient;
import com.google.inject.AbstractModule;
import controllers.DemoHttpActionAdapter;
import org.pac4j.core.authorization.authorizer.csrf.CsrfAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.play.ApplicationLogoutController;
import org.pac4j.play.CallbackController;
import org.pac4j.play.store.PlayCacheStore;
import org.pac4j.play.store.PlaySessionStore;
import play.Configuration;
import play.Environment;
import play.db.NamedDatabase;

/**
 * Created by Zhukov on 23.10.2016.
 */
public class SecurityModule extends AbstractModule {
    public final static String JWT_SALT = "12345678901234567890123456789012";

    private final Environment environment;
    private final Configuration configuration;

    public SecurityModule( Environment environment,     Configuration configuration) {
        this.environment = environment;
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(PlaySessionStore.class).to(PlayCacheStore.class);

        ServiceDstlFormClient serviceDstlFormClient = new ServiceDstlFormClient("/loginForm", new DbUsernamePasswordAuthenticator(configuration));
      // FormClient formClient =  new FormClient("/loginForm", new SimpleTestUsernamePasswordAuthenticator());
        //IndirectBasicAuthClient basicAuthClient = new IndirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator());

        HeaderClient headerClient = new HeaderClient("token", new JwtAuthenticator(new SecretSignatureConfiguration(JWT_SALT),
                                                              new SecretEncryptionConfiguration(SecurityModule.JWT_SALT)   ));

       // CsrfAuthorizer csrfAuthorizer = new CsrfAuthorizer();

        Clients clients = new Clients("/callback", serviceDstlFormClient, headerClient);

        final Config config = new Config(clients);
        config.setHttpActionAdapter(new DemoHttpActionAdapter());
        //config.addAuthorizer("csrf",csrfAuthorizer);
     //   ((PlayCacheStore) config.getSessionStore()).setTimeout(60);

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
