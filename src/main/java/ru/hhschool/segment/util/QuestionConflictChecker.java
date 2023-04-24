package ru.hhschool.segment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import ru.hhschool.segment.mapper.change.QuestionChangeMapper;
import ru.hhschool.segment.model.dto.change.QuestionChangeDto;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.enums.ConflictStatus;

public class QuestionConflictChecker {
  public static List<QuestionChangeDto> getQuestionConflictList(List<Question> listA, List<Question> listB) {
    List<QuestionChangeDto> conflictList = new ArrayList<>();

    if (listA != null && listB != null) {
      List<QuestionChangeDto> listLayerA = QuestionChangeMapper.questionChangeListToDtoList(listA);
      List<QuestionChangeDto> listLayerB = QuestionChangeMapper.questionChangeListToDtoList(listB);

      boolean conflict = listLayerA.stream()
          .map(QuestionChangeDto::getTitle)
          .anyMatch(element -> listLayerB.contains(element));
      if (conflict) {
        Set<String> titleB = listLayerB
            .stream()
            .map(QuestionChangeDto::getTitle)
            .collect(Collectors.toSet());

        for (QuestionChangeDto questionChangeDto : listLayerA) {
          if (titleB.contains(questionChangeDto.getTitle())) {
            questionChangeDto.setConflict(ConflictStatus.CONFLICT.isConflict());
            conflictList.add(questionChangeDto);
          }
        }
      }
    }
    return conflictList;
  }

}
