package leroymerlin.domain.repository;

import leroymerlin.domain.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    TransactionType findByCode(String code);
    boolean existsByCode(String code);
}
