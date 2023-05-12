package ru.hhschool.segment.model.entity;

import javax.persistence.*;
import java.io.Serializable;

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

  public Entrypoint(String title, String description, Long layerId) {
    this.title = title;
    this.description = description;
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
}
