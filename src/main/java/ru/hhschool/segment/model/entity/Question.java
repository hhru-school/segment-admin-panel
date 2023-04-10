package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Question implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  private String title;
  private String description;
  private String type;
  private boolean required;
  private String visibility;

  @ManyToOne
  private Layer layer;

  @Type(type = "list-array")
  @Column(
      name = "possibleansweridlist",
      columnDefinition = "bigint[]"
  )
  private List<Long> possibleAnswerIdList;

  public Question() {
  }

  public Question(String title, String description, String type, boolean required, String visibility, Layer layer, List<Long> possibleAnswerIdList) {
    this.title = title;
    this.description = description;
    this.type = type;
    this.required = required;
    this.visibility = visibility;
    this.layer = layer;
    this.possibleAnswerIdList = possibleAnswerIdList;
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

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public Layer getLayer() {
    return layer;
  }

  public void setLayer(Layer layer) {
    this.layer = layer;
  }

  public List<Long> getPossibleAnswerIdList() {
    return possibleAnswerIdList;
  }

  public void setPossibleAnswerIdList(List<Long> possibleAnswerIdList) {
    this.possibleAnswerIdList = possibleAnswerIdList;
  }
}
