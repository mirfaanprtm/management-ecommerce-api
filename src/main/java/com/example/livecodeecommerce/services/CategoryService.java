package com.example.livecodeecommerce.services;

import com.example.livecodeecommerce.models.Category;
import com.example.livecodeecommerce.repository.ICategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public Category createCategoryService(Category category){
        try {
            Optional<Category> category1 = categoryRepository.findById(category.getId());
            if(category1.isPresent())
                throw new RuntimeException("Category already exists...");
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Category> getAllCategoryService(){
        try {
            List<Category> categories = categoryRepository.findAll();
            if(categories.isEmpty()){
                throw new RuntimeException("No Category Found");
            }
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Optional<Category> getCategoryByIdService(Long id){
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isEmpty()){
                throw new RuntimeException("Category ID Not Found");
            }
            return category;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Category updateCategoryService(Long id, Category category){
        try {
            Optional<Category> category1 = categoryRepository.findById(id);
            if(category1.isEmpty())
                throw new RuntimeException("Category ID Not Found");
            category1.get().setName(category.getName());
            return categoryRepository.save(category1.get());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deleteCategoryService(Long id){
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isEmpty())
                throw new RuntimeException("Category ID Not Found");
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Category> findCategoryByNameService(String name, Pageable pageable){
        try {
            List<Category> categories = categoryRepository.findByNameContains(name, pageable);
            if(categories.isEmpty())
                throw new RuntimeException("Category Name Not Found");
            return categories;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
