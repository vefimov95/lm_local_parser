package ru.leroymerlin.finparser.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leroymerlin.finparser.domain.models.TransactionType;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

    TransactionType findByCode(String code);

    boolean existsByCode(String code);
}
