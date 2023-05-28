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
@Table(name = "segment_screen_entrypoint_links")
public class SegmentScreenEntrypointLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @OneToOne(fetch = FetchType.LAZY)
  private SegmentScreenEntrypointLink oldSegmentScreenEntrypointLink;
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
  @Column(name = "screen_position", nullable = false)
  private Integer screenPosition;

  public SegmentScreenEntrypointLink() {
  }

  public SegmentScreenEntrypointLink(
      SegmentScreenEntrypointLink oldSegmentScreenEntrypointLink,
      Layer layer,
      Segment segment,
      Entrypoint entrypoint,
      Screen screen,
      Integer screenPosition
  ) {
    this.oldSegmentScreenEntrypointLink = oldSegmentScreenEntrypointLink;
    this.layer = layer;
    this.segment = segment;
    this.entrypoint = entrypoint;
    this.screen = screen;
    this.screenPosition = screenPosition;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SegmentScreenEntrypointLink getOldSegmentScreenEntrypointScreenLink() {
    return oldSegmentScreenEntrypointLink;
  }

  public void setOldSegmentScreenEntrypointScreenLink(SegmentScreenEntrypointLink oldSegmentScreenEntrypointLink) {
    this.oldSegmentScreenEntrypointLink = oldSegmentScreenEntrypointLink;
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

  public Integer getScreenPosition() {
    return screenPosition;
  }

  public void setScreenPosition(Integer screenPosition) {
    this.screenPosition = screenPosition;
  }
}
