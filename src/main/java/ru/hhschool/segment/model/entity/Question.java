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
import ru.hhschool.segment.model.enums.AnswersNumberType;
import ru.hhschool.segment.model.enums.ResumeField;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "questions")
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description")
  private String description;
  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private ResumeField type;
  @Enumerated(EnumType.STRING)
  @Column(name = "answer_type", nullable = false)
  private AnswersNumberType answerType;
  @Type(type = "list-array")
  @Column(
      name = "possible_answers",
      columnDefinition = "bigint[]"
  )
  private List<Long> possibleAnswers;

  public Question() {
  }

  public Question(Long id, String title, String description, ResumeField type, AnswersNumberType answerType, List<Long> possibleAnswers) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.answerType = answerType;
    this.possibleAnswers = possibleAnswers;
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

  public ResumeField getType() {
    return type;
  }

  public void setType(ResumeField type) {
    this.type = type;
  }

  public AnswersNumberType getAnswerType() {
    return answerType;
  }

  public void setAnswerType(AnswersNumberType answerType) {
    this.answerType = answerType;
  }

  public List<Long> getPossibleAnswers() {
    return possibleAnswers;
  }

  public void setPossibleAnswers(List<Long> possibleAnswers) {
    this.possibleAnswers = possibleAnswers;
  }
}
