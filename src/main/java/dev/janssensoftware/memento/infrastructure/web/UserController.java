package dev.janssensoftware.memento.infrastructure.web;

import dev.janssensoftware.memento.application.usecase.UserUseCase;
import dev.janssensoftware.memento.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @Operation(summary = "Create a new user", description = "Stores a new user in the database.")
    @ApiResponse(responseCode = "201", description = "User successfully created")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userUseCase.createUser(user);
    }

    @Operation(summary = "Get a user by ID", description = "Retrieves a user based on its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userUseCase.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Operation(summary = "Get a user by username", description = "Finds a user by their username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userUseCase.getUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Operation(summary = "Update a user", description = "Updates an existing user's information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "400", description = "ID mismatch")
    })
    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        if (!id.equals(user.getId())) {
            throw new IllegalArgumentException("ID mismatch");
        }
        return userUseCase.updateUser(user);
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        userUseCase.deleteUserById(id);
    }
}
