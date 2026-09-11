package com.shopsphere.address;

import java.util.UUID;

import com.shopsphere.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name="addresses")
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
public class Address {

    @Id 
    @GeneratedValue (strategy=GenerationType.UUID)
    private UUID id;

    @Column (name="addressLine", nullable=false)
    private String addressLine;

    @Column(name="city", nullable=false)
    private String city;

    @Column(name="state", nullable=false)
    private String state;

    @Column(name="country", nullable=false)
    private String country;

    @Column(name="postal_code", nullable=false)
    private String postalCode;

    @Column(name="phone_number", nullable=false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="address_type", nullable=false)
    private AddressType addressType = AddressType.HOME;

    @Column(name="is_default", nullable=false)
    private boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
