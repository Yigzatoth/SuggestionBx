package org.example.suggestionsbx.repository;

import org.example.suggestionsbx.model.SuggestionRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRatingRepository extends JpaRepository<SuggestionRating, Long> {

  /**
   * Checks whether a rating already exists for a specific suggestion cast by a distinct device
   * fingerprint. Used to enforce the one-vote-per-device constraint.
   *
   * @param suggestionId the unique identifier of the suggestion
   * @param voterFingerprint the secure device fingerprint hash
   * @return true if a matching rating exists, false otherwise
   */
  boolean existsBySuggestionIdAndVoterFingerprint(Long suggestionId, String voterFingerprint);
}
