package leroymerlin.domain.repository;

import leroymerlin.domain.models.Bank;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository  {
    Bank findByName(String name);
    Bank findBySwiftNumber(String swiftNumber);
    boolean existsByName(String name);
    boolean existsBySwiftNumber(String swiftNumber);
}
