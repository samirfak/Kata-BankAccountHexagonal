package repository;

import hexagonalArchitecture.adapters.persistence.BankAccountJPARepository;
import hexagonalArchitecture.adapters.persistence.BankAccountRepository;
import hexagonalArchitecture.application.model.BankAccount;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = BankAccountRepository.class)
public class BankAccountRepositoryTest {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @MockBean
    BankAccountJPARepository bankAccountJPARepository;

    @Test
    void when_saved_then_load_by_id() {
        Mockito.when(bankAccountJPARepository.findById(1L)).thenReturn(Optional.of(new BankAccount(1L, BigDecimal.valueOf(100))));
        assertTrue(bankAccountRepository.load(1L).isPresent());
    }

    @Test
    void when_not_saved_then_load_by_id_null() {
        Mockito.when(bankAccountJPARepository.findById(1L)).thenReturn(Optional.empty());
        assertTrue(bankAccountRepository.load(1L).isEmpty());
    }

    @Test
    void make_jpa_save_when_saved () {
        BankAccount bankAccount = new BankAccount(1L, BigDecimal.valueOf(100));
        bankAccountRepository.save(bankAccount);
        Mockito.verify(bankAccountJPARepository,times(1)).save(bankAccount) ;
    }
}
