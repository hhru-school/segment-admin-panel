package ru.hhschool.segment.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

@Entity
@Table(name = "question_activate_links")
public class QuestionActivatorLink implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @Column(name = "question_required")
  private boolean questionRequired;
  @Enumerated(EnumType.STRING)
  @Column(name = "question_visibility")
  private QuestionVisibilityType questionVisibility;
  @Column(name = "layer_id")
  private Long layerId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "entrypoint_id")
  private Entrypoint entrypoint;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private Segment segment;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;

  public QuestionActivatorLink() {
  }

  public QuestionActivatorLink(
      Long id,
      boolean questionRequired,
      QuestionVisibilityType questionVisibility,
      Long layerId,
      Segment segment,
      Question question,
      Entrypoint entrypoint
  ) {
    this.id = id;
    this.questionRequired = questionRequired;
    this.questionVisibility = questionVisibility;
    this.layerId = layerId;
    this.segment = segment;
    this.question = question;
    this.entrypoint = entrypoint;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isQuestionRequired() {
    return questionRequired;
  }

  public void setQuestionRequired(boolean questionRequired) {
    this.questionRequired = questionRequired;
  }

  public QuestionVisibilityType getQuestionVisibility() {
    return questionVisibility;
  }

  public void setQuestionVisibility(QuestionVisibilityType questionVisibility) {
    this.questionVisibility = questionVisibility;
  }

  public Long getLayerId() {
    return layerId;
  }

  public void setLayerId(Long layerId) {
    this.layerId = layerId;
  }

  public Segment getSegment() {
    return segment;
  }

  public void setSegment(Segment segment) {
    this.segment = segment;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public Entrypoint getEntrypoint() {
    return entrypoint;
  }

  public void setEntrypoint(Entrypoint entrypoint) {
    this.entrypoint = entrypoint;
  }
}
