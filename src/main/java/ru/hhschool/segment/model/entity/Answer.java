package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.AnswerType;

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
@Table(name = "answers")
public class Answer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "answer_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title")
  private String title;
  @Column(name = "positive_title")
  private String positiveTitle;
  @Enumerated(EnumType.STRING)
  @Column(name = "answer_type")
  private AnswerType answerType;
  @Column(name = "is_default_answer", nullable = false)
  private boolean isDefault;
  @Column(name = "skip_at_result")
  private boolean skipAtResult;
  @Type(type = "list-array")
  @Column(
      name = "open_questions",
      columnDefinition = "bigint[]"
  )
  private List<Long> openQuestionList;

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

  public String getPositiveTitle() {
    return positiveTitle;
  }

  public void setPositiveTitle(String positiveTitle) {
    this.positiveTitle = positiveTitle;
  }

  public AnswerType getAnswerType() {
    return answerType;
  }

  public void setAnswerType(AnswerType answerType) {
    this.answerType = answerType;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean aDefault) {
    isDefault = aDefault;
  }

  public boolean isSkipAtResult() {
    return skipAtResult;
  }

  public void setSkipAtResult(boolean skipAtResult) {
    this.skipAtResult = skipAtResult;
  }

  public List<Long> getOpenQuestionList() {
    return openQuestionList;
  }

  public void setOpenQuestionList(List<Long> openQuestionList) {
    this.openQuestionList = openQuestionList;
  }
}
