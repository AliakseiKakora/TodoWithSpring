package by.itacademy.todolist.config;

import by.itacademy.todolist.util.DateParser;
import by.itacademy.todolist.util.DateParserImpl;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:data-base.properties")
@ComponentScan({"by.itacademy.todolist"})
@Import({PersistenceConfig.class, SecurityConfig.class})
public class ApplicationConfig {

    @Bean
    public DateParser dateParser() {
        return new DateParserImpl();
    }
}
