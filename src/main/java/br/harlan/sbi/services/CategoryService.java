package br.harlan.sbi.services;

import br.harlan.sbi.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryService {
    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);

    Category insert(Category category);

    Category update(Category category);

    void delete(Long id);

    List<Category> findAll();

    Page<Category> findPage(Integer page, Integer linesPerPage, String direction, String orderBy);
}
