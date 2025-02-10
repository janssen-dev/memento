package dev.janssensoftware.memento.adapter.note.in.web;

import dev.janssensoftware.memento.infrastructure.note.in.web.dto.WebNoteDto;
import dev.janssensoftware.memento.port.note.in.NoteWebPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NoteWebAdapter {

    private final NoteWebPort noteWebPort;
    private final Note_WebNoteDto_Mapper note_webNoteDto_mapper;

    public WebNoteDto createNote(WebNoteDto note, String username) {
        return note_webNoteDto_mapper.toWebNoteDto(noteWebPort.createNote(note_webNoteDto_mapper.toNote(note, noteWebPort.findUserByUsername(username).get())));
    }

    public List<WebNoteDto> getAllNotesByUsername(String username) {
        return noteWebPort.getAllNotesByUsername(username).stream().map(note_webNoteDto_mapper::toWebNoteDto).toList();
    }

    public Optional<WebNoteDto> getNoteById(UUID id) {
        return noteWebPort.getNoteById(id).map(note_webNoteDto_mapper::toWebNoteDto);
    }

    public void deleteNoteById(UUID id) {
        noteWebPort.deleteNoteById(id);
    }

    public List<WebNoteDto> patchNotes(List<WebNoteDto> notes, String username) {
        final var patchedNotes = noteWebPort.patchNotes(notes.stream().map(note -> note_webNoteDto_mapper.toNote(note, noteWebPort.findUserByUsername(username).get())).toList());
        return patchedNotes.stream().map(note_webNoteDto_mapper::toWebNoteDto).toList();
    }
}
