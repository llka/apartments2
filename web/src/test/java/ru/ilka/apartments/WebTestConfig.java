package ru.ilka.apartments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ilka.apartments.rest.RestRequestLogic;

@Configuration
public class WebTestConfig {
    @Bean
    RestRequestLogic restRequestLogic(){
        return new RestRequestLogic();
    }
}
