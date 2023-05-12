package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.dao.impl.AnswerDaoImpl;
import ru.hhschool.segment.dao.impl.EntrypointDaoImpl;
import ru.hhschool.segment.dao.impl.LayerDaoImpl;
import ru.hhschool.segment.dao.impl.SegmentDaoImpl;
import ru.hhschool.segment.dao.impl.QuestionActivatorLinkDaoImpl;
import ru.hhschool.segment.dao.impl.QuestionDaoImpl;
import ru.hhschool.segment.service.AnswerService;
import ru.hhschool.segment.service.EntrypointService;
import ru.hhschool.segment.service.FilterService;
import ru.hhschool.segment.service.LayerService;
import ru.hhschool.segment.service.SegmentService;
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
  public SegmentService getSegmentService(SegmentDao segmentDao,
                                          LayerDao layerDao,
                                          QuestionActivatorLinkDao questionActivatorLinkDao){
    return new SegmentService(segmentDao, layerDao, questionActivatorLinkDao);
  }

  @Bean
  public SegmentDao getSegmentDao() {
    return new SegmentDaoImpl();
  }

  @Bean
  public QuestionDao getQuestionDao() {
    return new QuestionDaoImpl();
  }

  @Bean
  public QuestionService getQuestionService(LayerDao layerDao,
                                            QuestionDao questionDao,
                                            QuestionActivatorLinkDao questionActivatorLinkDao,
                                            AnswerService answerService,
                                            FilterService filterService) {
    return new QuestionService(layerDao, questionDao, questionActivatorLinkDao, answerService, filterService);
  }

  @Bean
  public EntrypointDao getEntrypointDao() {
    return new EntrypointDaoImpl();
  }

  @Bean
  public EntrypointService getEntrypointService(EntrypointDao entrypointDao, LayerDao layerDao, QuestionActivatorLinkDao questionActivatorLinkDao) {
    return new EntrypointService(entrypointDao, layerDao, questionActivatorLinkDao);
  }

  @Bean
  public QuestionActivatorLinkDao getQuestionActivatorLinkDao() {
    return new QuestionActivatorLinkDaoImpl();
  }

  @Bean
  public AnswerDao getAnswerDao() {
    return new AnswerDaoImpl();
  }

  @Bean
  public AnswerService getAnswerService(AnswerDao answerDao, QuestionDao questionDao) {
    return new AnswerService(answerDao, questionDao);
  }

  @Bean
  public FilterService getFilterService() {
    return new FilterService();
  }
}
