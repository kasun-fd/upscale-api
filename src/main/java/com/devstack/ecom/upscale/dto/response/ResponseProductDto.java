package com.devstack.ecom.upscale.dto.response;

import com.devstack.ecom.upscale.entity.CustomerOrders;
import com.devstack.ecom.upscale.entity.ProductImage;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductDto {
    private String propertyId;
    private Long qty;
    private Double unitPrice;
    private String description;
    List<ResponseProductImageDto> productImage;

}
