package adapters.persistence;

import application.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BankAccountJPARepository extends JpaRepository<BankAccount, Long> {
    public Optional<BankAccount> findById(Long id);
}
