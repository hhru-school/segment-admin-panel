package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.impl.LayerDaoImpl;
import ru.hhschool.segment.service.LayerService;
import ru.hhschool.segment.service.SegmentService;

@Configuration
public class LayerConfig {
  @Bean
  public LayerService getLayerService(LayerDao layerDao, PlatformDao platformDao, SegmentService segmentService) {
    return new LayerService(layerDao, platformDao, segmentService);
  }

  @Bean
  public LayerDao getLayerDao() {
    return new LayerDaoImpl();
  }
}
