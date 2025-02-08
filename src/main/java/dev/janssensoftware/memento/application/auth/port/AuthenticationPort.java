package dev.janssensoftware.memento.application.auth.port;

import dev.janssensoftware.memento.domain.model.User;

public interface AuthenticationPort {
    String generateToken(User user);
    void authenticate(String username, String password);
    String encodePassword(String password);
}
