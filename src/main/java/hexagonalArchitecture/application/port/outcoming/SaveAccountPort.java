package hexagonalArchitecture.application.port.outcoming;

import hexagonalArchitecture.application.model.BankAccount;

public interface SaveAccountPort {
    void save(BankAccount bankAccount);
}
