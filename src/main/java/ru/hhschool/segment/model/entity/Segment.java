package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "segments")
public class Segment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "segment_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @ManyToOne(fetch = FetchType.LAZY)
  private Segment parentSegment;
  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;
  @Column(name = "description")
  private String description;
  @Type(type = "list-array")
  @Column(
      name = "role",
      columnDefinition = "bigint[]"
  )
  private List<Long> roleList;
  @Type(type = "list-array")
  @Column(
      name = "tags",
      columnDefinition = "bigint[]"
  )
  private List<Long> tagList;

  public Segment() {
  }

  public Segment(
      Long id,
      String title,
      Segment parentSegment,
      LocalDateTime createTime,
      String description,
      List<Long> roleList,
      List<Long> tagList
  ) {
    this.id = id;
    this.title = title;
    this.parentSegment = parentSegment;
    this.createTime = createTime;
    this.description = description;
    this.roleList = roleList;
    this.tagList = tagList;
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

  public Segment getParentSegment() {
    return parentSegment;
  }

  public void setParentSegment(Segment parentSegment) {
    this.parentSegment = parentSegment;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Long> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Long> roleList) {
    this.roleList = roleList;
  }

  public List<Long> getTagList() {
    return tagList;
  }

  public void setTagList(List<Long> tagList) {
    this.tagList = tagList;
  }
}
