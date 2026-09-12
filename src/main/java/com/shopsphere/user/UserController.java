package com.shopsphere.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import com.shopsphere.user.dto.UserResponse;
import com.shopsphere.user.dto.UpdateUserRequest;
import com.shopsphere.user.dto.CreateUserRequest;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable UUID userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/email")
    public UserResponse getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @PatchMapping("/{userId}")
    public UserResponse updateUserById(@PathVariable UUID userId, @Valid @RequestBody UpdateUserRequest userRequest){
        return userService.updateUser(userId, userRequest);
    }

    @PatchMapping("/{userId}/disable")
    public void disableUser(@PathVariable UUID userId){
        userService.disableUser(userId);
    }
}
