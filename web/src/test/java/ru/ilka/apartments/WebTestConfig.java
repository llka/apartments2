package ru.ilka.apartments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ilka.apartments.command.*;
import ru.ilka.apartments.rest.RestRequestLogic;

@Configuration
public class WebTestConfig {
    @Bean
    RestRequestLogic restRequestLogic() {
        return new RestRequestLogic();
    }

    @Bean
    ShowAllCommand showAllCommand() {
        return new ShowAllCommand();
    }

    @Bean
    ShowByIdCommand showByIdCommand() {
        return new ShowByIdCommand();
    }

    @Bean
    AddApartmentCommand addApartmentCommand() {
        return new AddApartmentCommand();
    }

    @Bean
    BookApartmentCommand bookApartmentCommand() {
        return new BookApartmentCommand();
    }

    @Bean
    DeleteApartmentCommand deleteApartmentCommand() {
        return new DeleteApartmentCommand();
    }

    @Bean
    DeleteAllCommand deleteAllCommand() {
        return new DeleteAllCommand();
    }

    @Bean
    ShowAvailableCommand showAvailableCommand() {
        return new ShowAvailableCommand();
    }
}
