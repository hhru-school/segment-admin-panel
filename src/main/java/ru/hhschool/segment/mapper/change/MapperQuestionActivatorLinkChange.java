package ru.hhschool.segment.mapper.change;

import java.util.List;
import ru.hhschool.segment.model.dto.change.QuestionActivatorLinkChangeDto;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

public class MapperQuestionActivatorLinkChange {
  public static QuestionActivatorLinkChangeDto questionActivatorLinkToDto(QuestionActivatorLink questionActivatorLink) {
    QuestionActivatorLinkChangeDto questionActivatorLinkChangeDto = new QuestionActivatorLinkChangeDto(
        questionActivatorLink.getId(),
        questionActivatorLink.isQuestionRequired(),
        questionActivatorLink.getQuestionVisibility(),
        questionActivatorLink.getEntrypoint().getTitle(),
        questionActivatorLink.getSegment().getTitle(),
        questionActivatorLink.getQuestion().getTitle()
    );
    return questionActivatorLinkChangeDto;
  }

  public static List<QuestionActivatorLinkChangeDto> QuestionActivatorLinkChangeListToDtoList(List<QuestionActivatorLink> QuestionActivatorLinkList) {
    return QuestionActivatorLinkList
        .stream()
        .map(MapperQuestionActivatorLinkChange::questionActivatorLinkToDto)
        .toList();
  }
}
