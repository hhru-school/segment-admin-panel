package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.QuestionType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
  @Enumerated(EnumType.STRING)
  private QuestionType type;
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

  @OneToMany(
      mappedBy = "question",
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST,
          CascadeType.DETACH,
          CascadeType.REFRESH}
  )
  private List<QuestionActivatorLinks>  questionActivatorLinks;

  public Question() {
  }

  public Question(String title, String description, QuestionType type, boolean required, String visibility, Layer layer, List<Long> possibleAnswerIdList) {
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

  public List<QuestionActivatorLinks> getQuestionActivatorLinks() {
    return questionActivatorLinks;
  }

  public void setQuestionActivatorLinks(List<QuestionActivatorLinks> questionActivatorLinks) {
    this.questionActivatorLinks = questionActivatorLinks;
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

  public QuestionType getType() {
    return type;
  }

  public void setType(QuestionType type) {
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
