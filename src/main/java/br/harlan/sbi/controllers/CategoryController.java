package br.harlan.sbi.controllers;

import br.harlan.sbi.entities.Category;
import br.harlan.sbi.response.Response;
import br.harlan.sbi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<Category>> find(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        Response<Category> response = new Response<>();
        response.setData(category.get());
        return ResponseEntity.ok().body(response);
    }
}
