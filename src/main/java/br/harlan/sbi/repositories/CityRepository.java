package br.harlan.sbi.repositories;

import br.harlan.sbi.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findById(Long id);

    Optional<City> findByName(String name);
}
