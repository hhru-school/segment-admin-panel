package ru.hhschool.segment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import ru.hhschool.segment.model.enums.PlatformType;

@Entity
@Table(name = "platforms")
public class Platform {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "platform_id", nullable = false, unique = true)
  private Long id;
  @Enumerated(EnumType.STRING)
  @Column(name = "platform", nullable = false)
  private PlatformType platform;
  @Column(name = "application_version", nullable = false)
  private String applicationVersion;

  public Platform() {
  }

  public Platform(PlatformType platform, String applicationVersion) {
    this.platform = platform;
    this.applicationVersion = applicationVersion;
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

  public String getApplicationVersion() {
    return applicationVersion;
  }

  public void setApplicationVersion(String applicationVersion) {
    this.applicationVersion = applicationVersion;
  }
}
