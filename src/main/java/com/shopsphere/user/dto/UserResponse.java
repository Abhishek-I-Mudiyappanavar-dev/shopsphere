package com.shopsphere.user.dto;

import lombok.*;

import com.shopsphere.role.RoleName;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String firstName;

    private String lastName;

    private String email;

    private Set<RoleName> roles;

    private boolean enabled;

}
