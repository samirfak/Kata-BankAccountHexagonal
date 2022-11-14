package application.port.incoming;

import application.model.BankAccount;

import java.math.BigDecimal;

public interface DepositUseCase {
    BankAccount deposit(Long id, BigDecimal amount);
}
