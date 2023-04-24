package ru.hhschool.segment.model.dto.change;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LayerChangeDto {
  private Long id;
  private Long parentLayerId;
  private boolean conflict;

  private Map<String, List<EntrypointChangeDto>> entrypointMap;
  private Map<String, List<SegmentChangeDto>> segmentMap;
  private Map<String, List<QuestionChangeDto>> questionMap;
  private Map<String, List<AnswerChangeDto>> answerMap;
  private Map<String, List<QuestionActivatorLinkChangeDto>> questionActivatorLinkMap;
  private Set<String> usedEntrypointTitleList;

  public LayerChangeDto() {
  }

  public LayerChangeDto(Long id, Long parentLayerId, boolean conflict) {
    this.id = id;
    this.parentLayerId = parentLayerId;
    this.conflict = conflict;

    entrypointMap = Map.of();
    segmentMap = Map.of();
    questionMap = Map.of();
    answerMap = Map.of();
    questionActivatorLinkMap = Map.of();
    usedEntrypointTitleList = Set.of();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentLayerId() {
    return parentLayerId;
  }

  public void setParentLayerId(Long parentLayerId) {
    this.parentLayerId = parentLayerId;
  }

  public Map<String, List<EntrypointChangeDto>> getEntrypointMap() {
    return entrypointMap;
  }

  public void setEntrypointMap(Map<String, List<EntrypointChangeDto>> entrypointMap) {
    this.entrypointMap = entrypointMap;
  }

  public Map<String, List<SegmentChangeDto>> getSegmentMap() {
    return segmentMap;
  }

  public void setSegmentMap(Map<String, List<SegmentChangeDto>> segmentMap) {
    this.segmentMap = segmentMap;
  }

  public Map<String, List<QuestionChangeDto>> getQuestionMap() {
    return questionMap;
  }

  public void setQuestionMap(Map<String, List<QuestionChangeDto>> questionMap) {
    this.questionMap = questionMap;
  }

  public Map<String, List<AnswerChangeDto>> getAnswerMap() {
    return answerMap;
  }

  public void setAnswerMap(Map<String, List<AnswerChangeDto>> answerMap) {
    this.answerMap = answerMap;
  }

  public Map<String, List<QuestionActivatorLinkChangeDto>> getQuestionActivatorLinkMap() {
    return questionActivatorLinkMap;
  }

  public void setQuestionActivatorLinkMap(Map<String, List<QuestionActivatorLinkChangeDto>> questionActivatorLinkMap) {
    this.questionActivatorLinkMap = questionActivatorLinkMap;
  }

  public Set<String> getUsedEntrypointTitleList() {
    return usedEntrypointTitleList;
  }

  public void setUsedEntrypointTitleList(Set<String> usedEntrypointTitleList) {
    this.usedEntrypointTitleList = usedEntrypointTitleList;
  }

  public boolean isConflict() {
    return conflict;
  }

  public void setConflict(boolean conflict) {
    this.conflict = conflict;
  }

}
