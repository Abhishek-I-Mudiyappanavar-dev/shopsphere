package com.shopsphere.user.dto;

import lombok.*;

import com.shopsphere.role.RoleName;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private UUID Id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<RoleName> roles;

    private boolean enabled;

}
