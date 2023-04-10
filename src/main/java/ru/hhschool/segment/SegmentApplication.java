package ru.hhschool.segment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SegmentApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SegmentApplication.class, args);
  }

}
