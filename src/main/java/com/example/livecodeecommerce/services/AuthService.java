package com.example.livecodeecommerce.services;

import com.example.livecodeecommerce.models.Auth;
import com.example.livecodeecommerce.models.User;
import com.example.livecodeecommerce.models.requests.LoginRequest;
import com.example.livecodeecommerce.models.requests.RegisterRequest;
import com.example.livecodeecommerce.repository.IAuthRepository;
import com.example.livecodeecommerce.repository.IUserRepository;
import com.example.livecodeecommerce.utils.JwtUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AuthService implements IAuthService{
    @Autowired
    private IAuthRepository authRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public String register(RegisterRequest registerRequest) {
        try{
            Auth auth = modelMapper.map(registerRequest, Auth.class);
            Auth authResult = authRepository.save(auth);

            User user = modelMapper.map(registerRequest, User.class);
            user.setAuth(authResult);
            userRepository.save(user);

            String token = jwtUtil.generateToken(user.getAuth().getEmail());
            return token;
        }catch (DataIntegrityViolationException e){
            throw new EntityExistsException();
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        try{
            Optional<Auth> auth = authRepository.findById(loginRequest.getEmail());
            if(auth.isEmpty())
                throw new RuntimeException("Email not registered...");
            if(!auth.get().getPassword().equals(loginRequest.getPassword()))
                throw new Exception("Incorrect password...");
            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return token;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
