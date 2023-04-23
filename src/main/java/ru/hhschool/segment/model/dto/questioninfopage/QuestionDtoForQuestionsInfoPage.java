package ru.hhschool.segment.model.dto.questioninfopage;

import java.util.List;
import java.util.Objects;

public class QuestionDtoForQuestionsInfoPage {
  private Long id;
  private String title;
  private String description;
  private List<AnswerDtoForQuestionsInfoPage> answerDtoList;
  private boolean searchedObject;

  public QuestionDtoForQuestionsInfoPage() {
  }

  public QuestionDtoForQuestionsInfoPage(Long id, String title, String description, List<AnswerDtoForQuestionsInfoPage> answerDtoList) {
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

  public List<AnswerDtoForQuestionsInfoPage> getAnswerDtoList() {
    return answerDtoList;
  }

  public void setAnswerDtoList(List<AnswerDtoForQuestionsInfoPage> answerDtoList) {
    this.answerDtoList = answerDtoList;
  }

  public boolean isSearchedObject() {
    return searchedObject;
  }

  public void setSearchedObject(boolean searchedObject) {
    this.searchedObject = searchedObject;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuestionDtoForQuestionsInfoPage that = (QuestionDtoForQuestionsInfoPage) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
