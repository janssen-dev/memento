package dev.janssensoftware.memento.application.auth.port;

import dev.janssensoftware.memento.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationPort {
    String generateToken(User user);
    void authenticate(String username, String password);
    String encodePassword(String password);
    UserDetails toUserDetails(User user);
}
