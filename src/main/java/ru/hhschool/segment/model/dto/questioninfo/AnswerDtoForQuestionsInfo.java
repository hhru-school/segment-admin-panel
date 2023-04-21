package ru.hhschool.segment.model.dto.questioninfo;

import ru.hhschool.segment.model.dto.QuestionDto;

import java.util.List;

public class AnswerDtoForQuestionsInfo {
  private Long id;
  private String title;
  private List<QuestionDto> openQuestonDtoList;

  public AnswerDtoForQuestionsInfo() {
  }

  public AnswerDtoForQuestionsInfo(Long id, String title, List<QuestionDto> openQuestonDtoList) {
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

  public List<QuestionDto> getOpenQuestonDtoList() {
    return openQuestonDtoList;
  }

  public void setOpenQuestonDtoList(List<QuestionDto> openQuestonDtoList) {
    this.openQuestonDtoList = openQuestonDtoList;
  }
}
