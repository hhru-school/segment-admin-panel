package ru.hhschool.segment;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import ru.hhschool.resource.ExampleResource;

@Component
public class JerseiAppConfig extends ResourceConfig {

    public JerseiAppConfig() {
        register(ExampleResource.class);
    }
}
