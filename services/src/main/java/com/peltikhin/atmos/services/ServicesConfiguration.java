package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.PersistenceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.peltikhin.atmos.services")
@Import({PersistenceConfiguration.class})
public class ServicesConfiguration {
}
