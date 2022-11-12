package adapters.persistence;

import application.model.BankAccount;
import application.port.outcoming.LoadAccountPort;
import application.port.outcoming.SaveAccountPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class BankAccountRepository implements LoadAccountPort, SaveAccountPort {
    private final BankAccountJPARepository repository;

    // Constructor
    public BankAccountRepository (BankAccountJPARepository repository) {
        this.repository = repository;
    }

    public Optional<BankAccount> load(Long id) {
        return repository.findById(id);
    }

    public void save(BankAccount bankAccount) {
        repository.save(bankAccount);
    }

}
