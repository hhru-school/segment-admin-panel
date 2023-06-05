package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.SegmentScreenEntrypointLinkDao;
import ru.hhschool.segment.dao.impl.SegmentScreenEntrypointLinkDaoImpl;

@Configuration
public class SegmentScreenEntrypointLinkConfig {
  @Bean
  public SegmentScreenEntrypointLinkDao getSegmentScreenEntrypointLinkDao() {
    return new SegmentScreenEntrypointLinkDaoImpl();
  }
}
