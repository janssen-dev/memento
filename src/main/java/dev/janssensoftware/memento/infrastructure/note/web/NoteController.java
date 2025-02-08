package dev.janssensoftware.memento.infrastructure.note.web;

import dev.janssensoftware.memento.application.note.NoteService;
import dev.janssensoftware.memento.domain.model.Note;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @Operation(summary = "Create a new note", description = "Stores a new note in the database.")
    @ApiResponse(responseCode = "201", description = "Note successfully created")
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @Operation(summary = "Get a note by ID", description = "Retrieves a note based on its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable UUID id) {
        return noteService.getNoteById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
    }

    @Operation(summary = "Get all notes of a user", description = "Retrieves all notes associated with a specific user.")
    @ApiResponse(responseCode = "200", description = "Notes successfully retrieved")
    @GetMapping("/user/{userId}")
    public List<Note> getNotesByUser(@PathVariable UUID userId) {
        return noteService.getAllNotesByUserId(userId);
    }

    @Operation(summary = "Update a note", description = "Updates an existing note with new content.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note successfully updated"),
            @ApiResponse(responseCode = "400", description = "ID mismatch")
    })
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable UUID id, @RequestBody Note note) {
        if (!id.equals(note.getId())) {
            throw new IllegalArgumentException("ID mismatch");
        }
        return noteService.updateNote(note);
    }

    @Operation(summary = "Delete a note", description = "Deletes a note by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Note successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable UUID id) {
        noteService.deleteNoteById(id);
    }
}
