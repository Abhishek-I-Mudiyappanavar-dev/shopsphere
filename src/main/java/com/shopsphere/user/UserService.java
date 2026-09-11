package com.shopsphere.user;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

import com.shopsphere.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    // private User createUser(String info){
    //     userRepository.save(User(info));
    // }

    // private User getUserById(UUID userId){
    //     userRepository.findById(userId);
    // }

    // private User updateUserInfo(String userInfo){

    // }

    // private User deleteUserById(UUID userId){
    //     userRepository.deleteById(userId);
    // }
}
