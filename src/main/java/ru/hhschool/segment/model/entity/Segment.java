package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import java.io.Serializable;
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
public class Segment implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "segment_id", nullable = false, unique = true)
  private Long id;
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
  @ManyToOne(fetch = FetchType.LAZY)
  private Segment parent;
  @Column(name = "layer_id")
  private Long layerId;
  @Column(name = "archived")
  private boolean archived;


  public Segment(String title, String description, List<Long> roleList, List<String> tagList, Segment parent, Long layerId) {
    this.title = title;
    this.description = description;
    this.roleList = roleList;
    this.tagList = tagList;
    this.parent = parent;
    this.layerId = layerId;
  }

  public Segment() {
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

  public Segment getParent() {
    return parent;
  }

  public void setParent(Segment parent) {
    this.parent = parent;
  }

  public Long getLayerId() {
    return layerId;
  }

  public void setLayerId(Long layerId) {
    this.layerId = layerId;
  }

  public boolean isArchived() {
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }
}
