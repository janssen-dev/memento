package dev.janssensoftware.memento.application.usecase;

import dev.janssensoftware.memento.application.port.UserPersistencePort;
import dev.janssensoftware.memento.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserUseCase {

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
            throw new IllegalArgumentException("User does not exist");
        }
        return userPersistencePort.save(user);
    }

    public void deleteUserById(UUID id) {
        if (!userPersistencePort.existsById(id)) {
            throw new IllegalArgumentException("User does not exist");
        }
        userPersistencePort.deleteById(id);
    }
}
