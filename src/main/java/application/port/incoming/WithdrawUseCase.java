package application.port.incoming;

import application.model.BankAccount;

import java.math.BigDecimal;

public interface WithdrawUseCase {
    BankAccount withdraw(Long id, BigDecimal amount);
}
