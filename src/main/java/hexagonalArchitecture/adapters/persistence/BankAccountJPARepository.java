package hexagonalArchitecture.adapters.persistence;

import hexagonalArchitecture.application.model.BankAccount;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BankAccountJPARepository extends JpaRepository<BankAccount, Long> {
}
