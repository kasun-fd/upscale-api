package com.devstack.ecom.upscale.dto.request;

import com.devstack.ecom.upscale.entity.CustomerOrders;
import com.devstack.ecom.upscale.entity.ProductImage;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestProductDto {
    private Long qty;
    private Double unitPrice;
    private String description;
}
