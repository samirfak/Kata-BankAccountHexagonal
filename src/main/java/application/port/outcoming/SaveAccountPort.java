package application.port.outcoming;

import application.model.BankAccount;

public interface SaveAccountPort {
    void save(BankAccount bankAccount);
}
