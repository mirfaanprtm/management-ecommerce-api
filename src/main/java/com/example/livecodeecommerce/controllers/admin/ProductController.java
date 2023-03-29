package com.example.livecodeecommerce.controllers.admin;

import com.example.livecodeecommerce.models.Category;
import com.example.livecodeecommerce.models.Product;
import com.example.livecodeecommerce.models.requests.CategoryRequest;
import com.example.livecodeecommerce.models.requests.ProductRequest;
import com.example.livecodeecommerce.models.requests.SearchData;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.ProductService;
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
@RequestMapping("/admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity createProductController(@RequestBody ProductRequest productRequest){
        Product newProduct = modelMapper.map(productRequest, Product.class);
        Product product = productService.createProductService(newProduct);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Product>("Product Created...", product));
    }
    @GetMapping
    public ResponseEntity getAllProductController(){
        List<Product> products = productService.getAllProductService();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<Product>>("Success Get All Product...", products));
    }
    @GetMapping("/{id}")
    public ResponseEntity getProductByIdController(@PathVariable("id") Long id){
        Optional<Product> product = productService.getProductByIdService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<Product>>("Success Get Product By ID...", product));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateProductController(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest){
        Product newProduct = modelMapper.map(productRequest, Product.class);
        Product product = productService.updateProductService(id, newProduct);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Product>("Success Update Product...", product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductController(@PathVariable("id") Long id){
        productService.deleteProductService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success Delete Product...", "Data Null"));
    }
    @PostMapping("/search/{page}/{size}/{sort}")
    public List<Product> searchProductByNameController(@RequestBody SearchData searchData, @PathVariable Integer size, @PathVariable Integer page, @PathVariable("sort") String sort){
        Pageable pageable;
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, size, Sort.by("id").descending());
        } else {
             pageable = PageRequest.of(page-1, size, Sort.by("id").ascending());
        }
        return productService.getProductByNameService(searchData.getSearchKey(), pageable);
    }
}
