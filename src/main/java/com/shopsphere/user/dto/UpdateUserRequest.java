package com.shopsphere.user.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private String firstName;

    private String lastName;

    private String email;

}
