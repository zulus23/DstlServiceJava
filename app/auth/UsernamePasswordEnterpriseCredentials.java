package auth;

import org.pac4j.core.credentials.UsernamePasswordCredentials;

/**
 * Created by Zhukov on 24.10.2016.
 */
public class UsernamePasswordEnterpriseCredentials extends UsernamePasswordCredentials {
    private static final long serialVersionUID = -7219878989627796564L;
    private final String enterprise;

    public UsernamePasswordEnterpriseCredentials(String username, String password, String enterprise, String clientName) {
        super(username, password, clientName);
        this.enterprise = enterprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsernamePasswordEnterpriseCredentials)) return false;
        if (!super.equals(o)) return false;

        UsernamePasswordEnterpriseCredentials that = (UsernamePasswordEnterpriseCredentials) o;

        return enterprise.equals(that.enterprise);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + enterprise.hashCode();
        return result;
    }

    public String getEnterprise() {
        return enterprise;
    }
}
