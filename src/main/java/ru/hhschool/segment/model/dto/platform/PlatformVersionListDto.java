package ru.hhschool.segment.model.dto.platform;

import java.util.List;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.enums.PlatformType;

public class PlatformVersionListDto {
  private PlatformType platform;
  private List<PlatformDto> versions;

  public PlatformVersionListDto() {
  }

  public PlatformVersionListDto(PlatformType platform, List<PlatformDto> versions) {
    this.platform = platform;
    this.versions = versions;
  }

  public PlatformType getPlatform() {
    return platform;
  }

  public void setPlatform(PlatformType platform) {
    this.platform = platform;
  }

  public List<PlatformDto> getVersions() {
    return versions;
  }

  public void setVersions(List<PlatformDto> versions) {
    this.versions = versions;
  }
}
