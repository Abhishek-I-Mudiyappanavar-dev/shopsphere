package com.shopsphere.address;

import com.shopsphere.address.dto.AddressResponse;
import com.shopsphere.address.dto.CreateAddressRequest;
import com.shopsphere.address.dto.UpdateAddressRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/{userId}/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<AddressResponse> getAddressesByUserId(@PathVariable UUID userId){

        return addressService.getAddressesByUserId(userId);
    }

    @GetMapping("/{addressId}")
    public AddressResponse getAddressById(@PathVariable UUID userId, @PathVariable UUID addressId){

        return addressService.getAddressById(userId, addressId);
    }

    @PostMapping
    public AddressResponse createAddress(@PathVariable UUID userId, @Valid @RequestBody CreateAddressRequest createRequest){

        return addressService.createAddress(userId, createRequest);
    }

    @PatchMapping("/{addressId}")
    public AddressResponse updateAddress(@PathVariable UUID userId,@PathVariable UUID addressId, @Valid @RequestBody UpdateAddressRequest updateRequest){

        return addressService.updateAddress(userId, addressId, updateRequest);
    }

    @DeleteMapping("/{addressId}")
    public void deleteAddress(@PathVariable UUID userId, @PathVariable UUID addressId){

        addressService.deleteAddress(userId, addressId);
    }
}
