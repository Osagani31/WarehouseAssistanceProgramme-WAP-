package com.warehouse.project.services.implementation;

import com.warehouse.project.dto.CategoryDTO;
import com.warehouse.project.dto.Response;
import com.warehouse.project.exceptions.NotFoundException;
import com.warehouse.project.models.Category;
import com.warehouse.project.repositories.CategoryRepository;
import com.warehouse.project.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;


    @Override
    public Response addCategory(CategoryDTO categoryDto) {
        Category categoryAdd = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(categoryAdd);

        return Response.builder().message("Category added successfully").build();
    }

    @Override
    public Response getAllCategory() {

        List<Category> categoryList = categoryRepository.findAll(Sort.by(Sort.Direction.DESC,
                "id"));

        categoryList.forEach(category -> category.setProducts(null));
        List<CategoryDTO> categoryDTOList=modelMapper.map(categoryList,new TypeToken<CategoryDTO>(){}.getType());

        return Response.builder().status(200).message("Category List").categories(categoryDTOList) .build();
    }

    @Override
    public Response getCategoryById(Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(() ->new NotFoundException("Category not found"));
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return Response.builder()
                .status(200)
                .message("success").category(categoryDTO).build();

    }

    @Override
    public Response updateCategory(Long id, CategoryDTO categoryDto) {

        Category existingCategory = categoryRepository.findById(id).orElseThrow(() ->new NotFoundException("Category not found"));
        existingCategory.setName(categoryDto.getName());
        categoryRepository.save(existingCategory);

        return Response.builder()
                .status(200)
                .message("successfully edited the Category").build();

    }

    @Override
    public Response deleteCategory(Long id) {

        categoryRepository.findById(id).orElseThrow(() ->new NotFoundException("Category not found"));

        categoryRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("successfully deleted the Category").build();
    }
}
