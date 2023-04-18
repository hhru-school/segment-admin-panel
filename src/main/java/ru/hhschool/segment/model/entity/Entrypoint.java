package ru.hhschool.segment.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entrypoints")
public class Entrypoint implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "entrypoint_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description")
  private String description;
  @Column(name = "entrypoint_type")
  private String type;
  @Column(name = "layer_id")
  private Long layerId;

  public Entrypoint(String title, String description, String type, Long layerId) {
    this.title = title;
    this.description = description;
    this.type = type;
    this.layerId = layerId;
  }

  public Entrypoint() {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getLayerId() {
    return layerId;
  }

  public void setLayerId(Long layerId) {
    this.layerId = layerId;
  }
}
