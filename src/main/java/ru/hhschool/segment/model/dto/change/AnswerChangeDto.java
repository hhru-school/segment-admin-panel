package ru.hhschool.segment.model.dto.change;

import ru.hhschool.segment.model.enums.AnswerType;

import java.util.List;

public class AnswerChangeDto {
  private Long id;
  private String title;
  private AnswerType answerType;
  private List<QuestionChangeDto> openQuestionList;
  private boolean conflict;

  public AnswerChangeDto() {
  }

  public AnswerChangeDto(Long id, String title, AnswerType answerType, List<QuestionChangeDto> openQuestionList) {
    this.id = id;
    this.title = title;
    this.answerType = answerType;
    this.openQuestionList = openQuestionList;
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

  public AnswerType getAnswerType() {
    return answerType;
  }

  public void setAnswerType(AnswerType answerType) {
    this.answerType = answerType;
  }

  public List<QuestionChangeDto> getOpenQuestionList() {
    return openQuestionList;
  }

  public void setOpenQuestionList(List<QuestionChangeDto> openQuestionList) {
    this.openQuestionList = openQuestionList;
  }

  public boolean isConflict() {
    return conflict;
  }

  public void setConflict(boolean conflict) {
    this.conflict = conflict;
  }
}
