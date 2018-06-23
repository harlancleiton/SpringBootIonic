package br.harlan.sbi.factory.dtos;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.dto.CategoryDTO;
import org.jetbrains.annotations.NotNull;

public class CategoryDTOFactory {
    @NotNull
    public static CategoryDTO create(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
