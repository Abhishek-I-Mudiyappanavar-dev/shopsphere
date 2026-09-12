package com.shopsphere.address.dto;

import com.shopsphere.address.AddressType;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {

    private String addressLine;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String phoneNumber;

    private AddressType addressType;

    private Boolean isDefault;
}
