package org.example.suggestionsbx.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.suggestionsbx.model.SuggestionRating;
import org.example.suggestionsbx.service.SuggestionRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suggestions")
@CrossOrigin(origins = "*")
public class SuggestionRatingController {

  private final SuggestionRatingService ratingService;

  /**
   * Constructs the rating controller with the required rating service dependency.
   *
   * @param ratingService the service handling rating business logic and anti-spam validation
   */
  public SuggestionRatingController(SuggestionRatingService ratingService) {
    this.ratingService = ratingService;
  }

  /**
   * Handles HTTP POST requests to submit a rating for a specific suggestion. Uses the incoming HTTP
   * request context to generate a secure server-side fingerprint.
   *
   * @param id the unique identifier of the suggestion being rated
   * @param ratingValue the score assigned by the user (typically 1 to 5)
   * @param nickname an optional display name provided by the user
   * @param request the HTTP servlet request used to extract save data
   * @return a {@link ResponseEntity} containing the saved {@link SuggestionRating
   */
  @PostMapping("/{id}/rate")
  public ResponseEntity<SuggestionRating> rateSuggestion(
      @PathVariable Long id,
      @RequestParam int ratingValue,
      @RequestParam(required = false) String nickname,
      HttpServletRequest request) {
    SuggestionRating rating = ratingService.addRating(id, ratingValue, nickname, request);
    return ResponseEntity.ok(rating);
  }
}
