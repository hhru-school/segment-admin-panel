package ru.hhschool.segment.configuration;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class HibernateConfig {
  private static String HIBERNATE_PROPERTIES = "hibernate-prod.properties";
  protected static EmbeddedPostgres pg;
  @Bean
  public SessionFactory createSessionFactory() throws IOException {
    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
      .loadProperties(HIBERNATE_PROPERTIES)
      .build();

    MetadataSources metadataSources = new MetadataSources(serviceRegistry);

    return metadataSources.buildMetadata().buildSessionFactory();
  }
}
