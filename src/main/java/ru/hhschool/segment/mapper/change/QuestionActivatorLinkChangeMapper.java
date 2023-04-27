package ru.hhschool.segment.mapper.change;

import java.util.List;
import ru.hhschool.segment.model.dto.change.QuestionActivatorLinkChangeDto;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class QuestionActivatorLinkChangeMapper {
  public static QuestionActivatorLinkChangeDto questionActivatorLinkToDto(QuestionActivatorLink questionActivatorLink) {
    String entrypointTitle = questionActivatorLink.getEntrypoint() == null ? null : questionActivatorLink.getEntrypoint().getTitle();
    String segmentTitle = questionActivatorLink.getSegment() == null ? null : questionActivatorLink.getSegment().getTitle();
    String questionTitle = questionActivatorLink.getQuestion() == null ? null : questionActivatorLink.getQuestion().getTitle();

    Long segmentParentId = null;
    String segmentParentTitle = null;
    if (questionActivatorLink.getSegment() != null && questionActivatorLink.getSegment().getParent() != null) {
      segmentParentId = questionActivatorLink.getSegment().getParent().getId();
      segmentParentTitle = questionActivatorLink.getSegment().getParent().getTitle();
    }

    QuestionActivatorLinkChangeDto questionActivatorLinkChangeDto = new QuestionActivatorLinkChangeDto(
        questionActivatorLink.getId(),
        false,
        questionActivatorLink.isQuestionRequired(),
        QuestionVisibilityType.HIDE_PREFILLED,
        questionActivatorLink.getQuestionVisibility(),
        entrypointTitle,
        segmentTitle,
        segmentParentTitle,
        segmentParentId,
        questionTitle
    );
    return questionActivatorLinkChangeDto;
  }

  public static List<QuestionActivatorLinkChangeDto> questionActivatorLinkChangeListToDtoList(List<QuestionActivatorLink> questionActivatorLinkList) {
    if (questionActivatorLinkList == null) {
      return List.of();
    }
    return questionActivatorLinkList
        .stream()
        .map(QuestionActivatorLinkChangeMapper::questionActivatorLinkToDto)
        .toList();
  }
}
