package com.shopsphere.address;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopsphere.address.dto.AddressResponse;
import com.shopsphere.address.dto.CreateAddressRequest;
import com.shopsphere.address.dto.UpdateAddressRequest;
import com.shopsphere.user.User;
import com.shopsphere.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public AddressResponse getAddressById(UUID addressId, UUID userId){

        Address address = addressRepository
            .findByIdAndUserId(userId, addressId)
            .orElseThrow(()-> new RuntimeException("Address not found with id"));
        
        return addressMapper.toResponse(address);
    }

    @Transactional(readOnly = true)
    public List<AddressResponse> getAddressesByUserId(UUID userId){

        userRepository.findById(userId)
            .orElseThrow(()-> new RuntimeException("User not found"));

        List<Address> addresses = addressRepository.findByUserId(userId);
        
        return addressMapper.toResponseList(addresses);
    }

    @Transactional
    public AddressResponse createAddress(UUID userId, CreateAddressRequest createRequest){

        User user = userRepository.findById(userId)
            .orElseThrow(()-> new RuntimeException("User not found"));

        Address address = addressMapper.toEntity(createRequest);

        address.setUser(user);

        if(createRequest.getAddressType()==null){
            address.setAddressType(AddressType.HOME);
        }

        if(createRequest.isDefault()){
            makeDefaultAddress(address, userId);
        }

        address = addressRepository.save(address);

        return addressMapper.toResponse(address);
    }

    @Transactional
    public AddressResponse updateAddress(UUID userId, UUID addressId, UpdateAddressRequest updateRequest){

        Address address = addressRepository.findByIdAndUserId(addressId, userId)
        .orElseThrow(()-> new RuntimeException("Address not found"));

        if(updateRequest.getAddressLine()!=null && !updateRequest.getAddressLine().isBlank()){
            address.setAddressLine(updateRequest.getAddressLine());
        }

        if(updateRequest.getCity()!=null && !updateRequest.getCity().isBlank()){
            address.setCity(updateRequest.getCity());
        }

        if(updateRequest.getState()!=null && !updateRequest.getState().isBlank()){
            address.setState(updateRequest.getState());
        }

        if(updateRequest.getCountry()!=null && !updateRequest.getCountry().isBlank()){
            address.setCountry(updateRequest.getCountry());
        }

        if(updateRequest.getPostalCode()!=null && !updateRequest.getPostalCode().isBlank()){
            address.setPostalCode(updateRequest.getPostalCode());
        }

        if(updateRequest.getPhoneNumber()!=null && !updateRequest.getPhoneNumber().isBlank()){
            address.setPhoneNumber(updateRequest.getPhoneNumber());
        }

        if(updateRequest.getAddressType()!=null){
            address.setAddressType(updateRequest.getAddressType());
        }

 if(updateRequest.getIsDefault()!=null){
        if(updateRequest.getIsDefault()){
            makeDefaultAddress(address, userId);
        }
        else{
            address.setDefault(false);
        }
    }       

        address = addressRepository.save(address);

        return addressMapper.toResponse(address);
    }

    @Transactional
    public void deleteAddress(UUID userId, UUID addressId){

        Address address = addressRepository
            .findByIdAndUserId(addressId, userId)
            .orElseThrow(()-> new RuntimeException("Address not found"));

        addressRepository.delete(address);

    }

    private void makeDefaultAddress(Address address, UUID userId){
        addressRepository.findByUserIdAndIsDefaultTrue(userId)
                .filter(existingDefault ->
                    !existingDefault.getId().equals(address.getId()))
                .ifPresent(existingDefault -> {
                    existingDefault.setDefault(false);
                    addressRepository.flush();
                });

            address.setDefault(true);
    }

}
