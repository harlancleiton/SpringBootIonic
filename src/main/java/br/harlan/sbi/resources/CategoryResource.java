package br.harlan.sbi.resources;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.response.Response;
import br.harlan.sbi.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryResource {
    @Autowired
    CategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryResource.class);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<Category>> find(@PathVariable Long id) {
        LOGGER.info("Finding Category by Id: {}", id);
        Optional<Category> category = categoryService.findById(id);
        Response<Category> response = new Response<>();
        response.setData(category.get());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Category>> insert(@RequestBody Category category) {
        LOGGER.info("Inserting Category: {}", category);
        Response<Category> response = new Response<>();
        category = categoryService.insert(category);
        response.setData(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Category category, @PathVariable Long id) {
        LOGGER.info("Updating Category: {}", category);
        category.setId(id);
        categoryService.update(category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("Deleting Category by Id: {}", id);
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
