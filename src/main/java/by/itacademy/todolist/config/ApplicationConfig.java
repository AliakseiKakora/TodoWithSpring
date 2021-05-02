package by.itacademy.todolist.config;

import by.itacademy.todolist.util.DateParser;
import by.itacademy.todolist.util.DateParserImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:data-base.properties")
public class ApplicationConfig {

    @Bean
    public DateParser dateParser() {
        return new DateParserImpl();
    }
}
