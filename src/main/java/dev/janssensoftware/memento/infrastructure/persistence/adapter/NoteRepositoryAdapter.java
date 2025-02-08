package dev.janssensoftware.memento.infrastructure.persistence.adapter;

import dev.janssensoftware.memento.application.port.NotePersistencePort;
import dev.janssensoftware.memento.domain.model.Note;
import dev.janssensoftware.memento.domain.model.User;
import dev.janssensoftware.memento.infrastructure.persistence.repository.NoteJpaRepository;
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
    public List<Note> findByUserId(UUID userId) {
        return noteJpaRepository.findByUserId(userId);
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
