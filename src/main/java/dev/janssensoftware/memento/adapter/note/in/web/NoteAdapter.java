package dev.janssensoftware.memento.adapter.note.in.web;

import dev.janssensoftware.memento.infrastructure.note.in.web.dto.WebNoteDto;
import dev.janssensoftware.memento.port.auth.out.UserPersistencePort;
import dev.janssensoftware.memento.port.note.in.NotePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NoteAdapter {

    private final NotePort notePort;
    private final Note_WebNoteDto_Mapper note_webNoteDto_mapper;
    private final UserPersistencePort userPersistencePort;

    public WebNoteDto createNote(WebNoteDto note, String username) {
        return note_webNoteDto_mapper.toWebNoteDto(notePort.createNote(note_webNoteDto_mapper.toNote(note, userPersistencePort.findByUsername(username).get())));
    }

    public List<WebNoteDto> getAllNotesByUsername(String username) {
        return notePort.getAllNotesByUsername(username).stream().map(note_webNoteDto_mapper::toWebNoteDto).toList();
    }

    public Optional<WebNoteDto> getNoteById(UUID id) {
        return notePort.getNoteById(id).map(note_webNoteDto_mapper::toWebNoteDto);
    }

    public void deleteNoteById(UUID id) {
        notePort.deleteNoteById(id);
    }

    public List<WebNoteDto> patchNotes(List<WebNoteDto> notes, String username) {
        final var patchedNotes = notePort.patchNotes(notes.stream().map(note -> note_webNoteDto_mapper.toNote(note, userPersistencePort.findByUsername(username).get())).toList());
        return patchedNotes.stream().map(note_webNoteDto_mapper::toWebNoteDto).toList();
    }
}
