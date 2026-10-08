package org.example.suggestionsbx.dto;

public class SuggestionRequestDTO {

  private String title;
  private String content;
  private String solution;
  private int rating;

  // Getters e Setters
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
}
