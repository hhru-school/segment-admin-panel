package ru.hhschool.segment.model.dto.layer;

import ru.hhschool.segment.model.enums.StateType;

public class SegmentStateLinkCreateDto {
  private Long oldSegmentStateLinkId;
  private Long segmentId;
  private StateType state;

  public SegmentStateLinkCreateDto() {
  }

  public SegmentStateLinkCreateDto(Long oldSegmentStateLinkId, Long segmentId, StateType state) {
    this.oldSegmentStateLinkId = oldSegmentStateLinkId;
    this.segmentId = segmentId;
    this.state = state;
  }

  public Long getOldSegmentStateLinkId() {
    return oldSegmentStateLinkId;
  }

  public void setOldSegmentStateLinkId(Long oldSegmentStateLinkId) {
    this.oldSegmentStateLinkId = oldSegmentStateLinkId;
  }

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
  }

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }
}
