package ru.hhschool.segment.mapper;

import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.entity.Answer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AnswerMapper {
  public static AnswerDto toDto(Answer entity) {
    AnswerDto answerDto = new AnswerDto();
    answerDto.setId(entity.getId());
    answerDto.setTitle(entity.getTitle());
    return answerDto;
  }


}
