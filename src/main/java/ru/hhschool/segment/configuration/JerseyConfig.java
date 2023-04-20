package ru.hhschool.segment.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.resource.LayerResource;
import ru.hhschool.segment.resource.QuestionResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    register(LayerResource.class);
    register(QuestionResource.class);
  }
}
