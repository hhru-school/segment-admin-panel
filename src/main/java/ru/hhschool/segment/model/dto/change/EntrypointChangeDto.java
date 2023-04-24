package ru.hhschool.segment.model.dto.change;

public class EntrypointChangeDto implements ConflictSetter {
  private Long id;
  private String title;
  private String description;
  private boolean conflict;

  public EntrypointChangeDto() {
  }

  public EntrypointChangeDto(Long id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
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

  public boolean isConflict() {
    return conflict;
  }

  @Override
  public void setConflict(boolean conflict) {
    this.conflict = conflict;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    EntrypointChangeDto that = (EntrypointChangeDto) o;

    return title.equals(that.title);
  }

  @Override
  public int hashCode() {
    return title.hashCode();
  }
}
