package auth;

import org.pac4j.core.client.IndirectClientV2;
import org.pac4j.core.client.RedirectAction;
import org.pac4j.core.context.Pac4jConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.credentials.extractor.FormExtractor;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.exception.TechnicalException;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.creator.ProfileCreator;
import org.pac4j.core.util.CommonHelper;

/**
 * Created by Zhukov on 25.10.2016.
 */
public class ServiceDstlFormClient extends IndirectClientV2<UsernamePasswordEnterpriseCredentials, CommonProfile> {


    private String loginUrl;

    public final static String ERROR_PARAMETER = "error";

    public final static String MISSING_FIELD_ERROR = "missing_field";

    private String usernameParameter = Pac4jConstants.USERNAME;

    private String passwordParameter = Pac4jConstants.PASSWORD;

    private String serviceDstlParameter = AuthConstants.SERVICE_DSTL;


    public ServiceDstlFormClient() {
    }

    public ServiceDstlFormClient(final String loginUrl, final Authenticator usernamePasswordAuthenticator) {
        this.loginUrl = loginUrl;
        setAuthenticator(usernamePasswordAuthenticator);
    }

    public ServiceDstlFormClient(final String loginUrl, final String usernameParameter, final String passwordParameter,
                                 final String serviceDstlParameter,
                      final Authenticator usernamePasswordAuthenticator) {
        this.loginUrl = loginUrl;
        this.usernameParameter = usernameParameter;
        this.passwordParameter = passwordParameter;
        this.serviceDstlParameter = serviceDstlParameter;
        setAuthenticator(usernamePasswordAuthenticator);
    }

    public ServiceDstlFormClient(final String loginUrl, final Authenticator usernamePasswordAuthenticator,
                      final ProfileCreator profileCreator) {
        this.loginUrl = loginUrl;
        setAuthenticator(usernamePasswordAuthenticator);
        setProfileCreator(profileCreator);
    }

    @Override
    protected void internalInit(final WebContext context) {
        super.internalInit(context);

        CommonHelper.assertNotBlank("loginUrl", this.loginUrl);
        this.loginUrl = callbackUrlResolver.compute(this.loginUrl, context);
        CommonHelper.assertNotBlank("usernameParameter", this.usernameParameter);
        CommonHelper.assertNotBlank("passwordParameter", this.passwordParameter);
        CommonHelper.assertNotBlank("serviceDstlParameter", this.serviceDstlParameter);

        setRedirectActionBuilder(webContext -> RedirectAction.redirect(this.loginUrl));
        setCredentialsExtractor(new FormEnterpriseExtractor(usernameParameter, passwordParameter, serviceDstlParameter,getName()));
    }

    @Override
    protected UsernamePasswordEnterpriseCredentials retrieveCredentials(final WebContext context) throws HttpAction {
        CommonHelper.assertNotNull("credentialsExtractor", getCredentialsExtractor());
        CommonHelper.assertNotNull("authenticator", getAuthenticator());

        final String username = context.getRequestParameter(this.usernameParameter);
        UsernamePasswordEnterpriseCredentials credentials;
        try {
            // retrieve credentials
            credentials = getCredentialsExtractor().extract(context);
            logger.debug("usernamePasswordCredentials: {}", credentials);
            if (credentials == null) {
                throw handleInvalidCredentials(context, username, "Username and password cannot be blank -> return to the form with error", MISSING_FIELD_ERROR, 401);
            }
            // validate credentials
            getAuthenticator().validate(credentials, context);
        } catch (final CredentialsException e) {
            throw handleInvalidCredentials(context, username, "Credentials validation fails -> return to the form with error", computeErrorMessage(e), 403);
        }

        return credentials;
    }

    private HttpAction handleInvalidCredentials(final WebContext context, final String username, String message, String errorMessage, int errorCode) throws HttpAction {
        // it's an AJAX request -> unauthorized (instead of a redirection)
        if (getAjaxRequestResolver().isAjax(context)) {
            logger.info("AJAX request detected -> returning " + errorCode);
            return HttpAction.status("AJAX request -> " + errorCode, errorCode, context);
        } else {
            String redirectionUrl = CommonHelper.addParameter(this.loginUrl, this.usernameParameter, username);
            redirectionUrl = CommonHelper.addParameter(redirectionUrl, ERROR_PARAMETER, errorMessage);
            logger.debug("redirectionUrl: {}", redirectionUrl);
            logger.debug(message);
            return HttpAction.redirect(message, context, redirectionUrl);
        }
    }

    /**
     * Return the error message depending on the thrown exception. Can be overriden for other message computation.
     *
     * @param e the technical exception
     * @return the error message
     */
    protected String computeErrorMessage(final TechnicalException e) {
        return e.getClass().getSimpleName();
    }

    public String getLoginUrl() {
        return this.loginUrl;
    }

    public void setLoginUrl(final String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getUsernameParameter() {
        return this.usernameParameter;
    }

    public void setUsernameParameter(final String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return this.passwordParameter;
    }

    public void setPasswordParameter(final String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }

    public String getServiceDstlParameter() {        return serviceDstlParameter;
    }

    public void setServiceDstlParameter(final String serviceDstlParameter) {
        this.serviceDstlParameter = serviceDstlParameter;
    }

    @Override
    public String toString() {
        return CommonHelper.toString(this.getClass(), "callbackUrl", this.callbackUrl, "name", getName(), "loginUrl",
                this.loginUrl, "usernameParameter", this.usernameParameter, "passwordParameter", this.passwordParameter, "serviceDstlParameter",this.serviceDstlParameter,
                "redirectActionBuilder", getRedirectActionBuilder(), "extractor", getCredentialsExtractor(), "authenticator", getAuthenticator(),
                "profileCreator", getProfileCreator());
    }

}
