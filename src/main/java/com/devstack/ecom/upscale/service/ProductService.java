package com.devstack.ecom.upscale.service;

import com.devstack.ecom.upscale.dto.request.RequestCustomerDto;
import com.devstack.ecom.upscale.dto.request.RequestProductDto;
import com.devstack.ecom.upscale.dto.response.ResponseCustomerDto;
import com.devstack.ecom.upscale.dto.response.ResponseProductDto;
import com.devstack.ecom.upscale.dto.response.paginate.CustomerPaginateDto;
import com.devstack.ecom.upscale.dto.response.paginate.ProductPaginateDto;

public interface ProductService {
    public void create(RequestProductDto dto);
    public ResponseProductDto findById(String id);
    public void update(String id,RequestProductDto dto);
    public ProductPaginateDto findAll(String searchText, int page, int size);
    public void delete(String id);
}
