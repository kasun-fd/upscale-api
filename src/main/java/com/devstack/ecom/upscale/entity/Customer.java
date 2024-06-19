package com.devstack.ecom.upscale.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customer")
public class Customer {
    @Id
    @Column(name = "property_id", nullable = false, length = 80)
    private String propertyId;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "is_active", columnDefinition = "TINYINT")
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<CustomerOrders> orders = new HashSet<>();

}
