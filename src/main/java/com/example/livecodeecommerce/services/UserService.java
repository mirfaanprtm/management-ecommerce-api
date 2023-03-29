package com.example.livecodeecommerce.services;

import com.example.livecodeecommerce.exceptions.NotFoundException;
import com.example.livecodeecommerce.models.Price;
import com.example.livecodeecommerce.models.User;
import com.example.livecodeecommerce.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    public User createUserService(User user){
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Page<User> getAllUserService(Pageable pageable){
        try {
            Page<User> users = userRepository.findAll(pageable);
            if(users.isEmpty()){
                throw new NotFoundException("No User Found");
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Optional<User> getUserByIdService(Long id){
        try {
            Optional<User> user = userRepository.findById(id);
            if(user.isEmpty()){
                throw new RuntimeException("User ID Not Found");
            }
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public User updateUserService(Long id, User user){
        try {
            Optional<User> user1 = userRepository.findById(id);
            if(user1.isEmpty())
                throw new RuntimeException("User ID Not Found");
            user1.get().setName(user.getName());
            user1.get().setPhone(user.getPhone());
            user1.get().setAuth(user.getAuth());
            return userRepository.save(user1.get());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deleteUserService(Long id){
        try {
            Optional<User> user = userRepository.findById(id);
            if(user.isEmpty())
                throw new RuntimeException("User ID Not Found");
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
