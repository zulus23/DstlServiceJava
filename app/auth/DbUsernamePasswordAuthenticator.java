package auth;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.HttpAction;

/**
 * Created by Zhukov on 24.10.2016.
 */
public class DbUsernamePasswordAuthenticator implements Authenticator<UsernamePasswordEnterpriseCredentials> {
    @Override
    public void validate(UsernamePasswordEnterpriseCredentials credentials, WebContext context) throws HttpAction {

    }
}
