package ru.hhschool.segment.model.dto.entrypointinfo;

import java.util.HashSet;
import java.util.Set;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class QuestionStatusDto {
  private String title;
  Set<QuestionVisibilityType> questionStatus = new HashSet<>();

  public QuestionStatusDto() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<QuestionVisibilityType> getQuestionStatus() {
    return questionStatus;
  }

  public void setQuestionStatus(Set<QuestionVisibilityType> questionStatus) {
    this.questionStatus = questionStatus;
  }

}
