package br.harlan.sbi.repositories;

import br.harlan.sbi.domain.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {
    Optional<RequestItem> findById(Long id);
}
