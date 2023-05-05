package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.dao.impl.AnswerDaoImpl;
import ru.hhschool.segment.dao.impl.EntrypointDaoImpl;
import ru.hhschool.segment.dao.impl.LayerDaoImpl;
import ru.hhschool.segment.dao.impl.QuestionActivatorLinkDaoImpl;
import ru.hhschool.segment.dao.impl.QuestionDaoImpl;
import ru.hhschool.segment.dao.impl.SegmentDaoImpl;

@Configuration
public class SegmentConfig {


  @Bean
  public LayerDao getLayerDao() {
    return new LayerDaoImpl();
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
  public EntrypointDao getEntrypointDao() {
    return new EntrypointDaoImpl();
  }


  @Bean
  public QuestionActivatorLinkDao getQuestionActivatorLinkDao() {
    return new QuestionActivatorLinkDaoImpl();
  }

  @Bean
  public AnswerDao getAnswerDao() {
    return new AnswerDaoImpl();
  }


}
