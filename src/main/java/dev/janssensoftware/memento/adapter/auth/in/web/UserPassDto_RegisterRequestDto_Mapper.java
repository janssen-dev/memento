package dev.janssensoftware.memento.adapter.auth.in.web;

import dev.janssensoftware.memento.application.auth.dto.UserPassDto;
import dev.janssensoftware.memento.infrastructure.auth.in.web.dto.RegisterRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserPassDto_RegisterRequestDto_Mapper {
    public UserPassDto toUserPassDto(RegisterRequestDto request) {
        return new UserPassDto(
                request.getUsername(),
                request.getPassword()
        );
    }
}
