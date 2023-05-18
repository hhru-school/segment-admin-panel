package ru.hhschool.segment.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ru.hhschool.segment.model.enums.LayerStateType;

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

  public Layer() {
  }

  public Layer(Long id, Layer parent, String title, String description, LayerStateType state, LocalDateTime createTime) {
    this.id = id;
    this.parent = parent;
    this.title = title;
    this.description = description;
    this.state = state;
    this.createTime = createTime;
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
}
