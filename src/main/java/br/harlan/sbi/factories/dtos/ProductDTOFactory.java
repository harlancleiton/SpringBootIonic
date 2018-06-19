package br.harlan.sbi.factories.dtos;

import br.harlan.sbi.domain.Product;
import br.harlan.sbi.dtos.ProductDTO;
import org.jetbrains.annotations.NotNull;

public class ProductDTOFactory {
    @NotNull
    public static ProductDTO create(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}
