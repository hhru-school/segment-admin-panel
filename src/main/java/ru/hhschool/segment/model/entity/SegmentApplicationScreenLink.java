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
public class SegmentApplicationScreenLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "layer_id")
  private Layer layer;
  @OneToOne(fetch = FetchType.LAZY)
  private SegmentApplicationScreenLink oldQuestionRequiredLink;
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
  @Column(name = "screen_position", nullable = false)
  private Integer screenPosition;

  public SegmentApplicationScreenLink() {}

  public SegmentApplicationScreenLink(Long id, Layer layer, SegmentApplicationScreenLink oldQuestionRequiredLink, Segment segment, Entrypoint entrypoint, Application application, Screen screen, Integer screenPosition) {
    this.id = id;
    this.layer = layer;
    this.oldQuestionRequiredLink = oldQuestionRequiredLink;
    this.segment = segment;
    this.entrypoint = entrypoint;
    this.application = application;
    this.screen = screen;
    this.screenPosition = screenPosition;
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

  public SegmentApplicationScreenLink getOldQuestionRequiredLink() {
    return oldQuestionRequiredLink;
  }

  public void setOldQuestionRequiredLink(SegmentApplicationScreenLink oldQuestionRequiredLink) {
    this.oldQuestionRequiredLink = oldQuestionRequiredLink;
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

  public Integer getScreenPosition() {
    return screenPosition;
  }

  public void setScreenPosition(Integer screenPosition) {
    this.screenPosition = screenPosition;
  }
}
