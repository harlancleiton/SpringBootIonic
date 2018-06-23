package br.harlan.sbi.resources;

import br.harlan.sbi.domain.Product;
import br.harlan.sbi.dto.ProductDTO;
import br.harlan.sbi.factory.dtos.ProductDTOFactory;
import br.harlan.sbi.response.Response;
import br.harlan.sbi.services.ProductService;
import br.harlan.sbi.utils.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/products")
public class ProductResource {
    @Autowired
    private ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<Product>> findById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        Response<Product> response = new Response<>();
        response.setData(product.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Response<Page<ProductDTO>>> findPage(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "categories", defaultValue = "") String categories,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "name") String orderBy
    ) {
        Response<Page<ProductDTO>> response = new Response<>();
        Page<Product> products = productService.findPage(UrlUtil.decodeString(name), UrlUtil.decodeLongId(categories), page, linesPerPage, direction, orderBy);
        Page<ProductDTO> productDTOs = products.map(ProductDTOFactory::create);
        response.setData(productDTOs);
        return ResponseEntity.ok().body(response);
    }
}
