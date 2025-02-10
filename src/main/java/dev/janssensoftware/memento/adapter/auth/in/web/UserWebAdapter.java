package dev.janssensoftware.memento.adapter.auth.in.web;

import dev.janssensoftware.memento.domain.model.User;
import dev.janssensoftware.memento.port.auth.in.UserWebPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserWebAdapter {

    private final UserWebPort userWebPort;

    public User createUser(User user) {
        return userWebPort.createUser(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userWebPort.getUserById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userWebPort.getUserByUsername(username);
    }

    public User updateUser(User user) {
        return userWebPort.updateUser(user);
    }

    public void deleteUserById(UUID id) {
        userWebPort.deleteUserById(id);
    }
}