package com.devstack.ecom.upscale.service;

import com.devstack.ecom.upscale.dto.request.RequestCustomerDto;
import com.devstack.ecom.upscale.dto.response.ResponseCustomerDto;
import com.devstack.ecom.upscale.dto.response.paginate.CustomerPaginateDto;

public interface CustomerService {
    public void create(RequestCustomerDto dto);
    public ResponseCustomerDto findById(String id);
    public void update(String id,RequestCustomerDto dto);
    public CustomerPaginateDto findAll(String searchText, int page, int size);
    public void delete(String id);
}
