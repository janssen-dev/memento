package dev.janssensoftware.memento.application.auth;

import dev.janssensoftware.memento.application.auth.port.UserPersistencePort;
import dev.janssensoftware.memento.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserPersistencePort userPersistencePort;

    public User createUser(User user) {
        return userPersistencePort.save(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userPersistencePort.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userPersistencePort.findByUsername(username);
    }

    public User updateUser(User user) {
        if (!userPersistencePort.existsById(user.getId())) {
            throw new IllegalArgumentException("User not found");
        }
        return userPersistencePort.save(user);
    }

    public void deleteUserById(UUID id) {
        userPersistencePort.deleteById(id);
    }
}
