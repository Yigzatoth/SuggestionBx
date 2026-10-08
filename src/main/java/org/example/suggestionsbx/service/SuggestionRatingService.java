package org.example.suggestionsbx.service;

import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.example.suggestionsbx.exception.AlreadyRatedException;
import org.example.suggestionsbx.exception.SuggestionNotFoundException;
import org.example.suggestionsbx.model.Suggestion;
import org.example.suggestionsbx.model.SuggestionRating;
import org.example.suggestionsbx.repository.SuggestionRatingRepository;
import org.example.suggestionsbx.repository.SuggestionRepository;
import org.springframework.stereotype.Service;

@Service
public class SuggestionRatingService {

  private final SuggestionRatingRepository ratingRepository;
  private final SuggestionRepository suggestionRepository;

  public SuggestionRatingService(
      SuggestionRatingRepository ratingRepository, SuggestionRepository suggestionRepository) {
    this.ratingRepository = ratingRepository;
    this.suggestionRepository = suggestionRepository;
  }

  /**
   * Identifies the suggestion allowing it to be rated. In theory does not allow several votes from
   * the same user.
   *
   * @param suggestionId the unique identifier of the suggestion to be rated
   * @param ratingValue the score, only between 1 and 5 (represented by stars in frontend)
   * @param nickname only optional, displays the voter name.
   * @return the saved {@link SuggestionRating} entity containing the vote details.
   * @throws IllegalArgumentException if the rating value is out of bounds
   * @throws AlreadyRatedException if the user has already voted on this suggestions
   */
  public SuggestionRating addRating(
      Long suggestionId, int ratingValue, String nickname, HttpServletRequest request) {
    // validates if the feedback exists
    Suggestion suggestion =
        suggestionRepository
            .findById(suggestionId)
            .orElseThrow(() -> new SuggestionNotFoundException(suggestionId));

    // generates the fingerprint (ip + user-agent)
    String fingerprint = generateVoterFingerprint(request);

    // verifies if the device as already voted for the suggestion
    boolean alreadyVoted =
        ratingRepository.existsBySuggestionIdAndVoterFingerprint(suggestionId, fingerprint);
    if (alreadyVoted) {
      throw new AlreadyRatedException("You have already rated this suggestion");
    }

    if (ratingValue < 1 || ratingValue > 5) {
      throw new IllegalArgumentException("Rating value must be between 1 and 5");
    }

    // creates and saves the new vote
    SuggestionRating rating = new SuggestionRating(suggestion, fingerprint, nickname, ratingValue);
    return ratingRepository.save(rating);
  }

  private String generateVoterFingerprint(HttpServletRequest request) {
    String clientIp = request.getRemoteAddr();
    String userAgent = request.getHeader("User-Agent");
    String rawData = clientIp + "|" + (userAgent != null ? userAgent : "unknown");

    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(rawData.getBytes(StandardCharsets.UTF_8));
      StringBuilder hexString = new StringBuilder();
      for (byte b : hashBytes) {
        hexString.append(String.format("%02x", b));
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error generating voter fingerprint", e);
    }
  }
}
