package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.PlatfromDao;
import ru.hhschool.segment.dao.impl.PlatformDaoImpl;

@Configuration
public class PlatformConfig {
  @Bean
  public PlatfromDao getPlatfromDao() {
    return new PlatformDaoImpl();
  }
}
