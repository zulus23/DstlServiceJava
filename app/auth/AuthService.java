package auth;

import java.util.Optional;

/**
 * Created by Gukov on 03.11.2016.
 */
public interface AuthService {
    Boolean isLoggedIn();

    Optional<DstlProfile> getUserInfo();

    String nameServiceDstl();
}
