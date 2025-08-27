package com.warehouse.project.services;

import com.warehouse.project.dto.CategoryDTO;
import com.warehouse.project.dto.Response;

public interface CategoryService {

    Response addCategory(CategoryDTO categoryDto);
    Response getAllCategory();
    Response getCategoryById(Long id);
    Response updateCategory(Long id,CategoryDTO categoryDto);
    Response deleteCategory(Long id);
}
