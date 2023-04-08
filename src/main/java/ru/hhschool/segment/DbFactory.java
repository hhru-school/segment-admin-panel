package ru.hhschool.segment;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DbFactory {
  public static SessionFactory createSessionFactory() {
    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
      .loadProperties("hibernate.properties")
      .build();

    MetadataSources metadataSources = new MetadataSources(serviceRegistry);

    return metadataSources.buildMetadata().buildSessionFactory();
  }

}
