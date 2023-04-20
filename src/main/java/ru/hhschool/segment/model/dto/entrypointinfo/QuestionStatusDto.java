package ru.hhschool.segment.model.dto.entrypointinfo;

import java.util.HashSet;
import java.util.Set;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class QuestionStatusDto {
  Set<QuestionVisibilityType> questionStatus = new HashSet<>();
  private String title;
  private Boolean resumeField;

  public QuestionStatusDto() {
  }

  public QuestionStatusDto(Set<QuestionVisibilityType> questionStatus, String title, Boolean resumeField) {
    this.questionStatus = questionStatus;
    this.title = title;
    this.resumeField = resumeField;
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

  public Boolean getResumeField() {
    return resumeField;
  }

  public void setResumeField(Boolean resumeField) {
    this.resumeField = resumeField;
  }
}
