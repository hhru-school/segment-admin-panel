package ru.hhschool.segment.model.enums;

public enum LayerStatus {
  EXPERIMENTAL(Group.ACTIVE),
  STABLE(Group.FINISHED),
  ARCHIVED(Group.FINISHED),
  CONFLICT(Group.ACTIVE);
  private final Group groupOfLayerStatus;

  LayerStatus(Group groupOfLayerStatus) {
    this.groupOfLayerStatus = groupOfLayerStatus;
  }

  public Group getGroup() {
    return groupOfLayerStatus;
  }

  public enum Group {
    ACTIVE,
    FINISHED,
  }
}

