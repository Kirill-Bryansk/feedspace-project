package ru.feedspace.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("ru.feedspace.domain.model")
@EnableJpaRepositories("ru.feedspace.domain.repository")
public class JpaConfig {
}