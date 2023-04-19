package ru.hhschool.segment.model.dto;

import java.util.Objects;

public class EntrypointDto {
  private Long id;
  private String title;
  private String description;
  private Long layerId;

  public EntrypointDto() {
  }

  public EntrypointDto(Long id, String title, String description, Long layerId) {
    this.id = id;
    this.title = title;
    this.description = description;
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

  public Long getLayerId() {
    return layerId;
  }

  public void setLayerId(Long layerId) {
    this.layerId = layerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    EntrypointDto that = (EntrypointDto) o;

    if (!title.equals(that.title)) {
      return false;
    }
    return Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    int result = title.hashCode();
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }
}
