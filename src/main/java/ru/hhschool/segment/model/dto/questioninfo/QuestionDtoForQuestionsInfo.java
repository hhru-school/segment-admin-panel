package ru.hhschool.segment.model.dto.questioninfo;

import ru.hhschool.segment.model.dto.AnswerDto;

import java.util.List;

public class QuestionDtoForQuestionsInfo {
  private Long id;
  private String title;
  private String description;
  private List<AnswerDtoForQuestionsInfo> answerDtoList;

  public QuestionDtoForQuestionsInfo() {
  }

  public QuestionDtoForQuestionsInfo(Long id, String title, String description, List<AnswerDtoForQuestionsInfo> answerDtoList) {
    this.id = id;
    this.title = title;
    this.description = description;
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
}
