package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.AnswerDao;

import javax.inject.Inject;

public class AnswerService {
  private final AnswerDao answerDao;

  @Inject
  public AnswerService(AnswerDao answerDao) {
    this.answerDao = answerDao;
  }
}
