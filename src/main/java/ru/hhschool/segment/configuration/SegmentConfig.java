package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.dao.impl.LayerDaoImpl;
import ru.hhschool.segment.service.LayerService;
import ru.hhschool.segment.service.QuestionService;

@Configuration
public class SegmentConfig {
  @Bean
  public LayerService getLayerService(LayerDao layerDao) {
    return new LayerService(layerDao);
  }

  @Bean
  public LayerDao getLayerDao() {
    return new LayerDaoImpl();
  }

  @Bean
  public QuestionService getQuestionService(LayerDao layerDao, QuestionDao questionDao) {
    return new QuestionService(layerDao, questionDao);
  }
}
