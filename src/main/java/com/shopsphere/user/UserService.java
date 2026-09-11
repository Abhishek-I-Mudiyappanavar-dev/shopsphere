package com.shopsphere.user;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopsphere.user.dto.CreateUserRequest;
import com.shopsphere.user.dto.UserResponse;
import com.shopsphere.user.dto.UpdateUserRequest;
import com.shopsphere.role.RoleRepository;
import com.shopsphere.role.Role;
import com.shopsphere.role.RoleName;

import java.util.UUID;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;


    public UserResponse createUser(CreateUserRequest userRequest){

        String email = userRequest.getEmail().trim().toLowerCase();

        if(userRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("email already exists");
        }
        userRequest.setEmail(email);
        User user = userMapper.toEntity(userRequest);

        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        
        Role customerRole = roleRepository.findByName(RoleName.CUSTOMER)
        .orElseThrow(()-> new RuntimeException("CUSTOMER role not found"));


        user.getRoles().add(customerRole);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    public List<UserResponse> getAllUser(){
        List<User> users = userRepository.findAll();

        List<UserResponse> responses = userMapper.toResponseList(users);

        return responses;
    }

    public UserResponse getUserById(UUID userId){
        User user = userRepository.findById(userId)
        .orElseThrow(()-> new RuntimeException("User not found"));

        UserResponse response = userMapper.toResponse(user);

        return response;
    }

    public UserResponse getUserByEmail(String email){
        email = email.trim().toLowerCase();
        User user = userRepository.findByEmail(email)
        .orElseThrow(()-> new RuntimeException("User not found with email Id"));

        UserResponse response = userMapper.toResponse(user);
        return response;
    }


    public UserResponse updateUser(UUID userId, UpdateUserRequest userRequest){

        User existedUser = userRepository.findById(userId)
        .orElseThrow(()-> new RuntimeException("User not found"));

        if(userRequest.getEmail()!=null && !Objects.equals(userRequest.getEmail(), existedUser.getEmail())){
            String email = userRequest.getEmail().trim().toLowerCase();
            userRepository.findByEmail(email).ifPresent(u -> {
                if(!existedUser.getId().equals(u.getId())){
                    throw new RuntimeException("User already exists with email Id");
                }
            });
            existedUser.setEmail(email);
        }

        if(userRequest.getFirstName()!=null) existedUser.setFirstName(userRequest.getFirstName());
        if(userRequest.getLastName()!=null) existedUser.setLastName(userRequest.getLastName());


        UserResponse response = userMapper.toResponse(userRepository.save(existedUser));
        return response;
    }

    public void deleteUser(UUID userId){
        User user = userRepository.findById(userId).
        orElseThrow(()-> new RuntimeException("User not found"));

        user.setEnabled(false);
        userRepository.save(user);
    }

}
