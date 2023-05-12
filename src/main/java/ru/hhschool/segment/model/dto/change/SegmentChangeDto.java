package ru.hhschool.segment.model.dto.change;

import java.util.Objects;

public class SegmentChangeDto implements ConflictSetter {
  private Long id;
  private Long parentId;
  private String title;
  private String description;
  private boolean archived;
  private boolean conflict;

  public SegmentChangeDto() {
  }

  public SegmentChangeDto(Long id, Long parentId, String title, String description, boolean archived) {
    this.id = id;
    this.title = title;
    this.parentId = parentId;
    this.description = description;
    this.archived = archived;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isArchived() {
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  public boolean isConflict() {
    return conflict;
  }

  @Override
  public void setConflict(boolean conflict) {
    this.conflict = conflict;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SegmentChangeDto that = (SegmentChangeDto) o;

    if (!Objects.equals(parentId, that.parentId)) {
      return false;
    }
    return title.equals(that.title);
  }

  @Override
  public int hashCode() {
    int result = parentId != null ? parentId.hashCode() : 0;
    result = 31 * result + title.hashCode();
    return result;
  }
}
