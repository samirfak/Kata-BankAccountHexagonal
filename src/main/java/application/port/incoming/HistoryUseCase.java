package application.port.incoming;

import application.model.BankAccount;

public interface HistoryUseCase {
    String history(Long id);
}
