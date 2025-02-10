package dev.janssensoftware.memento.adapter.auth.in.web;

import dev.janssensoftware.memento.domain.model.User;
import dev.janssensoftware.memento.port.auth.in.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    private final UserPort userPort;

    public User createUser(User user) {
        return userPort.createUser(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userPort.getUserById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userPort.getUserByUsername(username);
    }

    public User updateUser(User user) {
        return userPort.updateUser(user);
    }

    public void deleteUserById(UUID id) {
        userPort.deleteUserById(id);
    }
}