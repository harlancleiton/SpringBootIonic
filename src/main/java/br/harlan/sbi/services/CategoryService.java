package br.harlan.sbi.services;

import br.harlan.sbi.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryService {
    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);

    Category save(Category category);
}
