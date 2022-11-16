package hexagonalArchitecture.adapters.web;

import hexagonalArchitecture.application.model.BankAccount;
import hexagonalArchitecture.application.port.incoming.DepositUseCase;
import hexagonalArchitecture.application.port.incoming.HistoryUseCase;
import hexagonalArchitecture.application.port.incoming.WithdrawUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/{id}/deposit/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankAccount> deposit(@PathVariable final Long id, @PathVariable final BigDecimal amount) {
        BankAccount account = depositUseCase.deposit(id, amount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
    }

    @PostMapping(value = "/{id}/withdraw/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankAccount> withdraw(@PathVariable final Long id, @PathVariable final BigDecimal amount) {
        BankAccount account = withdrawUseCase.withdraw(id, amount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
    }

    @PostMapping(value = "/{id}/history", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
