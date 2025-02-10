package dev.janssensoftware.memento.application.auth;

import dev.janssensoftware.memento.application.auth.dto.TokenDto;
import dev.janssensoftware.memento.application.auth.dto.UserPassDto;
import dev.janssensoftware.memento.application.auth.dto.UsernameAlreadyExistsException;
import dev.janssensoftware.memento.domain.model.User;
import dev.janssensoftware.memento.port.auth.in.AuthWebPort;
import dev.janssensoftware.memento.port.auth.out.AuthJwtPort;
import dev.janssensoftware.memento.port.auth.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthWebPort {

    private final UserPersistencePort userPersistencePort;
    private final AuthJwtPort authenticationPort;

    @Override
    public TokenDto register(UserPassDto request) {
        if (userPersistencePort.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException();
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
        return new TokenDto(token);
    }

    @Override
    public TokenDto login(UserPassDto request) {
        authenticationPort.authenticate(request.getUsername(), request.getPassword());

        User user = userPersistencePort.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = authenticationPort.generateToken(user);
        return new TokenDto(token);
    }
}
