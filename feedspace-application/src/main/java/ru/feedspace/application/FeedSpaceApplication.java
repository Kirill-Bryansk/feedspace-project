package ru.feedspace.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "ru.feedspace.application",    // Контроллеры, сервисы из application модуля
        "ru.feedspace.api.handler"     // Обработчики исключений из api модуля
})
@EntityScan(basePackages = "ru.feedspace.domain.model")
@EnableJpaRepositories(basePackages = "ru.feedspace.domain.repository")
public class FeedSpaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeedSpaceApplication.class, args);
    }
}