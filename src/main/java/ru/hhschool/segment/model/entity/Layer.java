package ru.hhschool.segment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Layer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  private String title;
  private String description;
  private boolean Stable;
  private boolean Archive;
  private boolean Deleted;
  private LocalDateTime createTime;
  @ManyToOne
  private Layer parentLayer;
  @OneToMany(mappedBy = "parentLayer")
  private List<Layer> childrenLayerList;

  public Long getId() {
    return id;
  }

  public Layer() {
  }

  public void setId(Long id) {
    this.id = id;
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

  public boolean isStable() {
    return Stable;
  }

  public void setStable(boolean stable) {
    Stable = stable;
  }

  public boolean isArchive() {
    return Archive;
  }

  public Layer(String title, String description, boolean stable, boolean archive, boolean deleted, LocalDateTime createTime, Layer parentLayer, List<Layer> childrenLayerList) {
    this.title = title;
    this.description = description;
    Stable = stable;
    Archive = archive;
    Deleted = deleted;
    this.createTime = createTime;
    this.parentLayer = parentLayer;
    this.childrenLayerList = childrenLayerList;
  }

  public void setArchive(boolean archive) {
    Archive = archive;
  }

  public boolean isDeleted() {
    return Deleted;
  }

  public void setDeleted(boolean deleted) {
    Deleted = deleted;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public Layer getParentLayer() {
    return parentLayer;
  }

  public void setParentLayer(Layer parentLayer) {
    this.parentLayer = parentLayer;
  }

  public List<Layer> getChildrenLayerList() {
    return childrenLayerList;
  }

  public void setChildrenLayerList(List<Layer> childrenLayerList) {
    this.childrenLayerList = childrenLayerList;
  }
}
