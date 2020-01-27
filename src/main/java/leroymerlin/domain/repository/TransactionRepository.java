package leroymerlin.domain.repository;

import leroymerlin.domain.models.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository {
    Transaction findByReference(String reference);
    boolean existsByReference(String reference);
}
