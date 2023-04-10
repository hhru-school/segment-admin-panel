package ru.hhschool.segment.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Entrypoint implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  private String title;
  private String description;
  private String Type;
  @ManyToOne
  private Layer layer;
  @OneToMany(
      mappedBy = "entrypoint",
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST,
          CascadeType.DETACH,
          CascadeType.REFRESH}
  )
  private List<QuestionActivatorLinks> questionActivatorLinksList;

  public Entrypoint() {
  }

  public Entrypoint(String title, String description, String type, Layer layer) {
    this.title = title;
    this.description = description;
    Type = type;
    this.layer = layer;
  }

  public String getType() {
    return Type;
  }

  public void setType(String type) {
    Type = type;
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

  public Layer getLayer() {
    return layer;
  }

  public void setLayer(Layer layer) {
    this.layer = layer;
  }
}
