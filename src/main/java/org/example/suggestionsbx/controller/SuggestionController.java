package org.example.suggestionsbx.controller;

import java.util.List;
import org.example.suggestionsbx.dto.SuggestionRequestDTO;
import org.example.suggestionsbx.dto.SuggestionResponseDTO;
import org.example.suggestionsbx.service.SuggestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suggestions")
@CrossOrigin(origins = "*")
public class SuggestionController {

  private final SuggestionService service;

  /**
   * Constructs the controller with the required suggestion service dependency.
   *
   * @param service the suggestion service handling business logic
   */
  public SuggestionController(SuggestionService service) {
    this.service = service;
  }

  /**
   * Handles HTTP POST requests to create a new suggestion.
   *
   * @param dto the request body containing the suggestion details
   * @return a {@link ResponseEntity} containing the created {@link SuggestionResponseDTO} and an
   *     HTTP 201 (Created) status
   */
  @PostMapping
  public ResponseEntity<SuggestionResponseDTO> create(@RequestBody SuggestionRequestDTO dto) {
    SuggestionResponseDTO created = service.createSuggestion(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  /**
   * Handles HTTP GET requests to retrieve all available suggestions.
   *
   * @return a {@link ResponseEntity} containing a list of {@link SuggestionResponseDTO} objects and
   *     an HTTP 200 (OK) status
   */
  @GetMapping
  public ResponseEntity<List<SuggestionResponseDTO>> getAll() {
    List<SuggestionResponseDTO> list = service.getAllSuggestions();
    return ResponseEntity.ok(list);
  }
}
