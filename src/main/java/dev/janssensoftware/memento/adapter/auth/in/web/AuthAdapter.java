package dev.janssensoftware.memento.adapter.auth.in.web;

import dev.janssensoftware.memento.infrastructure.auth.in.web.dto.LoginRequestDto;
import dev.janssensoftware.memento.infrastructure.auth.in.web.dto.AuthResponseDto;
import dev.janssensoftware.memento.infrastructure.auth.in.web.dto.RegisterRequestDto;
import dev.janssensoftware.memento.port.auth.in.AuthPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthAdapter {

    private final UserPassDto_LoginRequestDto_Mapper userPassDto_loginRequestDto_mapper;
    private final UserPassDto_RegisterRequestDto_Mapper userPassDto_registerRequestDto_mapper;
    private final TokenDto_AuthResponseDto_Mapper tokenDto_authResponseDto_mapper;
    private final AuthPort authPort;

    public AuthResponseDto register(RegisterRequestDto request) {
        return tokenDto_authResponseDto_mapper.toAuthResponseDto(authPort.register(userPassDto_registerRequestDto_mapper.toUserPassDto(request)));
    }

    public AuthResponseDto login(LoginRequestDto request) {
        return tokenDto_authResponseDto_mapper.toAuthResponseDto(authPort.login(userPassDto_loginRequestDto_mapper.toUserPassDto(request)));
    }
}
