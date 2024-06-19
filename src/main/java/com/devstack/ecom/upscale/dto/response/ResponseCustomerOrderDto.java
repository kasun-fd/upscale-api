package com.devstack.ecom.upscale.dto.response;

import com.devstack.ecom.upscale.entity.Customer;
import com.devstack.ecom.upscale.entity.Product;
import com.devstack.ecom.upscale.entity.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCustomerOrderDto {

    private String propertyId;

    private Date createdDate;

    private double total;

    private int qty;

    private PaymentType type;

    private String customer;

    private String product;
}
