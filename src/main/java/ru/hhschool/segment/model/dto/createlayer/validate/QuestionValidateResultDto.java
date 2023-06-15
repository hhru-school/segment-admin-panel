package ru.hhschool.segment.model.dto.createlayer.validate;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionValidateResultDto {
  private Long entryPointId;
  private String entryPointTitle;
  private Long questionId;
  private String questionTitle;
  private List<ScreenValidateResultDto> screens;

  public QuestionValidateResultDto(){};

  public QuestionValidateResultDto(Long entryPointId, String entryPointTitle, Long questionId, String questionTitle, List<ScreenValidateResultDto> screens) {
    this.entryPointId = entryPointId;
    this.entryPointTitle = entryPointTitle;
    this.questionId = questionId;
    this.questionTitle = questionTitle;
    this.screens = screens;
  }

  public Long getEntryPointId() {
    return entryPointId;
  }

  public void setEntryPointId(Long entryPointId) {
    this.entryPointId = entryPointId;
  }

  public String getEntryPointTitle() {
    return entryPointTitle;
  }

  public void setEntryPointTitle(String entryPointTitle) {
    this.entryPointTitle = entryPointTitle;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public String getQuestionTitle() {
    return questionTitle;
  }

  public void setQuestionTitle(String questionTitle) {
    this.questionTitle = questionTitle;
  }

  public List<ScreenValidateResultDto> getScreens() {
    return screens;
  }

  public void setScreens(List<ScreenValidateResultDto> screens) {
    this.screens = screens;
  }
}
