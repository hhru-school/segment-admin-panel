package ru.hhschool.segment.model.dto;

public class EntrypointDto {
  private Long id;
  private String title;
  private String description;

  public EntrypointDto() {
  }

  public EntrypointDto(Long id, String title, String description) {
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
}
