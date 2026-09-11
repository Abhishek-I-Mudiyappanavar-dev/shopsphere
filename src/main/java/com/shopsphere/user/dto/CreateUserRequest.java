package com.shopsphere.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
