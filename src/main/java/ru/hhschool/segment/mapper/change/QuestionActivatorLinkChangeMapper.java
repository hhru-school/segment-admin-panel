package ru.hhschool.segment.mapper.change;

import java.util.List;
import ru.hhschool.segment.model.dto.change.QuestionActivatorLinkChangeDto;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

public class QuestionActivatorLinkChangeMapper {
  public static QuestionActivatorLinkChangeDto questionActivatorLinkToDto(QuestionActivatorLink questionActivatorLink) {
    QuestionActivatorLinkChangeDto questionActivatorLinkChangeDto = new QuestionActivatorLinkChangeDto(
        questionActivatorLink.getId(),
        questionActivatorLink.isQuestionRequired(),
        questionActivatorLink.getQuestionVisibility(),
        questionActivatorLink.getEntrypoint().getTitle(),
        questionActivatorLink.getSegment().getTitle(),
        questionActivatorLink.getSegment().getParent().getId(),
        questionActivatorLink.getQuestion().getTitle()
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
