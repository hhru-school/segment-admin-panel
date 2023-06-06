package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.dao.impl.EntrypointDaoImpl;
import ru.hhschool.segment.service.EntrypointService;

@Configuration
public class EntrypointConfig {
  @Bean
  public EntrypointDao getEntrypointDao() {
    return new EntrypointDaoImpl();
  }

  @Bean
  public EntrypointService getEntrypointService(EntrypointDao entrypointDao) {
    return new EntrypointService(entrypointDao);
  }
}
