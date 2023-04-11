package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

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
  private List<Long> roleIdList;
  @Type(type = "list-array")
  @Column(
      name = "tag",
      columnDefinition = "text[]"
  )
  private List<String> tagArrayList;
  @ManyToOne(fetch = FetchType.LAZY)
  private Segment parent;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private List<QuestionActivatorLinks> questionActivatorLinksList;

  public Long getId() {
    return id;
  }

  public List<QuestionActivatorLinks> getQuestionActivatorLinksList() {
    return questionActivatorLinksList;
  }

  public void setQuestionActivatorLinksList(List<QuestionActivatorLinks> questionActivatorLinksList) {
    this.questionActivatorLinksList = questionActivatorLinksList;
  }

  public Segment getParent() {
    return parent;
  }

  public void setParent(Segment parent) {
    this.parent = parent;
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
