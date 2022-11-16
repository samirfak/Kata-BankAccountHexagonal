package hexagonalArchitecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"hexagonalArchitecture"})
@SpringBootApplication
public class BankAccountApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BankAccountApplication.class, args);
    }

}
