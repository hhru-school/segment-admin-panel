package ru.hhschool.segment;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SegmentApplication {
  protected static SessionFactory sessionFactory;
  public static void main(String[] args) {
    SpringApplication.run(SegmentApplication.class, args);
    sessionFactory = DbFactory.createSessionFactory();
    try {
      System.out.println(sessionFactory.getCurrentSession().getStatistics());;
    } catch (HibernateException hex){
      System.out.println(hex.getMessage());
    }
  }

}
