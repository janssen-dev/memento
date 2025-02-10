package dev.janssensoftware.memento.application.auth;

import dev.janssensoftware.memento.domain.model.User;
import dev.janssensoftware.memento.port.auth.in.UserPort;
import dev.janssensoftware.memento.port.auth.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserPort {

    private final UserPersistencePort userPersistencePort;

    @Override
    public User createUser(User user) {
        return userPersistencePort.save(user);
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userPersistencePort.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userPersistencePort.findByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        if (!userPersistencePort.existsById(user.getId())) {
            throw new IllegalArgumentException("User not found");
        }
        return userPersistencePort.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        userPersistencePort.deleteById(id);
    }
}
