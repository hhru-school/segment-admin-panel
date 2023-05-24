package ru.hhschool.segment.model.dto.screen;

public class ScreenPlatformDto {
  private Long id;
  private String platform;
  private String version;

  public ScreenPlatformDto() {
  }

  public ScreenPlatformDto(Long id, String platform, String version) {
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

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
