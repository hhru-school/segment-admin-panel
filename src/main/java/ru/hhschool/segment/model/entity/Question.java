package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.AnswersNumberType;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "questions")
public class Question implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "question_title", nullable = false)
  private String title;
  @Column(name = "description")
  private String description;
  @Enumerated(EnumType.STRING)
  @Column(name = "question_type", nullable = false)
  private AnswersNumberType type;
  @Column(name = "question_required")
  private boolean required;
  @Enumerated(EnumType.STRING)
  @Column(name = "question_visibility")
  private QuestionVisibilityType questionVisibilityType;
  @Type(type = "list-array")
  @Column(
      name = "possible_answers",
      columnDefinition = "bigint[]"
  )
  private List<Long> possibleAnswerIdList;
  @Column(name = "layer_id")
  private Long layerId;

  @Column(name = "resume_field")
  private boolean resumeField;

  public Question(
      Long id,
      String title,
      String description,
      AnswersNumberType type,
      boolean required,
      QuestionVisibilityType questionVisibilityType,
      List<Long> possibleAnswerIdList,
      Long layerId,
      boolean resumeField
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.required = required;
    this.questionVisibilityType = questionVisibilityType;
    this.possibleAnswerIdList = possibleAnswerIdList;
    this.layerId = layerId;
    this.resumeField = resumeField;
  }

  public Question() {
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

  public AnswersNumberType getType() {
    return type;
  }

  public void setType(AnswersNumberType type) {
    this.type = type;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public QuestionVisibilityType getQuestionVisibilityType() {
    return questionVisibilityType;
  }

  public void setQuestionVisibilityType(QuestionVisibilityType questionVisibilityType) {
    this.questionVisibilityType = questionVisibilityType;
  }

  public List<Long> getPossibleAnswerIdList() {
    return possibleAnswerIdList;
  }

  public void setPossibleAnswerIdList(List<Long> possibleAnswerIdList) {
    this.possibleAnswerIdList = possibleAnswerIdList;
  }

  public Long getLayerId() {
    return layerId;
  }

  public void setLayerId(Long layerId) {
    this.layerId = layerId;
  }

  public boolean isResumeField() {
    return resumeField;
  }

  public void setResumeField(boolean resumeField) {
    this.resumeField = resumeField;
  }
}
