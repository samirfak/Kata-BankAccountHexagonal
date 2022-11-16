package services;

import hexagonalArchitecture.adapters.persistence.BankAccountRepository;
import hexagonalArchitecture.application.model.BankAccount;
import hexagonalArchitecture.application.services.BankAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest(classes = BankAccountService.class)
public class BankAccountServiceTest {
    @Autowired
    private BankAccountService accountService;

    @MockBean
    private BankAccountRepository accountRepository;

    @Test
    void throw_illegalArgumentException_when_withdraw_account_is_not_found() {
        Mockito.when(accountRepository.load(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.withdraw(1L, BigDecimal.valueOf(100)));
    }

    @Test
    void throw_IllegalArgumentException_when_amount_greater_than_balance() {
        Mockito.when(accountRepository.load(1L)).thenReturn(Optional.of(new BankAccount(1L, BigDecimal.valueOf(100))));
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(1L, BigDecimal.valueOf(200)));
    }

    @Test
    void return_correct_updated_account_when_successful_withdrawal() {
        Mockito.when(accountRepository.load(2L)).thenReturn(Optional.of(new BankAccount(2L, BigDecimal.valueOf(100))));
        BankAccount account = accountService.withdraw(2L, BigDecimal.valueOf(30));
        Assertions.assertEquals(account.getBalance(), BigDecimal.valueOf(70));
    }

    @Test
    void throw_IllegalArgument_Exception_when_deposit_amount_less_or_equal_to_0() {
        Mockito.when(accountRepository.load(1L)).thenReturn(Optional.of(new BankAccount(1L, BigDecimal.valueOf(100))));
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.deposit(1L, BigDecimal.valueOf(-2)));
    }

    @Test
    void return_correct_updated_account_when_correct_deposit_amount() {
        Mockito.when(accountRepository.load(2L)).thenReturn(Optional.of(new BankAccount(2L, BigDecimal.valueOf(100))));
        BankAccount account = accountService.deposit(2L, BigDecimal.valueOf(30));
        Assertions.assertEquals(account.getBalance(), BigDecimal.valueOf(130));
    }

    @Test
    void throw_illegalArgumentException_when_deposit_account_is_not_found() {
        Mockito.when(accountRepository.load(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.deposit(1L, BigDecimal.valueOf(100)));
    }
}
