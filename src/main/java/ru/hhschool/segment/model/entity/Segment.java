package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.StateType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;

import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "segments")
public class Segment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "segment_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "layer_id")
  private Long layerId;
  @ManyToOne(fetch = FetchType.LAZY)
  private Segment parentSegment;
  @OneToOne(fetch = FetchType.LAZY)
  private Segment oldSegment;
  @Column(name = "title", nullable = false)
  private String title;
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
      name = "tag",
      columnDefinition = "text[]"
  )
  private List<String> tagList;
  @Enumerated(EnumType.STRING)
  @Column(name = "state")
  private StateType state;

  public Segment() {}

  public Segment(Long id, Long layerId, Segment parentSegmentId, Segment oldSegmentId, String title, String description, List<Long> roleList, List<String> tagList, StateType state) {
    this.id = id;
    this.layerId = layerId;
    this.parentSegment = parentSegmentId;
    this.oldSegment = oldSegmentId;
    this.title = title;
    this.description = description;
    this.roleList = roleList;
    this.tagList = tagList;
    this.state = state;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getLayerId() {
    return layerId;
  }

  public void setLayerId(Long layerId) {
    this.layerId = layerId;
  }

  public Segment getParentSegmentId() {
    return parentSegment;
  }

  public void setParentSegmentId(Segment parentSegment) {
    this.parentSegment = parentSegment;
  }

  public Segment getOldSegmentId() {
    return oldSegment;
  }

  public void setOldSegmentId(Segment oldSegment) {
    this.oldSegment = oldSegment;
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

  public List<Long> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Long> roleList) {
    this.roleList = roleList;
  }

  public List<String> getTagList() {
    return tagList;
  }

  public void setTagList(List<String> tagList) {
    this.tagList = tagList;
  }

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }
}
