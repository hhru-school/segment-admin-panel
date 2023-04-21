package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.mapper.AnswerMapper;
import ru.hhschool.segment.model.dto.AnswerDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AnswerService {
  private final AnswerDao answerDao;

  @Inject
  public AnswerService(AnswerDao answerDao) {
    this.answerDao = answerDao;
  }

  public List<AnswerDto> getAllAsnwerDtoListByListId(List<Long> answersIdList) {
    List<AnswerDto> answerDtoList = new ArrayList<>();
    answersIdList.forEach(answerId -> {
      answerDtoList.add(AnswerMapper.toDto(answerDao.findById(answerId).orElseGet(null)));
    });
    return answerDtoList;
  }
}
