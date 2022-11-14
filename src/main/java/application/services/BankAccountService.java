package application.services;

import application.model.BankAccount;
import application.port.incoming.DepositUseCase;
import application.port.incoming.HistoryUseCase;
import application.port.incoming.WithdrawUseCase;
import application.port.outcoming.LoadAccountPort;
import application.port.outcoming.SaveAccountPort;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class BankAccountService implements DepositUseCase, WithdrawUseCase, HistoryUseCase {

    private LoadAccountPort loadAccountPort;
    private SaveAccountPort saveAccountPort;

    public BankAccountService(LoadAccountPort loadAccountPort, SaveAccountPort saveAccountPort) {
        this.loadAccountPort = loadAccountPort;
        this.saveAccountPort = saveAccountPort;
    }
    @Override
    public BankAccount deposit(Long id, BigDecimal amount) {
        BankAccount account = loadAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);
        account.deposit(amount);
        saveAccountPort.save(account);
        return account;
    }
    @Override
    public BankAccount withdraw(Long id, BigDecimal amount) {
        BankAccount account = loadAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);

        boolean success = account.withdraw(amount);

        if(!success) {
            throw new IllegalArgumentException();
        }
        saveAccountPort.save(account);
        return account;
    }

    @Override
    public String history(Long id) {
        BankAccount account = loadAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);
        return account.history();
       }
}
