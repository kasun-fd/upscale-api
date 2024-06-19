package com.devstack.ecom.upscale.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "product")
public class Product {
    @Id
    @Column(name = "property_id")
    private String propertyId;
    private Long qty;
    private Double unitPrice;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY)
    private List<CustomerOrders> orders = new ArrayList<>();

}
