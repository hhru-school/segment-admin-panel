package ru.hhschool.segment.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import ru.hhschool.segment.resource.ExampleResource;
import ru.hhschool.segment.resource.LayerResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ExampleResource.class);
        register(LayerResource.class);
    }
}
