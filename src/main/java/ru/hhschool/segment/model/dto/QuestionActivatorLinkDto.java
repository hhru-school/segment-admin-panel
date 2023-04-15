package ru.hhschool.segment.model.dto;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class QuestionActivatorLinkDto {
    private Long id;
    private boolean questionRequired;
    private QuestionVisibilityType questionVisibility;
    private Long layerId;
    private Long segmentId;
    private Long questionId;
    private Long entrypointId;

    public QuestionActivatorLinkDto() {
    }

    public QuestionActivatorLinkDto(Long id, boolean questionRequired, QuestionVisibilityType questionVisibility, Long layerId, Long segmentId, Long questionId, Long entrypointId) {
        this.id = id;
        this.questionRequired = questionRequired;
        this.questionVisibility = questionVisibility;
        this.layerId = layerId;
        this.segmentId = segmentId;
        this.questionId = questionId;
        this.entrypointId = entrypointId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isQuestionRequired() {
        return questionRequired;
    }

    public void setQuestionRequired(boolean questionRequired) {
        this.questionRequired = questionRequired;
    }

    public QuestionVisibilityType getQuestionVisibility() {
        return questionVisibility;
    }

    public void setQuestionVisibility(QuestionVisibilityType questionVisibility) {
        this.questionVisibility = questionVisibility;
    }

    public Long getLayerId() {
        return layerId;
    }

    public void setLayerId(Long layerId) {
        this.layerId = layerId;
    }

    public Long getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Long segmentId) {
        this.segmentId = segmentId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getEntrypointId() {
        return entrypointId;
    }

    public void setEntrypointId(Long entrypointId) {
        this.entrypointId = entrypointId;
    }
}
