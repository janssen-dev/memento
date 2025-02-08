package dev.janssensoftware.memento.application.auth.port;

import dev.janssensoftware.memento.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPersistencePort {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
