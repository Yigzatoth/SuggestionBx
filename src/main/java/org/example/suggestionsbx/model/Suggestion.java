package org.example.suggestionsbx.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "suggestions")
public class Suggestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(columnDefinition = "TEXT")
  private String content; // Feedback

  @Column(columnDefinition = "TEXT")
  private String solution; // Proposed Solution

  private int rating; // 1 up to 5 stars

  private LocalDateTime createdAt;

  /** default protected constructor required by JPA */
  public Suggestion() {}

  /**
   * constructs a new suggestion with the specific details.
   *
   * @param title the short summary or title of the suggestion
   * @param content the detailed description of the problem / feedback
   * @param solution the theoretical proposed solution to fix the problem
   * @param rating initial score assigned from 1 to 5
   */
  public Suggestion(String title, String content, String solution, int rating) {
    this.title = title;
    this.content = content;
    this.solution = solution;
    this.rating = rating;
    this.createdAt = LocalDateTime.now(); // Directly written in backend
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSolution() {
    return solution;
  }

  public void setSolution(String solution) {
    this.solution = solution;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
