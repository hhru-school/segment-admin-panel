package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.dao.impl.EntrypointDaoImpl;

@Configuration
public class EntrypointConfig {
  @Bean
  public EntrypointDao getEntrypointDao() {
    return new EntrypointDaoImpl();
  }
}
