package dev.janssensoftware.memento.adapter.note.in.web;

import dev.janssensoftware.memento.infrastructure.note.in.web.dto.WebNoteDto;
import dev.janssensoftware.memento.domain.model.Note;
import dev.janssensoftware.memento.domain.model.RgbaColor;
import dev.janssensoftware.memento.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class Note_WebNoteDto_Mapper {

    public Note toNote(WebNoteDto webNoteDto, User user) {
        return new Note(
                webNoteDto.id(),
                webNoteDto.content(),
                webNoteDto.width(),
                webNoteDto.height(),
                webNoteDto.x(),
                webNoteDto.y(),
                RgbaColor.fromHex(webNoteDto.rgbaColor()),
                user
        );
    }

    public WebNoteDto toWebNoteDto(Note note) {
        return new WebNoteDto(
                note.getId(),
                note.getContent(),
                note.getWidth(),
                note.getHeight(),
                note.getX(),
                note.getY(),
                note.getColor().toHex()
        );
    }
}