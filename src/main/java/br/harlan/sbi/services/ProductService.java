package br.harlan.sbi.services;

import br.harlan.sbi.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);

    Page<Product> findPage(String name, List<Long> ids, Integer page, Integer linesPerPage, String direction, String orderBy);
}
