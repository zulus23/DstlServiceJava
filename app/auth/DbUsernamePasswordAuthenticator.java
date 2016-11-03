package auth;

import org.pac4j.core.context.Pac4jConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zhukov on 24.10.2016.
 */
public class DbUsernamePasswordAuthenticator implements Authenticator<UsernamePasswordEnterpriseCredentials> {
    protected static final Logger logger = LoggerFactory.getLogger(DbUsernamePasswordAuthenticator.class);

    @Override
    public void validate(UsernamePasswordEnterpriseCredentials credentials, WebContext context) throws HttpAction {
        if (credentials == null) {
            throwsException("No credential");
        }
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        String service  = credentials.getEnterprise();
        if (CommonHelper.isBlank(username)) {
            throwsException("Username cannot be blank");
        }
        if (CommonHelper.isBlank(password)) {
            throwsException("Password cannot be blank");
        }
        if (CommonHelper.areNotEquals(username, password)) {
            throwsException("Username : '" + username + "' does not match password");
        }
        final DstlProfile profile = new DstlProfile();
        profile.setId(username);
        profile.addAttribute(Pac4jConstants.USERNAME, username);
        profile.addAttribute(AuthConstants.SERVICE_DSTL,service);
        credentials.setUserProfile(profile);
    }

    protected void throwsException(final String message) {
        throw new CredentialsException(message);
    }
}
