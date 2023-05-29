package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.*;
import ru.hhschool.segment.dao.impl.SegmentDaoImpl;
import ru.hhschool.segment.service.SegmentService;

@Configuration
public class SegmentConfig {
  @Bean
  public SegmentDao getSegmentDao() {
    return new SegmentDaoImpl();
  }

  @Bean
  public SegmentService getSegmentService(LayerDao layerDao,
                                          SegmentDao segmentDao,
                                          EntrypointDao entrypointDao,
                                          SegmentStateLinkDao segmentStateLinkDao,
                                          ScreenQuestionLinkDao screenQuestionLinkDao,
                                          SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao,
                                          QuestionRequiredLinkDao questionRequiredLinkDao,
                                          RoleDao roleDao,
                                          PlatfromDao platfromDao) {
    return new SegmentService(layerDao, segmentDao, entrypointDao, segmentStateLinkDao, screenQuestionLinkDao, segmentScreenEntrypointLinkDao, questionRequiredLinkDao, roleDao, platfromDao);
  }
}
