package ru.hhschool.segment.model.dto.screen;

import java.util.List;

public class ScreenPlatformVersionDto {
  private String platform;
  private List<ScreenPlatformDto> versions;

  public ScreenPlatformVersionDto() {
  }

  public ScreenPlatformVersionDto(String platform, List<ScreenPlatformDto> versions) {
    this.platform = platform;
    this.versions = versions;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public List<ScreenPlatformDto> getVersions() {
    return versions;
  }

  public void setVersions(List<ScreenPlatformDto> versions) {
    this.versions = versions;
  }
}
