package dev.janssensoftware.memento.adapter.note.out.persistence;

import dev.janssensoftware.memento.domain.model.Note;
import dev.janssensoftware.memento.domain.model.User;
import dev.janssensoftware.memento.application.note.persistence.repository.NoteJpaRepository;
import dev.janssensoftware.memento.port.note.out.NotePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NoteRepositoryAdapter implements NotePersistencePort {

    private final NoteJpaRepository noteJpaRepository;

    @Override
    public Note save(Note note) {
        return noteJpaRepository.save(note);
    }

    @Override
    public List<Note> findByUser(User user) {
        return noteJpaRepository.findByUser(user);
    }

    @Override
    public List<Note> findByUsername(String username) {
        return noteJpaRepository.findByUser_username(username);
    }

    @Override
    public Optional<Note> findById(UUID id) {
        return noteJpaRepository.findById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return noteJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        noteJpaRepository.deleteById(id);
    }
}
