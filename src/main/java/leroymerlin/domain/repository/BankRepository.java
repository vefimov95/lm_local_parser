package leroymerlin.domain.repository;

import leroymerlin.domain.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByName(String name);
    Bank findBySwiftNumber(String swiftNumber);
    boolean existsByName(String name);
    boolean existsBySwiftNumber(String swiftNumber);
}
