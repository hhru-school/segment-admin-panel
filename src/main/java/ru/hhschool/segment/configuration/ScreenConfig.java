package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.dao.abstracts.ScreenDao;
import ru.hhschool.segment.dao.impl.ScreenDaoImpl;
import ru.hhschool.segment.service.ScreenService;

@Configuration
public class ScreenConfig {
  @Bean
  public ScreenService getScreenService(ScreenDao screenDao, PlatformDao platformDao, QuestionDao questionDao) {
    return new ScreenService(screenDao, platformDao, questionDao);
  }

  @Bean
  public ScreenDao getScreenDao() {
    return new ScreenDaoImpl();
  }
}
