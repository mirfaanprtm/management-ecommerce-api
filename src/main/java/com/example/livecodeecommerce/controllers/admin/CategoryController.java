package com.example.livecodeecommerce.controllers.admin;

import com.example.livecodeecommerce.models.Category;
import com.example.livecodeecommerce.models.requests.CategoryRequest;
import com.example.livecodeecommerce.models.requests.SearchData;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity createCategoryController(@RequestBody CategoryRequest categoryRequest){
        Category newCategory = modelMapper.map(categoryRequest, Category.class);
        Category category = categoryService.createCategoryService(newCategory);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Category>("Category Created...", category));
    }
    @GetMapping
    public ResponseEntity getAllCategoryController(){
        List<Category> categories = categoryService.getAllCategoryService();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<Category>>("Success Get All Category...", categories));
    }
    @GetMapping("/{id}")
    public ResponseEntity getCategoryByIdController(@PathVariable("id") Long id){
        Optional<Category> category = categoryService.getCategoryByIdService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<Category>>("Success Get Category By ID...", category));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateCategoryController(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest){
        Category newCategory = modelMapper.map(categoryRequest, Category.class);
        Category category = categoryService.updateCategoryService(id, newCategory);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Category>("Success Update Category...", category));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoryController(@PathVariable("id") Long id){
        categoryService.deleteCategoryService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success Delete Category...", "Data Null"));
    }
    @PostMapping("/search/{page}/{size}/{sort}")
    public List<Category> searchCategoryController(@RequestBody SearchData searchData, @PathVariable Integer size, @PathVariable Integer page, @PathVariable("sort") String sort){
        Pageable pageable;
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, size, Sort.by("id").descending());
        } else {
            pageable = PageRequest.of(page-1, size, Sort.by("id").ascending());
        }
        return categoryService.findCategoryByNameService(searchData.getSearchKey(), pageable);
    }
}
