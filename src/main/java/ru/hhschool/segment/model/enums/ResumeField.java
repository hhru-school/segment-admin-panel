package ru.hhschool.segment.model.enums;

public enum ResumeField {
  RESUME_FIELD(Boolean.TRUE),
  QUESTION(Boolean.FALSE);

  private final Boolean resumeField;

  ResumeField(Boolean resumeField) {
    this.resumeField = resumeField;
  }

  public Boolean isResumeField() {
    return this.resumeField;
  }
}
