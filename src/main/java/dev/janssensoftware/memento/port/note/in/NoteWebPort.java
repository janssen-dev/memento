package dev.janssensoftware.memento.port.note.in;

import dev.janssensoftware.memento.domain.model.Note;
import dev.janssensoftware.memento.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteWebPort {
    Note createNote(Note note);

    List<Note> getAllNotesByUser(User user);

    List<Note> getAllNotesByUsername(String username);

    Optional<Note> getNoteById(UUID id);

    void deleteNoteById(UUID id);

    List<Note> patchNotes(List<Note> notes);
}
