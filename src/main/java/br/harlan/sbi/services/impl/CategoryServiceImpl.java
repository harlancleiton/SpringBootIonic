package br.harlan.sbi.services.impl;

import br.harlan.sbi.entities.Category;
import br.harlan.sbi.repositories.CategoryRepository;
import br.harlan.sbi.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public Optional<Category> findById(Long id) {
        LOGGER.info("Looking for category by id: {}", id);
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        LOGGER.info("Looking for category by name: {}", name);
        return categoryRepository.findByName(name);
    }

    @Override
    public Category save(Category category) {
        LOGGER.info("Persisting category: {}", category);
        return categoryRepository.save(category);
    }
}