package leroymerlin.domain.repository;

import leroymerlin.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByNumber(String number);
    boolean existsByNumber(String number);
}
