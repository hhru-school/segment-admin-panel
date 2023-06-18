package ru.hhschool.segment.model.dto.layer.create;

import java.util.List;

public class LayerCreateEntrypointDto {
  private Long id;
  private String title;
  private String description;
  private List<LayerCreateScreenDto> screens;

  public LayerCreateEntrypointDto() {
  }

  public LayerCreateEntrypointDto(Long id, String title, String description, List<LayerCreateScreenDto> screens) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.screens = screens;
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

  public List<LayerCreateScreenDto> getScreens() {
    return screens;
  }

  public void setScreens(List<LayerCreateScreenDto> screens) {
    this.screens = screens;
  }
}
