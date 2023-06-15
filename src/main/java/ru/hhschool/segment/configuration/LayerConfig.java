package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.abstracts.QuestionRequiredLinkDao;
import ru.hhschool.segment.dao.abstracts.ScreenQuestionLinkDao;
import ru.hhschool.segment.dao.abstracts.SegmentScreenEntrypointLinkDao;
import ru.hhschool.segment.dao.abstracts.SegmentStateLinkDao;
import ru.hhschool.segment.dao.impl.LayerDaoImpl;
import ru.hhschool.segment.service.LayerService;
import ru.hhschool.segment.service.SegmentService;

@Configuration
public class LayerConfig {
  @Bean
  public LayerService getLayerService(LayerDao layerDao,
                                      PlatformDao platformDao,
                                      SegmentService segmentService,
                                      SegmentStateLinkDao segmentStateLinkDao,
                                      QuestionRequiredLinkDao questionRequiredLinkDao,
                                      ScreenQuestionLinkDao screenQuestionLinkDao,
                                      SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao) {
    return new LayerService(layerDao,
        platformDao,
        segmentStateLinkDao,
        segmentService,
        questionRequiredLinkDao,
        screenQuestionLinkDao,
        segmentScreenEntrypointLinkDao);
  }

  @Bean
  public LayerDao getLayerDao() {
    return new LayerDaoImpl();
  }
}
