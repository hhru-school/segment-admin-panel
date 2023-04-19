package ru.hhschool.segment.model.dto.entrypointinfo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import ru.hhschool.segment.model.dto.EntrypointDto;

public class EntrypointWitchQuestionStatusDto {
  EntrypointDto entrypointDto;
  Set<QuestionStatusDto> questionStatusDtoList;

  public EntrypointWitchQuestionStatusDto() {
  }

  public EntrypointWitchQuestionStatusDto(EntrypointDto entrypointDto, Collection<QuestionStatusDto> questionStatusDtoList) {
    this.entrypointDto = entrypointDto;
    this.questionStatusDtoList = new HashSet<>(questionStatusDtoList);
  }

  public EntrypointDto getEntrypointDto() {
    return entrypointDto;
  }

  public void setEntrypointDto(EntrypointDto entrypointDto) {
    this.entrypointDto = entrypointDto;
  }

  public Set<QuestionStatusDto> getQuestionStatusDtoList() {
    return questionStatusDtoList;
  }

  public void setQuestionStatusDtoList(Set<QuestionStatusDto> questionStatusDtoList) {
    this.questionStatusDtoList = questionStatusDtoList;
  }
}
