package dev.janssensoftware.memento.infrastructure.auth.in.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String username;
    private String password;
}
