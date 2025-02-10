package dev.janssensoftware.memento.infrastructure.note.in.web;

import dev.janssensoftware.memento.infrastructure.note.in.web.dto.WebNoteDto;
import dev.janssensoftware.memento.adapter.note.in.web.NoteWebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteWebAdapter noteWebAdapter;

    @Operation(summary = "Create a new note", description = "Stores a new note in the database.")
    @ApiResponse(responseCode = "201", description = "Note successfully created")
    @PostMapping
    public WebNoteDto createNote(@RequestBody WebNoteDto note, Principal principal) {
        return noteWebAdapter.createNote(note, principal.getName());
    }

    @Operation(summary = "Get a note by ID", description = "Retrieves a note based on its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @GetMapping("/{id}")
    public WebNoteDto getNoteById(@PathVariable UUID id) {
        return noteWebAdapter.getNoteById(id).orElseThrow(() -> new IllegalArgumentException("Note not found"));
    }

    @Operation(summary = "Get all notes of the current user", description = "Retrieves all notes associated with the current user.")
    @ApiResponse(responseCode = "200", description = "Notes successfully retrieved")
    @GetMapping
    public List<WebNoteDto> getNotesByUser(Principal principal) {
        return noteWebAdapter.getAllNotesByUsername(principal.getName());
    }

    @Operation(summary = "Delete a note", description = "Deletes a note by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Note successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable UUID id) {
        noteWebAdapter.deleteNoteById(id);
    }

    @Operation(summary = "Patch multiple notes", description = "Updates multiple notes with new content.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notes successfully updated"),
            @ApiResponse(responseCode = "400", description = "ID mismatch")
    })
    @PatchMapping
    public List<WebNoteDto> patchNotes(@RequestBody List<WebNoteDto> notes, Principal principal) {
        return noteWebAdapter.patchNotes(notes, principal.getName());
    }
}
