package dev.janssensoftware.memento.application.note;

import dev.janssensoftware.memento.application.note.port.NotePersistencePort;
import dev.janssensoftware.memento.domain.model.Note;
import dev.janssensoftware.memento.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {

    private final NotePersistencePort notePersistencePort;

    public Note createNote(Note note) {
        return notePersistencePort.save(note);
    }

    public List<Note> getAllNotesByUser(User user) {

        return notePersistencePort.findByUser(user);
    }
    public List<Note> getAllNotesByUserId(UUID userId) {
        return notePersistencePort.findByUserId(userId);
    }

    public Optional<Note> getNoteById(UUID id) {
        return notePersistencePort.findById(id);
    }

    public Note updateNote(Note note) {
        if (!notePersistencePort.existsById(note.getId())) {
            throw new IllegalArgumentException("Note not found");
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