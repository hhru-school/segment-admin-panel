package ru.hhschool.segment.model.dto;

public class EntrypointDto {
  private Long id;
  private String title;
  private String description;
  private String type;
  private Long layerId;

  public EntrypointDto() {
  }

  public EntrypointDto(Long id, String title, String description, String type, Long layerId) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.layerId = layerId;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getLayerId() {
    return layerId;
  }

  public void setLayerId(Long layerId) {
    this.layerId = layerId;
  }
}
