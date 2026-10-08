package org.example.suggestionsbx.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "Suggestion_ratings",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"suggestion_id", "voter_fingerprint"})})
public class SuggestionRating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "suggestion_id", nullable = false)
  private Suggestion suggestion;

  @Column(name = "voter_fingerprint", nullable = false)
  private String voterFingerprint;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "rating_value", nullable = false)
  private int ratingValue;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  /** Default protected constructor required by JPA. */
  public SuggestionRating() {}

  /**
   * Constructs a new SuggestionRating with the specified details.
   *
   * @param suggestion the {@link Suggestion} entity being rated
   * @param voterFingerprint the secure device fingerprint hash of the voter
   * @param nickname an optional display name provided by the voter
   * @param ratingValue the numerical rating score assigned (typically 1 to 5)
   */
  public SuggestionRating(
      Suggestion suggestion, String voterFingerprint, String nickname, int ratingValue) {
    this.suggestion = suggestion;
    this.voterFingerprint = voterFingerprint;
    this.nickname = nickname;
    this.ratingValue = ratingValue;
    this.createdAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Suggestion getSuggestion() {
    return suggestion;
  }

  public void setSuggestion(Suggestion suggestion) {
    this.suggestion = suggestion;
  }

  public String getVoterFingerprint() {
    return voterFingerprint;
  }

  public void setVoterFingerprint(String voterFingerprint) {
    this.voterFingerprint = voterFingerprint;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public int getRatingValue() {
    return ratingValue;
  }

  public void setRatingValue(int ratingValue) {
    this.ratingValue = ratingValue;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
