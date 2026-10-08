package org.example.suggestionsbx.repository;

import java.util.List;
import org.example.suggestionsbx.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

  /**
   * Retrieves all suggestions sorted by creation date in descending order, ensuring the newest
   * suggestions appear first.
   *
   * @return a list of {@link Suggestion} entities ordered from newest to oldest
   */
  List<Suggestion> findAllByOrderByCreatedAtDesc();
}
