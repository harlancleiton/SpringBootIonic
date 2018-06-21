package br.harlan.sbi.services.impl;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.domain.Product;
import br.harlan.sbi.repositories.CategoryRepository;
import br.harlan.sbi.repositories.ProductRepository;
import br.harlan.sbi.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Optional<Product> findById(Long id) {
        LOGGER.info("Looking for product by Id: {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> findPage(String name, List<Long> ids, Integer page, Integer linesPerPage, String direction, String orderBy) {
        LOGGER.info("Looking for product by Page and CategoriesIDs. Page: {}. CategoriesIDs: {}", page, ids);
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categories = categoryRepository.findAllById(ids);
        return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }

    @Override
    public Product insert(Product product) {
        product.setId(null);
        LOGGER.info("Persisting category: {}", product);
        return productRepository.save(product);
    }
}
