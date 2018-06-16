package br.harlan.sbi.factories.dtos;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.dtos.CategoryDTO;

public class CategoryDTOFactory {
    public static CategoryDTO create(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
