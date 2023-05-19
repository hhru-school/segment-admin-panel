package ru.hhschool.segment.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "question_required_links")
public class QuestionRequiredLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "layer_id")
  private Layer layer;
  @OneToOne(fetch = FetchType.LAZY)
  private QuestionRequiredLink oldQuestionRequiredLink;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private Segment segment;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "application_id")
  private Application application;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;
  @Column(name = "question_required", nullable = false)
  private boolean questionRequired;

  public QuestionRequiredLink() {}

  public QuestionRequiredLink(Long id, Layer layer, QuestionRequiredLink oldQuestionRequiredLink, Segment segment, Application application, Question question, boolean questionRequired) {
    this.id = id;
    this.layer = layer;
    this.oldQuestionRequiredLink = oldQuestionRequiredLink;
    this.segment = segment;
    this.application = application;
    this.question = question;
    this.questionRequired = questionRequired;
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

  public QuestionRequiredLink getOldQuestionRequiredLink() {
    return oldQuestionRequiredLink;
  }

  public void setOldQuestionRequiredLink(QuestionRequiredLink oldQuestionRequiredLink) {
    this.oldQuestionRequiredLink = oldQuestionRequiredLink;
  }

  public Segment getSegment() {
    return segment;
  }

  public void setSegment(Segment segment) {
    this.segment = segment;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
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
}