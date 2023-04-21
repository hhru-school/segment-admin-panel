package ru.hhschool.segment.model.dto;

import ru.hhschool.segment.model.enums.QuestionType;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

import java.util.List;

public class QuestionDto {
  private Long id;
  private String title;
  private String description;
  private QuestionType type;
  private boolean required;
  private QuestionVisibilityType questionVisibilityType;
  private List<AnswerDto> answerDtoList;
  private Long layerId;
  private boolean resumeField;


  public QuestionDto() {
  }

  public QuestionDto(Long id, String title, String description, QuestionType type, boolean required, QuestionVisibilityType questionVisibilityType, List<AnswerDto> answerDtoList, Long layerId, boolean resumeField) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.required = required;
    this.questionVisibilityType = questionVisibilityType;
    this.answerDtoList = answerDtoList;
    this.layerId = layerId;
    this.resumeField = resumeField;
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

  public List<AnswerDto> getAnswerDtoList() {
    return answerDtoList;
  }

  public void setAnswerDtoList(List<AnswerDto> answerDtoList) {
    this.answerDtoList = answerDtoList;
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
