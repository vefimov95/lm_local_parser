package leroymerlin.domain.repository;

import leroymerlin.domain.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByReference(String reference);
    boolean existsByReference(String reference);
}
