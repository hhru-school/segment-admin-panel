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
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private List<Question> questionList;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "entrypoint_id")
  private List<Entrypoint> entrypointList;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private List<Segment> segmentList;

  public Question(String title, String description, QuestionType type, boolean required, QuestionVisibilityType questionVisibilityType, List<Long> possibleAnswerIdList, List<Question> questionList, List<Entrypoint> entrypointList, List<Segment> segmentList) {
    this.title = title;
    this.description = description;
    this.type = type;
    this.required = required;
    this.questionVisibilityType = questionVisibilityType;
    this.possibleAnswerIdList = possibleAnswerIdList;
    this.questionList = questionList;
    this.entrypointList = entrypointList;
    this.segmentList = segmentList;
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

  public List<Question> getQuestionList() {
    return questionList;
  }

  public void setQuestionList(List<Question> questionList) {
    this.questionList = questionList;
  }

  public List<Entrypoint> getEntrypointList() {
    return entrypointList;
  }

  public void setEntrypointList(List<Entrypoint> entrypointList) {
    this.entrypointList = entrypointList;
  }

  public List<Segment> getSegmentList() {
    return segmentList;
  }

  public void setSegmentList(List<Segment> segmentList) {
    this.segmentList = segmentList;
  }
}
