package hexagonalArchitecture.application.port.incoming;

import hexagonalArchitecture.application.model.BankAccount;

import java.math.BigDecimal;

public interface DepositUseCase {
    BankAccount deposit(Long id, BigDecimal amount);
}
