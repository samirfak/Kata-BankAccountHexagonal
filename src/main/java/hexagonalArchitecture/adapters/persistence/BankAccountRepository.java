package hexagonalArchitecture.adapters.persistence;

import hexagonalArchitecture.application.model.BankAccount;
import hexagonalArchitecture.application.port.outcoming.LoadAccountPort;
import hexagonalArchitecture.application.port.outcoming.SaveAccountPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankAccountRepository implements LoadAccountPort, SaveAccountPort {
    private final BankAccountJPARepository repository;

    // Constructor
    public BankAccountRepository (BankAccountJPARepository repository) {
        this.repository = repository;
    }
    @Override
    public Optional<BankAccount> load(Long id) {
        return repository.findById(id);
    }
    @Override
    public void save(BankAccount bankAccount) {
        repository.save(bankAccount);
    }

}
