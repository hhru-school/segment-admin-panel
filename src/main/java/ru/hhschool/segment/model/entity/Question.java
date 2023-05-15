package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.AnswersNumberType;
import ru.hhschool.segment.model.enums.ResumeField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
  private List<Long> possibleAnswerIdList;

  public Question() {}

  public Question(Long id, String title, String description, ResumeField type, AnswersNumberType answerType, List<Long> possibleAnswerIdList) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.answerType = answerType;
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

  public List<Long> getPossibleAnswerIdList() {
    return possibleAnswerIdList;
  }

  public void setPossibleAnswerIdList(List<Long> possibleAnswerIdList) {
    this.possibleAnswerIdList = possibleAnswerIdList;
  }
}
