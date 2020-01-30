package ru.leroymerlin.finparser.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leroymerlin.finparser.domain.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNumber(String number);

    boolean existsByNumber(String number);
}
