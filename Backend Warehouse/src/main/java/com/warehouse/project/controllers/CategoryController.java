package com.warehouse.project.controllers;

import com.warehouse.project.dto.CategoryDTO;
import com.warehouse.project.dto.Response;
import com.warehouse.project.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor

public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<Response> addCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<Response> updateCategory(@PathVariable Long id , @RequestBody @Valid  CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<Response> deleteCategory(@PathVariable Long id ) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

}
