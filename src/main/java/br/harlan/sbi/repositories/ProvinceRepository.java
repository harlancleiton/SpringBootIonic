package br.harlan.sbi.repositories;

import br.harlan.sbi.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Optional<Province> findById(Long id);

    Optional<Province> findByName(String name);
}
