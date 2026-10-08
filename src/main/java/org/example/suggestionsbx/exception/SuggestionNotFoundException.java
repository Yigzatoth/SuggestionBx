package org.example.suggestionsbx.exception;

public class SuggestionNotFoundException extends RuntimeException {
  public SuggestionNotFoundException(Long id) {
    super("Suggestion with ID " + id + " not found");
  }
}
