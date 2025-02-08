package dev.janssensoftware.memento.application.usecase;

import dev.janssensoftware.memento.application.port.NotePersistencePort;
import dev.janssensoftware.memento.domain.model.Note;
import dev.janssensoftware.memento.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteUseCase {

    private final NotePersistencePort notePersistencePort;

    public Note createNote(Note note) {
        return notePersistencePort.save(note);
    }

    public Optional<Note> getNoteById(UUID id) {
        return notePersistencePort.findById(id);
    }

    public List<Note> getNotesByUserId(UUID userId) {
        return notePersistencePort.findByUserId(userId);
    }

    public Note updateNote(Note note) {
        if (!notePersistencePort.existsById(note.getId())) {
            throw new IllegalArgumentException("Note does not exist");
        }
        return notePersistencePort.save(note);
    }

    public void deleteNoteById(UUID id) {
        if (!notePersistencePort.existsById(id)) {
            throw new IllegalArgumentException("Note does not exist");
        }
        notePersistencePort.deleteById(id);
    }
}
