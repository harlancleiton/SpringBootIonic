package br.harlan.sbi.repositories;

import br.harlan.sbi.entities.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    Optional<Telephone> findById(Long id);

    Optional<Telephone> findByTelephone(String telephone);
}
