import adapters.persistence.BankAccountRepository;
import application.model.BankAccount;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankAccountApplication {
    public static void main(final String[] args) {
        SpringApplication.run(BankAccountApplication.class, args);
    }

    @Bean
    public CommandLineRunner bootstrapData(BankAccountRepository repository) {
        return (args) -> {
            BigDecimal initialBalance = BigDecimal.valueOf(1000);
            BankAccount bankAccount = new BankAccount(0L, initialBalance);
            repository.save(bankAccount);
        };
    }
}
