package ru.hhschool.segment.model.dto.change;

import java.util.List;

public class EntrypointGroupByQuestionDto {
  String questionTitle;
  List<QuestionActivatorLinkChangeDto> questionActivatorLinkChangeDtoList;

  public EntrypointGroupByQuestionDto() {
  }

  public EntrypointGroupByQuestionDto(String questionTitle, List<QuestionActivatorLinkChangeDto> questionActivatorLinkChangeDtoList) {
    this.questionTitle = questionTitle;
    this.questionActivatorLinkChangeDtoList = questionActivatorLinkChangeDtoList;
  }

  public String getQuestionTitle() {
    return questionTitle;
  }

  public void setQuestionTitle(String questionTitle) {
    this.questionTitle = questionTitle;
  }

  public List<QuestionActivatorLinkChangeDto> getQuestionActivatorLinkChangeDtoList() {
    return questionActivatorLinkChangeDtoList;
  }

  public void setQuestionActivatorLinkChangeDtoList(List<QuestionActivatorLinkChangeDto> questionActivatorLinkChangeDtoList) {
    this.questionActivatorLinkChangeDtoList = questionActivatorLinkChangeDtoList;
  }
}
