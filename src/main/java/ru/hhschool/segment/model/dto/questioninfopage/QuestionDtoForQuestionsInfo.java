package ru.hhschool.segment.model.dto.questioninfopage;

import ru.hhschool.segment.model.enums.AnswersNumberType;

import java.util.List;

public class QuestionDtoForQuestionsInfo {
  private Long id;
  private String title;
  private String description;
  private AnswersNumberType type;
  private boolean searchedObject;
  private List<AnswerDtoForQuestionsInfo> answerDtoList;

  public QuestionDtoForQuestionsInfo() {
  }

  public QuestionDtoForQuestionsInfo(Long id, String title, String description, AnswersNumberType type, boolean searchedObject, List<AnswerDtoForQuestionsInfo> answerDtoList) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.searchedObject = searchedObject;
    this.answerDtoList = answerDtoList;
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

  public List<AnswerDtoForQuestionsInfo> getAnswerDtoList() {
    return answerDtoList;
  }

  public void setAnswerDtoList(List<AnswerDtoForQuestionsInfo> answerDtoList) {
    this.answerDtoList = answerDtoList;
  }

  public boolean isSearchedObject() {
    return searchedObject;
  }

  public void setSearchedObject(boolean searchedObject) {
    this.searchedObject = searchedObject;
  }

  public AnswersNumberType getType() {
    return type;
  }

  public void setType(AnswersNumberType type) {
    this.type = type;
  }
}
