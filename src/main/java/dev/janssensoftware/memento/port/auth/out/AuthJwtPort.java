package dev.janssensoftware.memento.port.auth.out;

import dev.janssensoftware.memento.domain.model.User;

public interface AuthJwtPort {
    String generateToken(User user);
    void authenticate(String username, String password);
    String encodePassword(String password);
}
