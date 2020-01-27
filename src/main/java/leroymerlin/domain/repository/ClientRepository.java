package leroymerlin.domain.repository;

import leroymerlin.domain.models.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository {
    Client findByName(String name);
    Client findByInn(String inn);
    boolean existsByName(String name);
    boolean existsByInn(String inn);
}
