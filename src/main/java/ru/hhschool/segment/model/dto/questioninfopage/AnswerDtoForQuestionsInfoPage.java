package ru.hhschool.segment.model.dto.questioninfopage;

import java.util.List;
import java.util.Set;

public class AnswerDtoForQuestionsInfoPage {
  private Long id;
  private String title;
  private List<QuestionDtoForQuestionsInfoPage> openQuestonDtoList;
  private boolean searchedObject;
  public AnswerDtoForQuestionsInfoPage() {
  }

  public AnswerDtoForQuestionsInfoPage(Long id, String title, List<QuestionDtoForQuestionsInfoPage> openQuestonDtoList, boolean searchedObject) {
    this.id = id;
    this.title = title;
    this.openQuestonDtoList = openQuestonDtoList;
    this.searchedObject = searchedObject;
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

  public List<QuestionDtoForQuestionsInfoPage> getOpenQuestonDtoList() {
    return openQuestonDtoList;
  }

  public void setOpenQuestonDtoList(List<QuestionDtoForQuestionsInfoPage> openQuestonDtoList) {
    this.openQuestonDtoList = openQuestonDtoList;
  }

  public boolean isSearchedObject() {
    return searchedObject;
  }

  public void setSearchedObject(boolean searchedObject) {
    this.searchedObject = searchedObject;
  }
}
