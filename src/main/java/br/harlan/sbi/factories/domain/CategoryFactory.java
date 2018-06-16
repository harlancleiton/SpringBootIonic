package br.harlan.sbi.factories.domain;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.dtos.CategoryDTO;

public class CategoryFactory {
    public static Category create(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }
}
