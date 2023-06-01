package ru.hhschool.segment.model.dto.platform;

import java.util.List;

public class PlatformVersionListDto {
  private String platform;
  private List<PlatformDto> versions;

  public PlatformVersionListDto() {
  }

  public PlatformVersionListDto(String platform, List<PlatformDto> versions) {
    this.platform = platform;
    this.versions = versions;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public List<PlatformDto> getVersions() {
    return versions;
  }

  public void setVersions(List<PlatformDto> versions) {
    this.versions = versions;
  }
}
