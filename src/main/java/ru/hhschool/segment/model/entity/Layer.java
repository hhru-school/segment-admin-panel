package ru.hhschool.segment.model.entity;

import org.hibernate.annotations.Type;
import ru.hhschool.segment.model.enums.LayerStateType;

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
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "layers")
public class Layer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "layer_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @ManyToOne(fetch = FetchType.LAZY)
  private Layer parent;
  @Column(name = "description")
  private String description;
  @Enumerated(EnumType.STRING)
  @Column(name = "state")
  private LayerStateType state;
  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;
  @Type(type = "list-array")
  @Column(
      name = "platforms",
      columnDefinition = "bigint[]"
  )
  private List<Long> platforms;

  @Column(name = "stabled_time")
  private LocalDateTime stabledTime;

  public Layer() {
  }

  public Layer(String title, Layer parent, String description, LayerStateType state, LocalDateTime createTime, List<Long> platforms) {
    this.title = title;
    this.parent = parent;
    this.description = description;
    this.state = state;
    this.createTime = createTime;
    this.platforms = platforms;
  }

  public Long getId() {
    return id;
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

  public Layer getParent() {
    return parent;
  }

  public void setParent(Layer parent) {
    this.parent = parent;
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
    if (state == LayerStateType.STABLE) {
      this.stabledTime = LocalDateTime.now();
      this.state = state;
    }
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public List<Long> getPlatforms() {
    return platforms;
  }

  public void setPlatforms(List<Long> platforms) {
    this.platforms = platforms;
  }

  public LocalDateTime getStabledTime() {
    return stabledTime;
  }
}
