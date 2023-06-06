package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.AnswerType;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "answers")
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "answer_id", nullable = false, unique = true)
  private Long id;
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
  @Type(type = "list-array")
  @Column(
      name = "open_questions",
      columnDefinition = "bigint[]"
  )
  private List<Long> openQuestions;

  public Answer() {
  }

  public Answer(String title, String positiveTitle, AnswerType type, boolean defaultAnswer, boolean skipAtResult, List<Long> openQuestions) {
    this.title = title;
    this.positiveTitle = positiveTitle;
    this.type = type;
    this.defaultAnswer = defaultAnswer;
    this.skipAtResult = skipAtResult;
    this.openQuestions = openQuestions;
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

  public List<Long> getOpenQuestions() {
    return openQuestions;
  }

  public void setOpenQuestions(List<Long> openQuestions) {
    this.openQuestions = openQuestions;
  }
}
