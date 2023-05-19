package ru.hhschool.segment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.dao.abstracts.RoleDao;
import ru.hhschool.segment.dao.impl.RoleDaoImpl;
import ru.hhschool.segment.service.RoleService;

@Configuration
public class RoleConfig {
  @Bean
  public RoleService getRoleService(RoleDao roleDao) {
    return new RoleService(roleDao);
  }

  @Bean
  public RoleDao getRoleDao() {
    return new RoleDaoImpl();
  }
}
