package br.harlan.sbi.services.impl;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.repositories.CategoryRepository;
import br.harlan.sbi.services.CategoryService;
import br.harlan.sbi.services.exceptions.DataIntegrityException;
import br.harlan.sbi.services.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public Optional<Category> findById(Long id) {
        LOGGER.info("Looking for category by id: {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent())
            throw new ObjectNotFoundException("Object not found. Id: " + id + ". Class: " + category.getClass().getName());
        ;
        return category;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByName(String name) {
        LOGGER.info("Looking for category by name: {}", name);
        return categoryRepository.findByName(name);
    }

    @Override
    public Category insert(Category category) {
        category.setId(null);
        LOGGER.info("Persisting category: {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        findById(category.getId());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(
                    "It is not possible to exclude a Category containing Products", e.getCause());
        }

    }
}
