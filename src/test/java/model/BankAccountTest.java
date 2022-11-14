package model;

import application.model.BankAccount;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    BankAccount account1, account2;
    @BeforeEach
    void setUp() {
        account1 = new BankAccount(1L, BigDecimal.valueOf(2000));
        account2 = new BankAccount(2L, BigDecimal.valueOf(2000));
    }
    //@ParameterizedTest
    //@ValueSource
    @Test
    void unaccepted_withdrawal() {
        assertFalse(account1.withdraw(BigDecimal.valueOf(3000)));
    }

    @Test
    void illegal_withdrawal() {
        assertThrows(IllegalArgumentException.class, () -> account1.withdraw(BigDecimal.valueOf(-1)));
    }

    @Test
    void illegal_deposit() {
        assertThrows(IllegalArgumentException.class, () -> account1.deposit(BigDecimal.valueOf(-1)));
    }

    @Test
    void successful_withdrawal() {
        assertTrue(account1.withdraw(BigDecimal.valueOf(1000)));
        assertTrue(account1.getBalance().compareTo(BigDecimal.valueOf(1000)) == 0);
    }

    @Test
    void successful_deposit() {
        account1.deposit(BigDecimal.valueOf(1000));
        assertTrue(account1.getBalance().compareTo(BigDecimal.valueOf(3000)) == 0);
    }

    @Test
    void histories() {
        account2.deposit(BigDecimal.valueOf(2000));
        account2.withdraw(BigDecimal.valueOf(3000));
        System.out.println(account2.history());
    }

}
