package dev.janssensoftware.memento.port.auth.in;

import dev.janssensoftware.memento.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserWebPort {
    User createUser(User user);

    Optional<User> getUserById(UUID id);

    Optional<User> getUserByUsername(String username);

    User updateUser(User user);

    void deleteUserById(UUID id);
}
