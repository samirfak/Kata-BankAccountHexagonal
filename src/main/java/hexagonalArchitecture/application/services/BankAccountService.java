package hexagonalArchitecture.application.services;

import hexagonalArchitecture.application.model.BankAccount;
import hexagonalArchitecture.application.port.incoming.DepositUseCase;
import hexagonalArchitecture.application.port.incoming.HistoryUseCase;
import hexagonalArchitecture.application.port.incoming.WithdrawUseCase;
import hexagonalArchitecture.application.port.outcoming.LoadAccountPort;
import hexagonalArchitecture.application.port.outcoming.SaveAccountPort;
import org.springframework.stereotype.Service;

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
