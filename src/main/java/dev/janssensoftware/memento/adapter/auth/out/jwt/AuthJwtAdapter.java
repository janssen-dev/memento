package dev.janssensoftware.memento.adapter.auth.out.jwt;

import dev.janssensoftware.memento.application.auth.mapper.User_UserDetails_Mapper;
import dev.janssensoftware.memento.domain.model.User;
import dev.janssensoftware.memento.infrastructure.security.JwtService;
import dev.janssensoftware.memento.port.auth.out.AuthJwtPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthJwtAdapter implements AuthJwtPort { // TODO should call a Service..

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final User_UserDetails_Mapper user_userDetails_mapper;

    @Override
    public String generateToken(User user) {
        return jwtService.generateToken(user_userDetails_mapper.toUserDetails(user));
    }

    @Override
    public void authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
