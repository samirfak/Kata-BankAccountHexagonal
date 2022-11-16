package hexagonalArchitecture.application.port.incoming;

import hexagonalArchitecture.application.model.BankAccount;

import java.math.BigDecimal;

public interface WithdrawUseCase {
    BankAccount withdraw(Long id, BigDecimal amount);
}
