package dev.janssensoftware.memento.adapter.auth.in.web;

import dev.janssensoftware.memento.application.auth.dto.TokenDto;
import dev.janssensoftware.memento.infrastructure.auth.in.web.dto.AuthResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TokenDto_AuthResponseDto_Mapper {

    public AuthResponseDto toAuthResponseDto(TokenDto tokenDto) {
        return new AuthResponseDto(
                tokenDto.getToken()
        );
    }
}
