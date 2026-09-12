package com.shopsphere.address.dto;

import com.shopsphere.address.AddressType;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {

    @NotBlank
    private String addressLine;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String phoneNumber;

    private AddressType addressType;

    private boolean isDefault;

}
