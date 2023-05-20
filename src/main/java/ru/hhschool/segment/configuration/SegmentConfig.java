package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.dao.abstracts.RoleDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.dao.impl.EntrypointDaoImpl;
import ru.hhschool.segment.dao.impl.SegmentDaoImpl;
import ru.hhschool.segment.service.FilterService;
import ru.hhschool.segment.service.SegmentService;

@Configuration
public class SegmentConfig {
  @Bean
  public SegmentDao getSegmentDao() {
    return new SegmentDaoImpl();
  }

  @Bean
  public SegmentService getSegmentService(SegmentDao segmentDao, RoleDao roleDao) {
    return new SegmentService(segmentDao, roleDao);
  }

  @Bean
  public EntrypointDao getEntrypointDao() {
    return new EntrypointDaoImpl();
  }

  @Bean
  public FilterService getFilterService() {
    return new FilterService();
  }
}
