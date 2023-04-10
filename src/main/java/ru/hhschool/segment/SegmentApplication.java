package ru.hhschool.segment;

import org.hibernate.HibernateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.hhschool.segment.dao.TestDao;

@SpringBootApplication
public class SegmentApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SegmentApplication.class, args);
    try {
      TestDao testDao = context.getBean(TestDao.class);
      System.out.println(testDao.getSessionFactory().getCurrentSession().getStatistics());;
    } catch (HibernateException hex){
      System.out.println(hex.getMessage());
    }
  }

}
