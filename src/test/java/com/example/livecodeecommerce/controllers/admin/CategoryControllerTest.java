package com.example.livecodeecommerce.controllers.admin;

import com.example.livecodeecommerce.models.Category;
import com.example.livecodeecommerce.models.requests.CategoryRequest;
import com.example.livecodeecommerce.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {

    @Test
    void createCategoryController() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Category 1");
        System.out.println(category);
    }

    @Test
    void getAllCategoryController() {
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setId(1L);
        category.setName("Category 1");
        Category category1 = new Category();
        category1.setId(2L);
        category1.setName("Category 2");
        categories.add(category);
        Mockito.when(categoryService.getAllCategoryService()).thenReturn(categories);
        System.out.println(categories);
    }

    @Test
    void getCategoryByIdController() {

    }

    @Test
    void updateCategoryController() {

    }

    @Test
    void deleteCategoryController() {

    }

    @Test
    void searchCategoryController() {

    }
}