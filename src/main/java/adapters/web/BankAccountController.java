package adapters.web;

import application.model.BankAccount;
import application.port.incoming.DepositUseCase;
import application.port.incoming.HistoryUseCase;
import application.port.incoming.WithdrawUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
@RestController
@RequestMapping("/account")
public class BankAccountController {

    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;
    private final HistoryUseCase historyUseCase;
    private static Logger logger = Logger.getLogger(BankAccountController.class.getName());
    public BankAccountController(DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase, HistoryUseCase historyUseCase) {
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
        this.historyUseCase = historyUseCase;
    }

    @PostMapping(value = "/{id}/deposit/{amount}")
    public ResponseEntity<BankAccount> deposit(@PathVariable final Long id, @PathVariable final BigDecimal amount) {
        BankAccount account = depositUseCase.deposit(id, amount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
    }

    @PostMapping(value = "/{id}/withdraw/{amount}")
    public ResponseEntity<BankAccount> withdraw(@PathVariable final Long id, @PathVariable final BigDecimal amount) {
        BankAccount account = withdrawUseCase.withdraw(id, amount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
    }

    @PostMapping(value = "/{id}/history")
    public ResponseEntity<String> history(@PathVariable final Long id) {
        String history = historyUseCase.history(id);
        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public ResponseEntity<String> handleNoSuchElementException(final NoSuchElementException ex) {
        logger.severe("Error, account doesn't exist: " + ex);
        return ResponseEntity.status(404).body("Error, account doesn't exist");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<String> handleIllegalArgumentException(final IllegalArgumentException ex) {
        logger.severe("Error, account doesn't exist: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error, invalid amount");
    }
}
