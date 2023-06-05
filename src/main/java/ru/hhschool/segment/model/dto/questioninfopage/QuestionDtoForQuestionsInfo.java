package ru.hhschool.segment.model.dto.questioninfopage;

import java.util.List;
import ru.hhschool.segment.model.enums.AnswersNumberType;
import ru.hhschool.segment.model.enums.ResumeField;

public class QuestionDtoForQuestionsInfo {
  private Long id;
  private String title;
  private String description;
  private ResumeField type;
  private AnswersNumberType answersType;
  private boolean searchedObject;
  private List<AnswerDtoForQuestionsInfo> possibleAnswersList;

  public QuestionDtoForQuestionsInfo() {
  }

  public QuestionDtoForQuestionsInfo(
      Long id,
      String title,
      String description,
      ResumeField type,
      AnswersNumberType answersType,
      boolean searchedObject,
      List<AnswerDtoForQuestionsInfo> answerDtoList
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.answersType = answersType;
    this.searchedObject = searchedObject;
    this.possibleAnswersList = answerDtoList;
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

  public List<AnswerDtoForQuestionsInfo> getPossibleAnswersList() {
    return possibleAnswersList;
  }

  public void setPossibleAnswersList(List<AnswerDtoForQuestionsInfo> possibleAnswersList) {
    this.possibleAnswersList = possibleAnswersList;
  }

  public boolean isSearchedObject() {
    return searchedObject;
  }

  public void setSearchedObject(boolean searchedObject) {
    this.searchedObject = searchedObject;
  }

  public ResumeField getType() {
    return type;
  }

  public void setType(ResumeField type) {
    this.type = type;
  }

  public AnswersNumberType getAnswersType() {
    return answersType;
  }

  public void setAnswersType(AnswersNumberType answersType) {
    this.answersType = answersType;
  }
}
