package leroymerlin.domain.repository;

import leroymerlin.domain.models.Account;

public interface AccountRepository {
    Account findByNumber(String number);
    boolean existsByNumber(String number);
}
