package dev.janssensoftware.memento.application.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPassDto {
    private String username;
    private String password;
}
