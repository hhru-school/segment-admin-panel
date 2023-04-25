package ru.hhschool.segment.mapper.change;

import java.util.List;
import ru.hhschool.segment.model.dto.change.QuestionActivatorLinkChangeDto;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

public class QuestionActivatorLinkChangeMapper {
  public static QuestionActivatorLinkChangeDto questionActivatorLinkToDto(QuestionActivatorLink questionActivatorLink) {
    String entrypointTitle = questionActivatorLink.getEntrypoint() == null ? null : questionActivatorLink.getEntrypoint().getTitle();
    String segmentTitle = questionActivatorLink.getSegment() == null ? null : questionActivatorLink.getSegment().getTitle();
    String questionTitle = questionActivatorLink.getQuestion() == null ? null : questionActivatorLink.getQuestion().getTitle();

    Long segmentParentId = null;
    if (questionActivatorLink.getSegment() != null && questionActivatorLink.getSegment().getParent() != null) {
      segmentParentId = questionActivatorLink.getSegment().getParent().getId();
    }

    QuestionActivatorLinkChangeDto questionActivatorLinkChangeDto = new QuestionActivatorLinkChangeDto(
        questionActivatorLink.getId(),
        questionActivatorLink.isQuestionRequired(),
        questionActivatorLink.getQuestionVisibility(),
        entrypointTitle,
        segmentTitle,
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
