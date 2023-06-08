package ru.hhschool.segment.model.dto.layer;

public class QuestionRequiredLinkCreateDto {
  private Long oldQuestionRequiredLinkId;
  private Long segmentId;
  private Long questionId;
  private boolean questionRequired;

  public QuestionRequiredLinkCreateDto() {
  }

  public QuestionRequiredLinkCreateDto(Long oldQuestionRequiredLinkId, Long segmentId, Long questionId, boolean questionRequired) {
    this.oldQuestionRequiredLinkId = oldQuestionRequiredLinkId;
    this.segmentId = segmentId;
    this.questionId = questionId;
    this.questionRequired = questionRequired;
  }

  public Long getOldQuestionRequiredLinkId() {
    return oldQuestionRequiredLinkId;
  }

  public void setOldQuestionRequiredLinkId(Long oldQuestionRequiredLinkId) {
    this.oldQuestionRequiredLinkId = oldQuestionRequiredLinkId;
  }

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public boolean isQuestionRequired() {
    return questionRequired;
  }

  public void setQuestionRequired(boolean questionRequired) {
    this.questionRequired = questionRequired;
  }
}
