package com.shopsphere.address;

import org.springframework.stereotype.Service;

import com.shopsphere.address.dto.AddressResponse;
import com.shopsphere.address.dto.CreateAddressRequest;
import com.shopsphere.user.UserService;

import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    private final UserService userService;

    public AddressResponse getAddressById(UUID addressId){

        Address address = addressRepository.findById(addressId)
        .orElseThrow(()-> new RuntimeException("Address not found with id"));
        
        return addressMapper.toResponse(address);
    }

    public List<AddressResponse> getAllAddresses(){
        return addressMapper.toResponseList(addressRepository.findAll());
    }

    public AddressResponse createAddress(UUID userId, CreateAddressRequest addressRequest){

        

        Address address = addressMapper.toEntity(addressRequest);

        if(addressRequest.isDefault()){

        }

        address = addressRepository.save(address);

        return addressMapper.toResponse(address);
    }

}
