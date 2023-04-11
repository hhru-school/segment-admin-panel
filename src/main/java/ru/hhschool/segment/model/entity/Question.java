package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.QuestionType;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
  private QuestionType type;
  @Column(name = "question_required")
  private boolean requiredType;
  @Enumerated(EnumType.STRING)
  @Column(name = "question_visibility")
  private QuestionVisibilityType questionVisibilityType;
  @Type(type = "list-array")
  @Column(
      name = "possible_answers",
      columnDefinition = "bigint[]"
  )
  private List<Long> possibleAnswerIdList;

  public List<QuestionActivatorLinks> getQuestionActivatorLinksList() {
    return questionActivatorLinksList;
  }

  public void setQuestionActivatorLinksList(List<QuestionActivatorLinks> questionActivatorLinksList) {
    this.questionActivatorLinksList = questionActivatorLinksList;
  }

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private List<QuestionActivatorLinks> questionActivatorLinksList;

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

  public QuestionType getType() {
    return type;
  }

  public void setType(QuestionType type) {
    this.type = type;
  }

  public boolean isRequiredType() {
    return requiredType;
  }

  public void setRequiredType(boolean requiredType) {
    this.requiredType = requiredType;
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
}
