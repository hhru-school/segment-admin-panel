package ru.hhschool.segment.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "question_required_links")
public class QuestionRequiredLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @Column(name = "question_required", nullable = false)
  private boolean questionRequired;

  @OneToOne(fetch = FetchType.LAZY)
  private QuestionRequiredLink oldQuestionRequiredLink;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "layer_id")
  private Layer layer;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private Segment segment;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "application_id")
  private Application application;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;

  public QuestionRequiredLink() {}

  public QuestionRequiredLink(Long id, boolean questionRequired, QuestionRequiredLink oldQuestionRequiredLink, Layer layer, Segment segment, Application application, Question question) {
    this.id = id;
    this.questionRequired = questionRequired;
    this.oldQuestionRequiredLink = oldQuestionRequiredLink;
    this.layer = layer;
    this.segment = segment;
    this.application = application;
    this.question = question;
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

  public QuestionRequiredLink getOldQuestionRequiredLink() {
    return oldQuestionRequiredLink;
  }

  public void setOldQuestionRequiredLink(QuestionRequiredLink oldQuestionRequiredLink) {
    this.oldQuestionRequiredLink = oldQuestionRequiredLink;
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
}
