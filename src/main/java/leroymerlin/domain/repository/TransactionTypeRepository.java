package leroymerlin.domain.repository;

import leroymerlin.domain.models.TransactionType;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository{
    TransactionType findByCode(String code);
    boolean existsByCode(String code);
}
