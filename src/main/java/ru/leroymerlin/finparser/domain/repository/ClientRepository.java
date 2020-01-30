package ru.leroymerlin.finparser.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leroymerlin.finparser.domain.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);

    Client findByInn(String inn);

    boolean existsByName(String name);

    boolean existsByInn(String inn);
}
