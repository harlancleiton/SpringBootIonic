package br.harlan.sbi.factories.domain;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.dtos.CategoryDTO;
import org.jetbrains.annotations.NotNull;

public class CategoryFactory {
    @NotNull
    public static Category create(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }
}
