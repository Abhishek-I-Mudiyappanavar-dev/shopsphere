package com.shopsphere.address;

import java.util.UUID;
import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    List<Address> findByUserId(UUID userId);

    Optional<Address> findByIdAndUserId(UUID addressId, UUID userId);

    Optional<Address> findByUserIdAndIsDefaultTrue(UUID userId);

}
