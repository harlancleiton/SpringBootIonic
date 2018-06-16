package br.harlan.sbi.factories.dtos;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.dtos.CategoryDTO;
import org.jetbrains.annotations.NotNull;

public class CategoryDTOFactory {
    @NotNull
    public static CategoryDTO create(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
