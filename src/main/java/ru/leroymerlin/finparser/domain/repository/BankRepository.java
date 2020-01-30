package ru.leroymerlin.finparser.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leroymerlin.finparser.domain.models.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Bank findByName(String name);

    Bank findBySwiftNumber(String swiftNumber);

    boolean existsByName(String name);

    boolean existsBySwiftNumber(String swiftNumber);
}
