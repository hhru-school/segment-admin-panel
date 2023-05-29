package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.QuestionRequiredLinkDao;
import ru.hhschool.segment.dao.impl.QuestionRequiredLinkDaoImpl;

@Configuration
public class QuestionRequiredLinkConfig {
  @Bean
  public QuestionRequiredLinkDao getQuestionRequiredLinkDao() {
    return new QuestionRequiredLinkDaoImpl();
  }
}
