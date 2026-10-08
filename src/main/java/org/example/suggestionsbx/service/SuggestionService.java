package org.example.suggestionsbx.service;

import java.util.List;
import java.util.stream.Collectors;
import org.example.suggestionsbx.dto.SuggestionRequestDTO;
import org.example.suggestionsbx.dto.SuggestionResponseDTO;
import org.example.suggestionsbx.model.Suggestion;
import org.example.suggestionsbx.repository.SuggestionRepository;
import org.springframework.stereotype.Service;

@Service
public class SuggestionService {

  private final SuggestionRepository repository;

  /**
   * Constructs the service with the required repository dependency.
   *
   * @param repository repository the persistence repository for {@link Suggestion} entities
   */
  public SuggestionService(SuggestionRepository repository) {
    this.repository = repository;
  }

  /**
   * Creates a new suggestion based on the incoming request data, persists it, and returns the
   * corresponding response DTO.
   *
   * @param dto the data transfer object containing the suggestion details
   * @return a {@link SuggestionResponseDTO} representing the newly saved suggestion
   */
  public SuggestionResponseDTO createSuggestion(SuggestionRequestDTO dto) {
    Suggestion suggestion =
        new Suggestion(dto.getTitle(), dto.getContent(), dto.getSolution(), dto.getRating());

    Suggestion saved = repository.save(suggestion);
    return mapToResponse(saved);
  }

  /**
   * Retrieves all suggestions ordered by creation date in descending order.
   *
   * @return a list of {@link SuggestionResponseDTO} objects representing all recorded suggestions
   */
  public List<SuggestionResponseDTO> getAllSuggestions() {
    return repository.findAllByOrderByCreatedAtDesc().stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
  }

  /**
   * Maps a persistent {@link Suggestion} entity to a {@link SuggestionResponseDTO}.
   *
   * @param s the suggestion entity to be converted
   * @return the mapped response data transfer object
   */
  private SuggestionResponseDTO mapToResponse(Suggestion s) {
    return new SuggestionResponseDTO(
        s.getId(), s.getTitle(), s.getContent(), s.getSolution(), s.getRating(), s.getCreatedAt());
  }
}
