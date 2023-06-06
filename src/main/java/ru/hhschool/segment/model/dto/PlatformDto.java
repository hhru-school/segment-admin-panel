package ru.hhschool.segment.model.dto;

import ru.hhschool.segment.model.enums.PlatformType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PlatformDto {
  private Long id;
  private PlatformType platform;
  private String version;

  public PlatformDto(Long id, PlatformType platform, String version) {
    this.id = id;
    this.platform = platform;
    this.version = version;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PlatformType getPlatform() {
    return platform;
  }

  public void setPlatform(PlatformType platform) {
    this.platform = platform;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
