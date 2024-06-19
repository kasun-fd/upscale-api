package com.devstack.ecom.upscale.service.impl;

import com.devstack.ecom.upscale.dto.request.RequestCustomerOrdersDto;
import com.devstack.ecom.upscale.dto.response.ResponseCustomerOrderDto;
import com.devstack.ecom.upscale.dto.response.paginate.CustomerOrderPaginateDto;
import com.devstack.ecom.upscale.dto.response.paginate.CustomerPaginateDto;
import com.devstack.ecom.upscale.entity.Customer;
import com.devstack.ecom.upscale.entity.CustomerOrders;
import com.devstack.ecom.upscale.entity.Product;
import com.devstack.ecom.upscale.exception.EntryNotFoundException;
import com.devstack.ecom.upscale.repo.CustomerOrderRepo;
import com.devstack.ecom.upscale.repo.CustomerRepo;
import com.devstack.ecom.upscale.repo.ProductRepo;
import com.devstack.ecom.upscale.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    @Override
    public void create(RequestCustomerOrdersDto dto) {

        Optional<Customer> selectedCustomer = customerRepo.findById(dto.getCustomer());
        Optional<Product> selectedProduct = productRepo.findById(dto.getProduct());

        if (selectedCustomer.isEmpty()) {
            throw new EntryNotFoundException("Customer id was wrong!");
        }

        if (selectedProduct.isEmpty()) {
            throw new EntryNotFoundException("Product id was wrong!");
        }

        CustomerOrders customerOrders = CustomerOrders.builder()
                .propertyId(UUID.randomUUID().toString())
                .createdDate(dto.getCreatedDate())
                .qty(dto.getQty())
                .customer(selectedCustomer.get())
                .total(dto.getTotal())
                .type(dto.getType())
                .product(selectedProduct.get())
                .build();

        orderRepo.save(customerOrders);

    }


    @Override
    public ResponseCustomerOrderDto findById(String id) {
        Optional<CustomerOrders> selectedOrder = orderRepo.findById(id);
        if (selectedOrder.isEmpty()) {
            throw new EntryNotFoundException("There is no data related to the ID you provided!");
        }

        return createCustomerOOrderDto(selectedOrder.get());
    }

    @Override
    public void update(String id, RequestCustomerOrdersDto dto) {
//        Not Necessary
    }

    @Override
    public CustomerOrderPaginateDto findAll(String customerId, int page, int size) {
        return CustomerOrderPaginateDto.builder()
                .dataList(orderRepo.findAllWithSearchText(customerId, PageRequest.of(page, size))
                        .stream().map(this::createCustomerOOrderDto).toList())
                .count(
                        customerRepo.countAllWithSearchText(customerId)
                )
                .build();
    }

    @Override
    public void delete(String id) {
        orderRepo.deleteById(id);
    }

    public ResponseCustomerOrderDto createCustomerOOrderDto(CustomerOrders customerOrders){
        return ResponseCustomerOrderDto.builder()
                .propertyId(customerOrders.getPropertyId())
                .product(customerOrders.getProduct().getDescription())
                .createdDate(customerOrders.getCreatedDate())
                .customer(customerOrders.getCustomer().getName())
                .type(customerOrders.getType())
                .qty(customerOrders.getQty())
                .total(customerOrders.getTotal())
                .build();
    }

}
