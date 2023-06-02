package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.ScreenQuestionLinkDao;
import ru.hhschool.segment.dao.impl.ScreenQuestionLinkDaoImpl;

@Configuration
public class ScreenQuestionLinkConfig {
  @Bean
  public ScreenQuestionLinkDao getScreenQuestionLinkDao() {
    return new ScreenQuestionLinkDaoImpl();
  }
}
