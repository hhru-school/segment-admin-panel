package ru.hhschool.segment.model.dto.questioninfopage;

import java.util.List;

public class AnswerDtoForQuestionsInfoPage {
  private Long id;
  private String title;
  private List<QuestionDtoForQuestionsInfoPage> openQuestonDtoList;

  public AnswerDtoForQuestionsInfoPage() {
  }

  public AnswerDtoForQuestionsInfoPage(Long id, String title, List<QuestionDtoForQuestionsInfoPage> openQuestonDtoList) {
    this.id = id;
    this.title = title;
    this.openQuestonDtoList = openQuestonDtoList;
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
}
