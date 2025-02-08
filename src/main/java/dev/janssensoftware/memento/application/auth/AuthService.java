package dev.janssensoftware.memento.application.auth;

import dev.janssensoftware.memento.application.auth.dto.AuthRequest;
import dev.janssensoftware.memento.application.auth.dto.AuthResponse;
import dev.janssensoftware.memento.application.auth.dto.RegisterRequest;
import dev.janssensoftware.memento.application.auth.port.AuthenticationPort;
import dev.janssensoftware.memento.application.auth.port.UserPersistencePort;
import dev.janssensoftware.memento.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserPersistencePort userPersistencePort;
    private final AuthenticationPort authenticationPort;

    public AuthResponse register(RegisterRequest request) {
        if (userPersistencePort.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User already exists");
        }

        User user = new User(
                UUID.randomUUID(),
                null,
                request.getUsername(),
                authenticationPort.encodePassword(request.getPassword()),
                List.of("USER"),
                List.of()
        );

        userPersistencePort.save(user);
        String token = authenticationPort.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationPort.authenticate(request.getUsername(), request.getPassword());

        User user = userPersistencePort.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = authenticationPort.generateToken(user);
        return new AuthResponse(token);
    }
}
