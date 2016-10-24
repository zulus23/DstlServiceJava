package auth;

import org.pac4j.core.credentials.UsernamePasswordCredentials;

/**
 * Created by Zhukov on 24.10.2016.
 */
public class UsernamePasswordEnterpriseCredentials extends UsernamePasswordCredentials {
    private static final long serialVersionUID = -7219878989627796564L;
    private final String enterprise;

    public UsernamePasswordEnterpriseCredentials(String username, String password, String clientName, String enterprise) {
        super(username, password, clientName);
        this.enterprise = enterprise;
    }

    public String getEnterprise() {
        return enterprise;
    }
}
