package ru.hhschool.segment.model.dto.change;

public class EntrypointChangeDto implements GetterElemet {
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

  @Override
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

  public void setConflict(boolean conflict) {
    this.conflict = conflict;
  }
}
