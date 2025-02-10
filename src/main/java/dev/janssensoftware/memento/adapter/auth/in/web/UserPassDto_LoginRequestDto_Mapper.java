package dev.janssensoftware.memento.adapter.auth.in.web;

import dev.janssensoftware.memento.application.auth.dto.UserPassDto;
import dev.janssensoftware.memento.infrastructure.auth.in.web.dto.LoginRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserPassDto_LoginRequestDto_Mapper {
    public UserPassDto toUserPassDto(LoginRequestDto request) {
        return new UserPassDto(
                request.getUsername(),
                request.getPassword()
        );
    }
}
