package hexagonalArchitecture.adapters.configuration;

import hexagonalArchitecture.BankAccountApplication;
import hexagonalArchitecture.adapters.persistence.BankAccountRepository;
import hexagonalArchitecture.application.services.BankAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = BankAccountApplication.class)
public class BeanConfiguration {

    @Bean
    BankAccountService bankAccountService(BankAccountRepository repository) {
        return new BankAccountService(repository, repository);
    }
}
