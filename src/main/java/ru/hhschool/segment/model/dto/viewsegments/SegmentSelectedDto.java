package ru.hhschool.segment.model.dto.viewsegments;

import java.util.List;

public class SegmentSelectedDto {
  private Long segmentId;
  private String title;
  private List<SegmentViewQuestionDto> questions;

  public SegmentSelectedDto(Long segmentId, String title, List<SegmentViewQuestionDto> questions) {
    this.segmentId = segmentId;
    this.title = title;
    this.questions = questions;
  }

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<SegmentViewQuestionDto> getQuestions() {
    return questions;
  }

  public void setQuestions(List<SegmentViewQuestionDto> questions) {
    this.questions = questions;
  }
}
