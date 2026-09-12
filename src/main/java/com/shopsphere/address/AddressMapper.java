package com.shopsphere.address;

import org.springframework.stereotype.Component;

import com.shopsphere.address.dto.CreateAddressRequest;
import com.shopsphere.address.dto.AddressResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    public Address toEntity(CreateAddressRequest request){

        Address address = new Address();

        address.setAddressLine(request.getAddressLine());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());
        address.setPhoneNumber(request.getPhoneNumber());
        address.setAddressType(request.getAddressType());

        return address;
    }

    public AddressResponse toResponse(Address address){

        AddressResponse response = new AddressResponse();

        response.setId(address.getId());
        response.setAddressLine(address.getAddressLine());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setCountry(address.getCountry());
        response.setPostalCode(address.getPostalCode());
        response.setPhoneNumber(address.getPhoneNumber());
        response.setAddressType(address.getAddressType());
        response.setDefault(address.isDefault());

        return response;
    }

    public List<AddressResponse> toResponseList(List<Address> addresses){
        return addresses.stream()
                .map(this::toResponse)
                .toList();
    }
}
