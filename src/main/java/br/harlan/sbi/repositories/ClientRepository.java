package br.harlan.sbi.repositories;

import br.harlan.sbi.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long id);

    Optional<Client> findByName(String name);

    Optional<Client> findByEmail(String email);
}
