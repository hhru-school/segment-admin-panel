package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Segment implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  private String title;
  private String description;
  @Type(type = "list-array")
  @Column(
      name = "roleidlist",
      columnDefinition = "bigint[]"
  )
  private List<Long> roleIdList;
  @Type(type = "list-array")
  @Column(
      name = "taglist",
      columnDefinition = "text[]"
  )
  private List<String> tagArrayList;
  @ManyToOne
  private Segment parentSegment;
  @OneToMany(mappedBy = "parentSegment")
  private List<Segment> childrenSegmentList;
  @ManyToOne
  private Layer layer;
  @OneToMany(
      mappedBy = "segment",
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST,
          CascadeType.DETACH,
          CascadeType.REFRESH}
  )
  private List<QuestionActivatorLinks> questionActivatorLinksList;

  public Segment getParentSegment() {
    return parentSegment;
  }

  public Segment() {
  }

  public Segment(String title, String description, List<Long> roleIdList, List<String> tagArrayList, Segment parentSegment, List<Segment> childrenSegmentList, Layer layer) {
    this.title = title;
    this.description = description;
    this.roleIdList = roleIdList;
    this.tagArrayList = tagArrayList;
    this.parentSegment = parentSegment;
    this.childrenSegmentList = childrenSegmentList;
    this.layer = layer;
  }

  public void setParentSegment(Segment parentSegment) {
    this.parentSegment = parentSegment;
  }

  public List<Segment> getChildrenSegmentList() {
    return childrenSegmentList;
  }

  public void setChildrenSegmentList(List<Segment> childrenSegmentList) {
    this.childrenSegmentList = childrenSegmentList;
  }

  public Layer getLayer() {
    return layer;
  }

  public void setLayer(Layer layer) {
    this.layer = layer;
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

  public List<Long> getRoleIdList() {
    return roleIdList;
  }

  public void setRoleIdList(List<Long> roleIdList) {
    this.roleIdList = roleIdList;
  }

  public List<String> getTagArrayList() {
    return tagArrayList;
  }

  public void setTagArrayList(List<String> tagArrayList) {
    this.tagArrayList = tagArrayList;
  }
}
