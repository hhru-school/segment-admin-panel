package ru.hhschool.segment.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.resource.EntrypointResource;
import ru.hhschool.segment.resource.LayerResource;
import ru.hhschool.segment.resource.SegmentResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    register(LayerResource.class);
    register(SegmentResource.class);
    register(EntrypointResource.class);
  }
}
