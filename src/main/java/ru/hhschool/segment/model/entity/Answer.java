package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.AnswerType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "answers")
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "answer_id", nullable = false, unique = true)
  private Long id;
  @Type(type = "list-array")
  @Column(
      name = "open_questions",
      columnDefinition = "bigint[]"
  )
  private List<Long> openQuestionList;
  @Column(name = "title")
  private String title;
  @Column(name = "positive_title")
  private String positiveTitle;
  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private AnswerType type;
  @Column(name = "default_answer", nullable = false)
  private boolean defaultAnswer;
  @Column(name = "skip_at_result")
  private boolean skipAtResult;

  public Answer() {}

  public Answer(Long id, List<Long> openQuestionList, String title, String positiveTitle, AnswerType type, boolean defaultAnswer, boolean skipAtResult) {
    this.id = id;
    this.openQuestionList = openQuestionList;
    this.title = title;
    this.positiveTitle = positiveTitle;
    this.type = type;
    this.defaultAnswer = defaultAnswer;
    this.skipAtResult = skipAtResult;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Long> getOpenQuestionList() {
    return openQuestionList;
  }

  public void setOpenQuestionList(List<Long> openQuestionList) {
    this.openQuestionList = openQuestionList;
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

  public AnswerType getType() {
    return type;
  }

  public void setType(AnswerType type) {
    this.type = type;
  }

  public boolean isDefaultAnswer() {
    return defaultAnswer;
  }

  public void setDefaultAnswer(boolean defaultAnswer) {
    this.defaultAnswer = defaultAnswer;
  }

  public boolean isSkipAtResult() {
    return skipAtResult;
  }

  public void setSkipAtResult(boolean skipAtResult) {
    this.skipAtResult = skipAtResult;
  }
}
