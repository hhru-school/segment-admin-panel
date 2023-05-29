package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.SegmentStateLinkDao;
import ru.hhschool.segment.dao.impl.SegmentStateLinkDaoImpl;

@Configuration
public class SegmentStateLinkConfig {
  @Bean
  public SegmentStateLinkDao getSegmentStateLinkDao() {
    return new SegmentStateLinkDaoImpl();
  }
}
