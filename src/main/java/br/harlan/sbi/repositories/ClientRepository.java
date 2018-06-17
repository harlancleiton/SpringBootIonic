package br.harlan.sbi.repositories;

import br.harlan.sbi.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long id);

    @Transactional(readOnly = true)
    Optional<Client> findByName(String name);

    @Transactional(readOnly = true)
    Optional<Client> findByEmail(String email);

    @Transactional(readOnly = true)
    Optional<Client> findByCpfCnpj(String cpfCnpj);
}
