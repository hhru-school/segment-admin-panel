package ru.hhschool.segment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question_required_links")
public class QuestionRequiredLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @OneToOne(fetch = FetchType.LAZY)
  private QuestionRequiredLink oldQuestionRequiredLink;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private Segment segment;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_state_id")
  SegmentStateLink segmentStateLink;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;
  @Column(name = "question_required", nullable = false)
  private boolean questionRequired;

  public QuestionRequiredLink() {
  }

  public QuestionRequiredLink(
      QuestionRequiredLink oldQuestionRequiredLink,
      Segment segment,
      SegmentStateLink segmentStateLink,
      Question question,
      boolean questionRequired
  ) {
    this.oldQuestionRequiredLink = oldQuestionRequiredLink;
    this.segment = segment;
    this.segmentStateLink = segmentStateLink;
    this.question = question;
    this.questionRequired = questionRequired;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public SegmentStateLink getSegmentStateLink() {
    return segmentStateLink;
  }

  public void setSegmentStateLink(SegmentStateLink segmentStateLink) {
    this.segmentStateLink = segmentStateLink;
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
