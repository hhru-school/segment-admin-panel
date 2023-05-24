package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.impl.PlatformDaoImpl;

@Configuration
public class PlatformConfig {
  @Bean
  public PlatformDao getPlatformDao() {
    return new PlatformDaoImpl();
  }
}
