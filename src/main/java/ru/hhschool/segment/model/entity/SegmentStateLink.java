package ru.hhschool.segment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import ru.hhschool.segment.model.enums.StateType;

@Entity
@Table(name = "segment_state_links")
public class SegmentStateLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @OneToOne(fetch = FetchType.LAZY)
  private SegmentStateLink oldSegmentStateLink;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "layer_id")
  private Layer layer;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private Segment segment;
  @Enumerated(EnumType.STRING)
  @Column(name = "state", nullable = false)
  private StateType state;

  public SegmentStateLink() {
  }

  public SegmentStateLink(SegmentStateLink oldSegmentStateLink, Layer layer, Segment segment, StateType state) {
    this.oldSegmentStateLink = oldSegmentStateLink;
    this.layer = layer;
    this.segment = segment;
    this.state = state;
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

  public SegmentStateLink getOldSegmentStateLink() {
    return oldSegmentStateLink;
  }

  public void setOldSegmentStateLink(SegmentStateLink oldSegmentStateLink) {
    this.oldSegmentStateLink = oldSegmentStateLink;
  }

  public Segment getSegment() {
    return segment;
  }

  public void setSegment(Segment segment) {
    this.segment = segment;
  }

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }
}
