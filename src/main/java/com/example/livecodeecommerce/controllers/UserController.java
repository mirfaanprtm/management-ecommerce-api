package com.example.livecodeecommerce.controllers;

import com.example.livecodeecommerce.models.Price;
import com.example.livecodeecommerce.models.User;
import com.example.livecodeecommerce.models.requests.PriceRequest;
import com.example.livecodeecommerce.models.requests.UserRequest;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity createUserController(@RequestBody UserRequest userRequest){
        User newUser = modelMapper.map(userRequest, User.class);
        User user = userService.createUserService(newUser);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<User>("User Created...", user));
    }
    @GetMapping("/search/{page}/{size}/{sort}")
    public Page<User> getAllUserController(@PathVariable Integer size, @PathVariable Integer page, @PathVariable("sort") String sort){
        Pageable pageable;
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, size, Sort.by("id").descending());
        } else {
            pageable = PageRequest.of(page-1, size, Sort.by("id").ascending());
        }
        return userService.getAllUserService(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity getUserByIdController(@PathVariable("id") Long id){
        Optional<User> user = userService.getUserByIdService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<User>>("Success Get User By ID...", user));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateUserController(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest){
        User newUser = modelMapper.map(userRequest, User.class);
        User user = userService.updateUserService(id, newUser);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<User>("Success Update User...", user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserController(@PathVariable("id") Long id){
        userService.deleteUserService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success Delete User...", "Data Null"));
    }
}
