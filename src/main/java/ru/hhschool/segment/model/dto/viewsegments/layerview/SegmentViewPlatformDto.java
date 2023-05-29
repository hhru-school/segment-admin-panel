package ru.hhschool.segment.model.dto.viewsegments.layerview;

import ru.hhschool.segment.model.enums.PlatformType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class SegmentViewPlatformDto {
  @Enumerated(EnumType.STRING)
  private PlatformType platform;
  private String version;

  public SegmentViewPlatformDto(PlatformType platform, String version) {
    this.platform = platform;
    this.version = version;
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
