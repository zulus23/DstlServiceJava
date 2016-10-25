package auth;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.extractor.CredentialsExtractor;
import org.pac4j.core.exception.HttpAction;

/**
 * Created by Gukov on 25.10.2016.
 */
public class FormEnterpriseExtractor implements CredentialsExtractor<UsernamePasswordEnterpriseCredentials> {

    private final String usernameParameter;

    private final String passwordParameter;

    private final String serviceDstlParameter;

    private final String clientName;

    public FormEnterpriseExtractor(final String usernameParameter, final String passwordParameter,final String serviceDstlParameter, final String clientName) {
        this.usernameParameter = usernameParameter;
        this.passwordParameter = passwordParameter;
        this.serviceDstlParameter = serviceDstlParameter;
        this.clientName = clientName;
    }

    @Override
    public UsernamePasswordEnterpriseCredentials extract(WebContext context) throws HttpAction {
        final String username = context.getRequestParameter(this.usernameParameter);
        final String password = context.getRequestParameter(this.passwordParameter);
        final String serviceDstl = context.getRequestParameter(this.serviceDstlParameter);
        if (username == null || password == null || serviceDstl == null) {
            return null;
        }

        return new UsernamePasswordEnterpriseCredentials(username, password,serviceDstl, clientName);
    }

    public String getUsernameParameter() {
        return usernameParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }


    public String getServiceDstlParameter() {
        return serviceDstlParameter;
    }
}
