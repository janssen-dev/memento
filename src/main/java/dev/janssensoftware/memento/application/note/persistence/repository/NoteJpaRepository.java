package dev.janssensoftware.memento.application.note.persistence.repository;

import dev.janssensoftware.memento.domain.model.Note;
import dev.janssensoftware.memento.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteJpaRepository extends JpaRepository<Note, UUID> {
    List<Note> findByUser(User user);
    List<Note> findByUser_username(String username);
}
