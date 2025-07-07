package dev.janssensoftware.memento;

import dev.janssensoftware.memento.infrastructure.auth.in.web.dto.AuthResponseDto;
import dev.janssensoftware.memento.infrastructure.auth.in.web.dto.LoginRequestDto;
import dev.janssensoftware.memento.infrastructure.auth.in.web.dto.RegisterRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRegisterUser() {
        // Arrange
        RegisterRequestDto registerRequest = new RegisterRequestDto();
        registerRequest.setUsername("testuser");
        registerRequest.setPassword("password123");

        // Act
        ResponseEntity<AuthResponseDto> response = restTemplate.postForEntity(
                "/api/auth/register",
                registerRequest,
                AuthResponseDto.class
        );

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getToken()).isNotBlank();
    }

    @Test
    public void testLoginUser() {
        // Arrange - First register a user
        RegisterRequestDto registerRequest = new RegisterRequestDto();
        registerRequest.setUsername("loginuser");
        registerRequest.setPassword("password123");

        restTemplate.postForEntity(
                "/api/auth/register",
                registerRequest,
                AuthResponseDto.class
        );

        // Create login request
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setUsername("loginuser");
        loginRequest.setPassword("password123");

        // Act - Attempt to login
        ResponseEntity<AuthResponseDto> response = restTemplate.postForEntity(
                "/api/auth/login",
                loginRequest,
                AuthResponseDto.class
        );

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getToken()).isNotBlank();
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        // Arrange
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setUsername("nonexistentuser");
        loginRequest.setPassword("wrongpassword");

        // Act
        ResponseEntity<AuthResponseDto> response = restTemplate.postForEntity(
                "/api/auth/login",
                loginRequest,
                AuthResponseDto.class
        );

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void testRegisterDuplicateUser() {
        // Arrange
        RegisterRequestDto firstRegisterRequest = new RegisterRequestDto();
        firstRegisterRequest.setUsername("duplicateuser");
        firstRegisterRequest.setPassword("password123");

        // Register the user first time
        restTemplate.postForEntity(
                "/api/auth/register",
                firstRegisterRequest,
                AuthResponseDto.class
        );

        // Try to register the same user again
        RegisterRequestDto secondRegisterRequest = new RegisterRequestDto();
        secondRegisterRequest.setUsername("duplicateuser");
        secondRegisterRequest.setPassword("password456");

        // Act
        ResponseEntity<AuthResponseDto> response = restTemplate.postForEntity(
                "/api/auth/register",
                secondRegisterRequest,
                AuthResponseDto.class
        );

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }
}