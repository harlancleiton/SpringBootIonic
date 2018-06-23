package br.harlan.sbi.resources;

import br.harlan.sbi.domain.Category;
import br.harlan.sbi.dto.CategoryDTO;
import br.harlan.sbi.factory.domain.CategoryFactory;
import br.harlan.sbi.factory.dtos.CategoryDTOFactory;
import br.harlan.sbi.response.Response;
import br.harlan.sbi.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
//@CrossOrigin(origins = "*")
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

    @GetMapping
    public ResponseEntity<Response<List<CategoryDTO>>> findAll() {
        LOGGER.info("Finding all Categories.");
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        categories.forEach(category -> categoryDTOs.add(CategoryDTOFactory.create(category)));
        Response<List<CategoryDTO>> response = new Response<>();
        response.setData(categoryDTOs);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Response<Page<CategoryDTO>>> findPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "name") String orderBy
    ) {
        LOGGER.info("Finding Categories by Page: {}, LinesPerPage: {}, OrderBy: {}, Direction: {}", page, linesPerPage, orderBy, direction);
        Response<Page<CategoryDTO>> response = new Response<Page<CategoryDTO>>();
        Page<Category> categoryPage = categoryService.findPage(page, linesPerPage, direction, orderBy);
        Page<CategoryDTO> categoryDTOPage = categoryPage.map(CategoryDTOFactory::create);
        response.setData(categoryDTOPage);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Response<CategoryDTO>> insert(@Valid @RequestBody CategoryDTO categoryDTO) {
        LOGGER.info("Inserting Category: {}", categoryDTO);
        Response<CategoryDTO> response = new Response<>();
        Category category = CategoryFactory.create(categoryDTO);
        category = categoryService.insert(category);
        response.setData(CategoryDTOFactory.create(category));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        LOGGER.info("Updating Category: {}", categoryDTO);
        categoryDTO.setId(id);
        Category category = CategoryFactory.create(categoryDTO);
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
