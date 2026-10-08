package org.example.suggestionsbx.dto;

import java.time.LocalDateTime;

public class SuggestionResponseDTO {

  private Long id;
  private String title;
  private String content;
  private String solution;
  private int rating;
  private LocalDateTime createdAt;

  public SuggestionResponseDTO(
      Long id, String title, String content, String solution, int rating, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.solution = solution;
    this.rating = rating;
    this.createdAt = createdAt;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getSolution() {
    return solution;
  }

  public int getRating() {
    return rating;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
