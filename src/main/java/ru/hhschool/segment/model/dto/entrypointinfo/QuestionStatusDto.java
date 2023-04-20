package ru.hhschool.segment.model.dto.entrypointinfo;

import java.util.HashSet;
import java.util.Set;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class QuestionStatusDto {
  Set<QuestionVisibilityType> questionStatus = new HashSet<>();
  private String title;
  private boolean resumeField;

  public QuestionStatusDto() {
  }

  public QuestionStatusDto(Set<QuestionVisibilityType> questionStatus, String title, boolean resumeField) {
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

  public void setQuestionStatus(Set<QuestionVisibilityType> questionStatus) {
    this.questionStatus = questionStatus;
  }

  public boolean isResumeField() {
    return resumeField;
  }

  public void setResumeField(boolean resumeField) {
    this.resumeField = resumeField;
  }
}
