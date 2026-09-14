package com.shopsphere.category;

import com.shopsphere.category.dto.CategoryResponse;
import com.shopsphere.category.dto.CreateCategoryRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryRequest createRequest){
        Category category = new Category();

        category.setName(createRequest.getName());
        category.setDescription(createRequest.getDescription());

        return category;
    }

    public CategoryResponse toResponse(Category category){

        CategoryResponse response = new CategoryResponse();

        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());

        return response;
    }

    public List<CategoryResponse> toResponseList(List<Category> categories){

        return categories.stream()
                 .map(this::toResponse)
                 .toList();
    }
}
