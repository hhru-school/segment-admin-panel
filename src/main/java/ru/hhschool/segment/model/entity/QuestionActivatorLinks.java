package ru.hhschool.segment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class QuestionActivatorLinks implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @ManyToOne
  private Layer layer;
  @ManyToOne
  private Segment segment;
  @ManyToOne
  private Entrypoint entrypoint;
  @ManyToOne
  private Question question;
  private boolean questionRequired;
  private boolean questionVisibility;

  public QuestionActivatorLinks() {
  }

  public QuestionActivatorLinks(Layer layer, Segment segment, Entrypoint entrypoint, Question question, boolean questionRequired, boolean questionVisibility) {
    this.layer = layer;
    this.segment = segment;
    this.entrypoint = entrypoint;
    this.question = question;
    this.questionRequired = questionRequired;
    this.questionVisibility = questionVisibility;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Layer getLayer() {
    return layer;
  }

  public void setLayer(Layer layer) {
    this.layer = layer;
  }

  public Segment getSegment() {
    return segment;
  }

  public void setSegment(Segment segment) {
    this.segment = segment;
  }

  public Entrypoint getEntrypoint() {
    return entrypoint;
  }

  public void setEntrypoint(Entrypoint entrypoint) {
    this.entrypoint = entrypoint;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public boolean isQuestionRequired() {
    return questionRequired;
  }

  public void setQuestionRequired(boolean questionRequired) {
    this.questionRequired = questionRequired;
  }

  public boolean isQuestionVisibility() {
    return questionVisibility;
  }

  public void setQuestionVisibility(boolean questionVisibility) {
    this.questionVisibility = questionVisibility;
  }
}
