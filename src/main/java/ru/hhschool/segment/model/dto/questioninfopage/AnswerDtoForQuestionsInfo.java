package ru.hhschool.segment.model.dto.questioninfopage;

import ru.hhschool.segment.model.enums.AnswerType;

import java.util.List;

public class AnswerDtoForQuestionsInfo {
  private Long id;
  private String title;
  private String positiveTitle;
  private AnswerType answerType;
  private boolean defaultAnswer;
  private boolean skipAtResult;
  private boolean searchedObject;
  private List<QuestionDtoForQuestionsInfo> openQuestionList;

  public AnswerDtoForQuestionsInfo() {
  }

  public AnswerDtoForQuestionsInfo(Long id, String title, String positiveTitle, AnswerType answerType, boolean defaultAnswer, boolean skipAtResult, boolean searchedObject, List<QuestionDtoForQuestionsInfo> openQuestonDtoList) {
    this.id = id;
    this.title = title;
    this.positiveTitle = positiveTitle;
    this.answerType = answerType;
    this.defaultAnswer = defaultAnswer;
    this.skipAtResult = skipAtResult;
    this.searchedObject = searchedObject;
    this.openQuestionList = openQuestonDtoList;
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

  public List<QuestionDtoForQuestionsInfo> getOpenQuestionList() {
    return openQuestionList;
  }

  public void setOpenQuestionList(List<QuestionDtoForQuestionsInfo> openQuestionList) {
    this.openQuestionList = openQuestionList;
  }

  public boolean isSearchedObject() {
    return searchedObject;
  }

  public void setSearchedObject(boolean searchedObject) {
    this.searchedObject = searchedObject;
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
}
