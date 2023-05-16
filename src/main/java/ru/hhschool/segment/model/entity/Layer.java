package ru.hhschool.segment.model.entity;

import ru.hhschool.segment.model.enums.LayerStateType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "layers")
public class Layer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "layer_id", nullable = false, unique = true)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private Layer parent;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description")
  private String description;
  @Enumerated(EnumType.STRING)
  @Column(name = "state")
  private LayerStateType state;
  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private List<Segment> segmentList;

  public Layer() {}

  public Layer(Long id, Layer parent, String title, String description, LayerStateType state, LocalDateTime createTime, List<Segment> segmentList) {
    this.id = id;
    this.parent = parent;
    this.title = title;
    this.description = description;
    this.state = state;
    this.createTime = createTime;
    this.segmentList = segmentList;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Layer getParent() {
    return parent;
  }

  public void setParent(Layer parent) {
    this.parent = parent;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LayerStateType getState() {
    return state;
  }

  public void setState(LayerStateType state) {
    this.state = state;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public List<Segment> getSegmentList() {
    return segmentList;
  }

  public void setSegmentList(List<Segment> segmentList) {
    this.segmentList = segmentList;
  }
}
