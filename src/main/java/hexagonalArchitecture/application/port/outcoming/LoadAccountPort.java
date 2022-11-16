package hexagonalArchitecture.application.port.outcoming;

import hexagonalArchitecture.application.model.BankAccount;

import java.util.Optional;

public interface LoadAccountPort {
    Optional<BankAccount> load(Long id);
}
