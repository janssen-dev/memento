package dev.janssensoftware.memento.port.note.out;

import dev.janssensoftware.memento.domain.model.Note;
import dev.janssensoftware.memento.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotePersistencePort {
    Note save(Note note);
    List<Note> findByUser(User user);
    List<Note> findByUsername(String username);
    Optional<Note> findById(UUID id);
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
