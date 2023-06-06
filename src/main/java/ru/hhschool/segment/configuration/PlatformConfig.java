package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.impl.PlatformDaoImpl;
import ru.hhschool.segment.service.PlatformService;

@Configuration
public class PlatformConfig {
  @Bean
  public PlatformDao getPlatformDao() {
    return new PlatformDaoImpl();
  }

  @Bean
  public PlatformService getPlatformService(PlatformDao platformDao) {
    return new PlatformService(platformDao);
  }
}
