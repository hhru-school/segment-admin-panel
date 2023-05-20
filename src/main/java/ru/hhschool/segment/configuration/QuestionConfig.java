package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.dao.impl.QuestionDaoImpl;

@Configuration
public class QuestionConfig {
  @Bean
  public QuestionDao getQuestionDao() {
    return new QuestionDaoImpl();
  }

}
