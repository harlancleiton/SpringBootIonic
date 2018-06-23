package br.harlan.sbi.factory.domain;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.dto.CategoryDTO;
import org.jetbrains.annotations.NotNull;

public class CategoryFactory {
    @NotNull
    public static Category create(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }
}
