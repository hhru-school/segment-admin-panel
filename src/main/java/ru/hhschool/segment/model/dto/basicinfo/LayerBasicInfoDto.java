package ru.hhschool.segment.model.dto.basicinfo;

import java.time.LocalDateTime;
import java.util.List;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.enums.LayerStatus;

public class LayerBasicInfoDto {
  private Long id;
  private String title;
  private String description;
  private LocalDateTime createTime;
  private LayerStatus layerStatus;
  private List<PlatformDto> platforms;
  private List<LayerDto> parentLayersList;

  public LayerBasicInfoDto() {
  }

  public LayerBasicInfoDto(
      Long id,
      String title,
      String description,
      LocalDateTime createTime,
      LayerStatus layerStatus,
      List<PlatformDto> platforms,
      List<LayerDto> parentLayersList
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.createTime = createTime;
    this.layerStatus = layerStatus;
    this.platforms = platforms;
    this.parentLayersList = parentLayersList;
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

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public LayerStatus getLayerStatus() {
    return layerStatus;
  }

  public void setLayerStatus(LayerStatus layerStatus) {
    this.layerStatus = layerStatus;
  }

  public List<PlatformDto> getPlatforms() {
    return platforms;
  }

  public void setPlatforms(List<PlatformDto> platforms) {
    this.platforms = platforms;
  }

  public List<LayerDto> getParentLayersList() {
    return parentLayersList;
  }

  public void setParentLayersList(List<LayerDto> parentLayersList) {
    this.parentLayersList = parentLayersList;
  }
}
