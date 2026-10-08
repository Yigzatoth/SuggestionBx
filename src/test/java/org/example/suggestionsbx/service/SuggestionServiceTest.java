package org.example.suggestionsbx.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.example.suggestionsbx.dto.SuggestionRequestDTO;
import org.example.suggestionsbx.dto.SuggestionResponseDTO;
import org.example.suggestionsbx.model.Suggestion;
import org.example.suggestionsbx.repository.SuggestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for {@link SuggestionService}. Isolates business logic by mocking the underlying
 * repository layer.
 */
class SuggestionServiceTest {

  @Mock private SuggestionRepository repository;

  @InjectMocks private SuggestionService service;

  @BeforeEach
  void setUp() {
    // Initializes Mockito annotations before each test execution
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createSuggestion_ShouldReturnMappedResponseDTO() {
    // Arrange (Prepares entry data and how mock should behave)
    SuggestionRequestDTO requestDTO = new SuggestionRequestDTO();
    requestDTO.setTitle("Improve Login Speed");
    requestDTO.setContent("Login takes too long on mobile.");
    requestDTO.setSolution("Optimize database index on users table.");
    requestDTO.setRating(5);

    Suggestion savedEntity =
        new Suggestion(
            requestDTO.getTitle(),
            requestDTO.getContent(),
            requestDTO.getSolution(),
            requestDTO.getRating());

    // Simulates repository behaviour (after saving, returns the entity)
    when(repository.save(any(Suggestion.class))).thenReturn(savedEntity);

    // Act (Execute the method test)
    SuggestionResponseDTO result = service.createSuggestion(requestDTO);

    // Assert (Validate the expected results)
    assertNotNull(result);
    assertEquals("Improve Login Speed", result.getTitle());
    assertEquals("Login takes too long on mobile.", result.getContent());
    assertEquals("Optimize database index on users table.", result.getSolution());

    // Checks if the repository was called one time.
    verify(repository, times(1)).save(any(Suggestion.class));
  }
}
