package ru.hhschool.segment.model.entity;

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
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "layers")
public class Layer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "layer_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description")
  private String description;
  @Column(name = "layer_stable")
  private boolean stable;
  @Column(name = "layer_archive")
  private boolean archive;
  @Column(name = "layer_deleted")
  private boolean deleted;
  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;
  @ManyToOne(fetch = FetchType.LAZY)
  private Layer parent;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "layer_id")
  private List<QuestionActivatorLinks> questionActivatorLinksList;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "layer_id")
  private List<Answer> answerList;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private List<Question> questionList;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "entrypoint_id")
  private List<Entrypoint> entrypointList;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "segment_id")
  private List<Segment> segmentList;

  public Layer() {
  }

  public Layer(String title, String description, boolean stable, boolean archive, boolean deleted, LocalDateTime createTime, Layer parent, List<QuestionActivatorLinks> questionActivatorLinksList, List<Answer> answerList, List<Question> questionList, List<Entrypoint> entrypointList, List<Segment> segmentList) {
    this.title = title;
    this.description = description;
    this.stable = stable;
    this.archive = archive;
    this.deleted = deleted;
    this.createTime = createTime;
    this.parent = parent;
    this.questionActivatorLinksList = questionActivatorLinksList;
    this.answerList = answerList;
    this.questionList = questionList;
    this.entrypointList = entrypointList;
    this.segmentList = segmentList;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public List<Question> getQuestionList() {
    return questionList;
  }

  public void setQuestionList(List<Question> questionList) {
    this.questionList = questionList;
  }

  public List<Entrypoint> getEntrypointList() {
    return entrypointList;
  }

  public void setEntrypointList(List<Entrypoint> entrypointList) {
    this.entrypointList = entrypointList;
  }

  public List<Segment> getSegmentList() {
    return segmentList;
  }

  public void setSegmentList(List<Segment> segmentList) {
    this.segmentList = segmentList;
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

  public boolean isStable() {
    return stable;
  }

  public void setStable(boolean stable) {
    this.stable = stable;
  }

  public boolean isArchive() {
    return archive;
  }

  public void setArchive(boolean archive) {
    this.archive = archive;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public Layer getParent() {
    return parent;
  }

  public void setParent(Layer parent) {
    this.parent = parent;
  }

  public List<QuestionActivatorLinks> getQuestionActivatorLinksList() {
    return questionActivatorLinksList;
  }

  public void setQuestionActivatorLinksList(List<QuestionActivatorLinks> questionActivatorLinksList) {
    this.questionActivatorLinksList = questionActivatorLinksList;
  }

  public List<Answer> getAnswerList() {
    return answerList;
  }

  public void setAnswerList(List<Answer> answerList) {
    this.answerList = answerList;
  }
}
