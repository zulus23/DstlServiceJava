package auth;

import org.pac4j.core.profile.CommonProfile;

/**
 * Created by Zhukov on 03.11.2016.
 */
public class DstlProfile extends CommonProfile{
    private static final long serialVersionUID = -1856159870249261876L;

    public String getNameServiceDstl() {
      return    (String)getAttribute(AuthConstants.SERVICE_DSTL);
    }

}
