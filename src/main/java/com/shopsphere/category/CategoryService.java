package com.shopsphere.category;

import com.shopsphere.category.dto.CategoryResponse;
import com.shopsphere.category.dto.CreateCategoryRequest;
import com.shopsphere.category.dto.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public CategoryResponse getById(UUID categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found"));

        return categoryMapper.toResponse(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();

        return categoryMapper.toResponseList(categories);
    }

    @Transactional
    public CategoryResponse createCategory(CreateCategoryRequest createRequest){

        if(categoryRepository.findByName(createRequest.getName()).isPresent()){
            throw new RuntimeException("Category already exists");
        }

        Category category = categoryMapper.toEntity(createRequest);
        category =  categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Transactional
    public CategoryResponse updateCategory(UUID categoryId, UpdateCategoryRequest updateRequest){

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found"));

        if(updateRequest.getName()!=null && !updateRequest.getName().isBlank()){
            categoryRepository.findByName(updateRequest.getName())
                    .ifPresent(existingCategory -> {
                        if(!existingCategory.getId().equals(categoryId)){
                            throw new RuntimeException("Category already exists");
                        }
                    });
            category.setName(updateRequest.getName());
        }

        if(updateRequest.getDescription()!=null && !updateRequest.getDescription().isBlank()){
            category.setDescription(updateRequest.getDescription());
        }

        category = categoryRepository.save(category);

        return categoryMapper.toResponse(category);
    }

    @Transactional
    public void deleteCategory(UUID categoryId){

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }
}
