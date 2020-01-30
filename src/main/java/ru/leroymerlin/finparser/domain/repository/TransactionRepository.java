package ru.leroymerlin.finparser.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leroymerlin.finparser.domain.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
