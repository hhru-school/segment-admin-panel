package ru.hhschool.segment.model.dto.change;

import ru.hhschool.segment.model.dto.QuestionActivatorLinkDto;

import java.util.List;
import java.util.Map;

public class QuestionActivatorLinkChangeDto {
    Map<String, List<QuestionActivatorLinkDto>> fastIndexLinkStatusMap;

    public QuestionActivatorLinkChangeDto(Map<String, List<QuestionActivatorLinkDto>> fastIndexLinkStatusMap) {
        this.fastIndexLinkStatusMap = fastIndexLinkStatusMap;
    }

    public QuestionActivatorLinkChangeDto() {
    }

    public Map<String, List<QuestionActivatorLinkDto>> getFastIndexLinkStatusMap() {
        return fastIndexLinkStatusMap;
    }

    public void setFastIndexLinkStatusMap(Map<String, List<QuestionActivatorLinkDto>> fastIndexLinkStatusMap) {
        this.fastIndexLinkStatusMap = fastIndexLinkStatusMap;
    }
}
