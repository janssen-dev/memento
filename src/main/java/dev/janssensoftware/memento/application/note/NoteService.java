package dev.janssensoftware.memento.application.note;

import dev.janssensoftware.memento.domain.model.Note;
import dev.janssensoftware.memento.domain.model.User;
import dev.janssensoftware.memento.port.auth.out.UserPersistencePort;
import dev.janssensoftware.memento.port.note.in.NoteWebPort;
import dev.janssensoftware.memento.port.note.out.NotePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService implements NoteWebPort {

    private final NotePersistencePort notePersistencePort;
    private final UserPersistencePort userPersistencePort;

    @Override
    public Note createNote(Note note) {
        return notePersistencePort.save(note);
    }

    @Override
    public List<Note> getAllNotesByUser(User user) {

        return notePersistencePort.findByUser(user);
    }
    @Override
    public List<Note> getAllNotesByUsername(String username) {
        return notePersistencePort.findByUsername(username);
    }

    @Override
    public Optional<Note> getNoteById(UUID id) {
        return notePersistencePort.findById(id);
    }

    @Override
    public void deleteNoteById(UUID id) {
        if (!notePersistencePort.existsById(id)) {
            throw new IllegalArgumentException("Note does not exist");
        }
        notePersistencePort.deleteById(id);
    }


    @Override
    public List<Note> patchNotes(List<Note> notes) {
        return notes.stream()
                .map(note -> {
                    Optional<Note> existingNote = notePersistencePort.findById(note.getId());
                    if (existingNote.isPresent()) {
                        return notePersistencePort.save(note);
                    } else {
                        throw new IllegalArgumentException("Note not found with id: " + note.getId());
                    }
                })
                .toList();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userPersistencePort.findByUsername(username);
    }
}
