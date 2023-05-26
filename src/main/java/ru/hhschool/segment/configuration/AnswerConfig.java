package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.dao.impl.AnswerDaoImpl;
import ru.hhschool.segment.service.AnswerService;

@Configuration
public class AnswerConfig {
  @Bean
  public AnswerDao getAnswerDao() {
    return new AnswerDaoImpl();
  }

  @Bean
  public AnswerService getAnswerService(AnswerDao answerDao, QuestionDao questionDao) {
    return new AnswerService(answerDao, questionDao);
  }

}
