package ru.hhschool.segment.model.entity;

import javax.persistence.*;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

@Entity
@Table(name = "screen_question_links")
public class ScreenQuestionLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "old_id")
  private ScreenQuestionLink oldScreenQuestionLink;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "layer_id")
  private Layer layer;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private Segment segment;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "entrypoint_id")
  private Entrypoint entrypoint;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "screen_id")
  private Screen screen;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;
  @Column(name = "question_position", nullable = false)
  private Integer questionPosition;
  @Enumerated(EnumType.STRING)
  @Column(name = "question_visibility", nullable = false)
  private QuestionVisibilityType questionVisibility;

  public ScreenQuestionLink() {
  }

  public ScreenQuestionLink(
      ScreenQuestionLink oldScreenQuestionLink,
      Layer layer,
      Segment segment,
      Entrypoint entrypoint,
      Screen screen,
      Question question,
      Integer questionPosition,
      QuestionVisibilityType questionVisibility
  ) {
    this.oldScreenQuestionLink = oldScreenQuestionLink;
    this.layer = layer;
    this.segment = segment;
    this.entrypoint = entrypoint;
    this.screen = screen;
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

  public Screen getScreen() {
    return screen;
  }

  public void setScreen(Screen screen) {
    this.screen = screen;
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
