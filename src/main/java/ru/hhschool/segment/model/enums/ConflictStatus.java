package ru.hhschool.segment.model.enums;

public enum ConflictStatus {
  CONFLICT(Boolean.TRUE),
  NONE(Boolean.FALSE);

  private final Boolean resumeField;

  ConflictStatus(Boolean resumeField) {
    this.resumeField = resumeField;
  }

  public Boolean isConflict() {
    return this.resumeField;
  }
}
