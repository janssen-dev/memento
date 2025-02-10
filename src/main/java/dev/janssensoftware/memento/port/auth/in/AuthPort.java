package dev.janssensoftware.memento.port.auth.in;

import dev.janssensoftware.memento.application.auth.dto.TokenDto;
import dev.janssensoftware.memento.application.auth.dto.UserPassDto;

public interface AuthPort {
    TokenDto register(UserPassDto request);

    TokenDto login(UserPassDto request);
}
