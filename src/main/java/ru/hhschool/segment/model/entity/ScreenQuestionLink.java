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
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

@Entity
@Table(name = "screen_question_links")
public class ScreenQuestionLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @OneToOne(fetch = FetchType.LAZY)
  private ScreenQuestionLink oldScreenQuestionLink;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_screen_entrypoint_id")
  private SegmentScreenEntrypointScreenLink segmentScreenEntrypointScreenLink;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;
  @Column(name = "question_position", nullable = false)
  private Integer questionPosition;
  @Column(name = "question_visibility", nullable = false)
  private QuestionVisibilityType questionVisibility;

  public ScreenQuestionLink() {
  }

  public ScreenQuestionLink(
      Long id,
      ScreenQuestionLink oldScreenQuestionLink,
      SegmentScreenEntrypointScreenLink segmentScreenEntrypointScreenLink,
      Question question,
      Integer questionPosition,
      QuestionVisibilityType questionVisibility
  ) {
    this.id = id;
    this.oldScreenQuestionLink = oldScreenQuestionLink;
    this.segmentScreenEntrypointScreenLink = segmentScreenEntrypointScreenLink;
    this.question = question;
    this.questionPosition = questionPosition;
    this.questionVisibility = questionVisibility;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ScreenQuestionLink getOldScreenQuestionLink() {
    return oldScreenQuestionLink;
  }

  public void setOldScreenQuestionLink(ScreenQuestionLink oldScreenQuestionLink) {
    this.oldScreenQuestionLink = oldScreenQuestionLink;
  }

  public SegmentScreenEntrypointScreenLink getSegmentScreenEntrypointScreenLink() {
    return segmentScreenEntrypointScreenLink;
  }

  public void setSegmentScreenEntrypointScreenLink(SegmentScreenEntrypointScreenLink segmentScreenEntrypointScreenLink) {
    this.segmentScreenEntrypointScreenLink = segmentScreenEntrypointScreenLink;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public Integer getQuestionPosition() {
    return questionPosition;
  }

  public void setQuestionPosition(Integer questionPosition) {
    this.questionPosition = questionPosition;
  }

  public QuestionVisibilityType getQuestionVisibility() {
    return questionVisibility;
  }

  public void setQuestionVisibility(QuestionVisibilityType questionVisibility) {
    this.questionVisibility = questionVisibility;
  }
}
