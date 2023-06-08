package ru.hhschool.segment.model.dto.layer;

import ru.hhschool.segment.model.enums.StateType;

public class SegmentScreenEntrypointLinkCreateDto {
  private Long oldSegmentScreenEntrypointLinkId;
  private Long segmentId;
  private Long entrypointId;
  private Long screenId;
  private Integer screenPosition;
  private StateType screenState;

  public SegmentScreenEntrypointLinkCreateDto() {
  }

  public SegmentScreenEntrypointLinkCreateDto(
      Long oldSegmentScreenEntrypointLinkId,
      Long segmentId,
      Long entrypointId,
      Long screenId,
      Integer screenPosition,
      StateType screenState
  ) {
    this.oldSegmentScreenEntrypointLinkId = oldSegmentScreenEntrypointLinkId;
    this.segmentId = segmentId;
    this.entrypointId = entrypointId;
    this.screenId = screenId;
    this.screenPosition = screenPosition;
    this.screenState = screenState;
  }

  public Long getOldSegmentScreenEntrypointLinkId() {
    return oldSegmentScreenEntrypointLinkId;
  }

  public void setOldSegmentScreenEntrypointLinkId(Long oldSegmentScreenEntrypointLinkId) {
    this.oldSegmentScreenEntrypointLinkId = oldSegmentScreenEntrypointLinkId;
  }

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
  }

  public Long getEntrypointId() {
    return entrypointId;
  }

  public void setEntrypointId(Long entrypointId) {
    this.entrypointId = entrypointId;
  }

  public Long getScreenId() {
    return screenId;
  }

  public void setScreenId(Long screenId) {
    this.screenId = screenId;
  }

  public Integer getScreenPosition() {
    return screenPosition;
  }

  public void setScreenPosition(Integer screenPosition) {
    this.screenPosition = screenPosition;
  }

  public StateType getScreenState() {
    return screenState;
  }

  public void setScreenState(StateType screenState) {
    this.screenState = screenState;
  }
}
