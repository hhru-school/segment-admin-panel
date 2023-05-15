package ru.hhschool.segment.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "platforms")
public class Platform {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "platform_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "platform", nullable = false)
  private String platform;
  @Column(name = "application_version", nullable = false)
  private String applicationVersion;

  public Platform() {}

  public Platform(Long id, String platform, String applicationVersion) {
    this.id = id;
    this.platform = platform;
    this.applicationVersion = applicationVersion;
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

  public String getApplicationVersion() {
    return applicationVersion;
  }

  public void setApplicationVersion(String applicationVersion) {
    this.applicationVersion = applicationVersion;
  }
}
