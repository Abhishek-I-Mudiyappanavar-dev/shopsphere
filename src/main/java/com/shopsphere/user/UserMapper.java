package com.shopsphere.user;

import com.shopsphere.user.dto.CreateUserRequest;
import com.shopsphere.user.dto.UserResponse;
import com.shopsphere.role.Role;
import org.springframework.stereotype.Component;
import lombok.*;

import java.util.stream.Collectors;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toEntity(CreateUserRequest request){

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        return user;

    }

    public UserResponse toResponse(User user){

        UserResponse response = new UserResponse();

        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setEnabled(user.isEnabled());
        response.setRoles(
            user.getRoles()
            .stream()
            .map(Role::getName)
            .collect(Collectors.toSet())
        );

        return response;

    }

    public List<UserResponse> toResponseList(List<User> users){

        return users.stream()
                .map(this::toResponse)
                .toList();
                
    }
}
