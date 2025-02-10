package dev.janssensoftware.memento.infrastructure.note.in.web.dto;

import java.util.UUID;

public record WebNoteDto(UUID id, String content, int width, int height, int x, int y, String rgbaColor) {
}
