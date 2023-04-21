package ru.hhschool.segment.model.dto.questioninfo;

import java.util.List;
import java.util.Objects;

public class QuestionDtoForQuestionsPage {
  private Long id;
  private String title;
  private String description;
  private List<AnswerDtoForQuestionsPage> answerDtoList;

  public QuestionDtoForQuestionsPage() {
  }

  public QuestionDtoForQuestionsPage(Long id, String title, String description, List<AnswerDtoForQuestionsPage> answerDtoList) {
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

  public List<AnswerDtoForQuestionsPage> getAnswerDtoList() {
    return answerDtoList;
  }

  public void setAnswerDtoList(List<AnswerDtoForQuestionsPage> answerDtoList) {
    this.answerDtoList = answerDtoList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuestionDtoForQuestionsPage that = (QuestionDtoForQuestionsPage) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
