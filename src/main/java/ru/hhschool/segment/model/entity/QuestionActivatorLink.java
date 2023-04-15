package ru.hhschool.segment.model.entity;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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
  @Column(name = "segment_id")
  private Long segmentId;
  @Column(name = "question_id")
  private Long questionId;
  @Column(name = "entrypoint_id")
  private Long entrypointId;

  public QuestionActivatorLink() {
  }

  public QuestionActivatorLink(boolean questionRequired, QuestionVisibilityType questionVisibility, Long layerId, Long segmentId, Long questionId, Long entrypointId) {
    this.questionRequired = questionRequired;
    this.questionVisibility = questionVisibility;
    this.layerId = layerId;
    this.segmentId = segmentId;
    this.questionId = questionId;
    this.entrypointId = entrypointId;
  }

  public Long getEntrypointId() {
    return entrypointId;
  }

  public void setEntrypointId(Long entrypointId) {
    this.entrypointId = entrypointId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuestionActivatorLink that = (QuestionActivatorLink) o;
    return id.equals(that.id) && Objects.equals(layerId, that.layerId) && Objects.equals(segmentId, that.segmentId) && Objects.equals(questionId, that.questionId) && Objects.equals(entrypointId, that.entrypointId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, layerId, segmentId, questionId, entrypointId);
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

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }
}
