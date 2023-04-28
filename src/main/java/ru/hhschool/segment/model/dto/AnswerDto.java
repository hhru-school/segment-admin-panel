package ru.hhschool.segment.model.dto;

import ru.hhschool.segment.model.enums.AnswerType;

import java.util.List;

public class AnswerDto {
  private Long id;
  private String title;
  private String positiveTitle;
  private AnswerType answerType;
  private boolean defaultAnswer;
  private boolean skipAtResult;
  private List<QuestionDto> openQuestonDtoList;
  private Long layerId;

  public AnswerDto() {
  }

  public AnswerDto(Long id, String title, String positiveTitle, AnswerType answerType, boolean defaultAnswer, boolean skipAtResult, List<QuestionDto> openQuestonDtoList, Long layerId) {
    this.id = id;
    this.title = title;
    this.positiveTitle = positiveTitle;
    this.answerType = answerType;
    this.defaultAnswer = defaultAnswer;
    this.skipAtResult = skipAtResult;
    this.openQuestonDtoList = openQuestonDtoList;
    this.layerId = layerId;
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

  public AnswerType getAnswerType() {
    return answerType;
  }

  public void setAnswerType(AnswerType answerType) {
    this.answerType = answerType;
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

  public List<QuestionDto> getOpenQuestonDtoList() {
    return openQuestonDtoList;
  }

  public void setOpenQuestonDtoList(List<QuestionDto> openQuestonDtoList) {
    this.openQuestonDtoList = openQuestonDtoList;
  }

  public Long getLayerId() {
    return layerId;
  }

  public void setLayerId(Long layerId) {
    this.layerId = layerId;
  }
}
