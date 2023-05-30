package ru.hhschool.segment.model.dto.viewsegments.layerview;

public class SegmentViewRequirementDto {
  private Long id;
  private String title;
  private Boolean required;
  private Boolean changed;
  private Boolean isNew;

  public SegmentViewRequirementDto() {

  }

  public SegmentViewRequirementDto(Long id, String title, Boolean required, Boolean changed, Boolean isNew) {
    this.id = id;
    this.title = title;
    this.required = required;
    this.changed = changed;
    this.isNew = isNew;
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
  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }
  public Boolean getChanged() {
    return changed;
  }

  public void setChanged(Boolean changed) {
    this.changed = changed;
  }
  public Boolean getNew() {
    return isNew;
  }

  public void setNew(Boolean aNew) {
    isNew = aNew;
  }
}
