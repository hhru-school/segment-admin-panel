package ru.hhschool.segment.model.dto.change;

public class SegmentChangeDto {
  private Long id;
  private String title;
  private String description;
  private boolean archived;
  private boolean conflict;

  public SegmentChangeDto() {
  }

  public SegmentChangeDto(Long id, String title, String description, boolean archived) {
    this.id = id;
    this.title = title;
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

  public void setConflict(boolean conflict) {
    this.conflict = conflict;
  }
}
