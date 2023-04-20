package ru.hhschool.segment.mapper.entrypointinfo;

import java.util.Set;
import ru.hhschool.segment.mapper.EntrypointMapper;
import ru.hhschool.segment.model.dto.entrypointinfo.EntrypointWitchQuestionStatusDto;
import ru.hhschool.segment.model.dto.entrypointinfo.QuestionStatusDto;
import ru.hhschool.segment.model.entity.Entrypoint;

public class EntrypointWitchQuestionStatusMapper {
  public static EntrypointWitchQuestionStatusDto questionToQuestionStatusDto(
      Entrypoint entrypoint,
      Set<QuestionStatusDto> questionStatusDtoSet
  ) {

    EntrypointWitchQuestionStatusDto entrypointWitchQuestionStatusDto =
        new EntrypointWitchQuestionStatusDto(
            EntrypointMapper.entrypointToDto(entrypoint),
            questionStatusDtoSet
        );

    return entrypointWitchQuestionStatusDto;
  }
}
