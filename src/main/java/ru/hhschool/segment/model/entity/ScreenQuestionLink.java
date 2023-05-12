package ru.hhschool.segment.model.entity;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "question_required_links")
public class ScreenQuestionLink implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @Column(name = "question_position", nullable = false)
  private Integer questionPosition;
  @Column(name = "question_visibility", nullable = false)
  private QuestionVisibilityType questionVisibility;

  @OneToOne(fetch = FetchType.LAZY)
  @Column(name = "old_id")
  private ScreenQuestionLink oldQuestionRequiredLink;
  @ManyToOne(fetch = FetchType.LAZY)
  @Column(name = "layer_id")
  private Layer layer;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private Segment segment;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "entrypoint_id")
  private Entrypoint entrypoint;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "application_id")
  private Application application;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "screen_id")
  private Screen screen;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;

  public ScreenQuestionLink(Long id,
                            Integer questionPosition,
                            QuestionVisibilityType questionVisibility,
                            ScreenQuestionLink oldQuestionRequiredLink,
                            Layer layer,
                            Segment segment,
                            Entrypoint entrypoint,
                            Application application,
                            Screen screen,
                            Question question) {
    this.id = id;
    this.questionPosition = questionPosition;
    this.questionVisibility = questionVisibility;
    this.oldQuestionRequiredLink = oldQuestionRequiredLink;
    this.layer = layer;
    this.segment = segment;
    this.entrypoint = entrypoint;
    this.application = application;
    this.screen = screen;
    this.question = question;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public ScreenQuestionLink getOldQuestionRequiredLink() {
    return oldQuestionRequiredLink;
  }

  public void setOldQuestionRequiredLink(ScreenQuestionLink oldQuestionRequiredLink) {
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

  public Entrypoint getEntrypoint() {
    return entrypoint;
  }

  public void setEntrypoint(Entrypoint entrypoint) {
    this.entrypoint = entrypoint;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
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
}
