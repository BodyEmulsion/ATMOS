package com.peltikhin.atmos.jpa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages = "com.peltikhin.atmos.jpa.repositories")
public class PersistenceConfiguration {
}
