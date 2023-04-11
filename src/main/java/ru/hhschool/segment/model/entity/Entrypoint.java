package ru.hhschool.segment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

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
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "entrypoint_id")
  private List<QuestionActivatorLinks> questionActivatorLinksList;

  public Long getId() {
    return id;
  }

  public List<QuestionActivatorLinks> getQuestionActivatorLinksList() {
    return questionActivatorLinksList;
  }

  public void setQuestionActivatorLinksList(List<QuestionActivatorLinks> questionActivatorLinksList) {
    this.questionActivatorLinksList = questionActivatorLinksList;
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
}
