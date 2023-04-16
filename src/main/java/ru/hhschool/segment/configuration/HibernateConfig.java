package ru.hhschool.segment.configuration;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.impl.LayerDaoImpl;
import ru.hhschool.segment.service.LayerService;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:hibernate-${spring.profiles.active}.properties")
public class HibernateConfig {

  @Value("${hibernate.connection.driver_class}")
  private String driverClassName;
  @Value("${hibernate.connection.username}")
  private String username;
  @Value("${hibernate.connection.password}")
  private String password;
  @Value("${hibernate.connection.url}")
  private String url;
  @Value("${hibernate.dialect}")
  private String dialect;
  @Value("${hibernate.show_sql}")
  private String showSql;

  @Bean
  public LayerService getLayerService(LayerDao layerDao) {
    return new LayerService(layerDao);
  }

  @Bean
  public LayerDao getLayerDao() {
    return new LayerDaoImpl();
  }

  @Bean
  public LocalSessionFactoryBean getSessionFactory() {
    Properties properties = new Properties();
    properties.put(Environment.DIALECT, dialect);
    properties.put(Environment.SHOW_SQL, showSql);
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    factoryBean.setPackagesToScan("ru.hhschool.segment.model.entity");
    factoryBean.setDataSource(dataSource());
    factoryBean.setHibernateProperties(properties);
    return factoryBean;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setUrl(url);
    return dataSource;
  }

}