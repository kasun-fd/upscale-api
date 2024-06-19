package com.devstack.ecom.upscale.service.impl;

import com.amazonaws.services.mq.model.InternalServerErrorException;
import com.devstack.ecom.upscale.dto.request.RequestCustomerDto;
import com.devstack.ecom.upscale.dto.response.ResponseProductImageDto;
import com.devstack.ecom.upscale.entity.Product;
import com.devstack.ecom.upscale.entity.ProductImage;
import com.devstack.ecom.upscale.exception.EntryNotFoundException;
import com.devstack.ecom.upscale.repo.ProductImageRepo;
import com.devstack.ecom.upscale.repo.ProductRepo;
import com.devstack.ecom.upscale.service.FileService;
import com.devstack.ecom.upscale.service.ProductImageService;
import com.devstack.ecom.upscale.util.CommonFileSavedBinaryDataDto;
import com.devstack.ecom.upscale.util.FileDataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Value("${aws.bucket_name}")
    private String bucketName;
    private final ProductImageRepo productImageRepo;
    private final ProductRepo productRepo;
    private final FileService fileService;
    private final FileDataExtractor fileDataExtractor;
    @Override
    public void create(MultipartFile file, String productId) throws SQLException, IOException {

        CommonFileSavedBinaryDataDto resource = null;

        try {

            Optional<Product> selectedProduct = productRepo.findById(productId);

            if (selectedProduct.isEmpty()){
                throw new EntryNotFoundException("Product was not found...");
            }

            resource
                    = fileService.createResource(file, "upscale/product_images/", bucketName);

            ProductImage productImage = ProductImage.builder()
                    .propertyId(UUID.randomUUID().toString())
                    .hash(fileDataExtractor.blobToByteArray(
                            resource.getHash()
                    ))
                    .directory(resource.getDirectory().getBytes())
                    .fileName(fileDataExtractor.blobToByteArray(
                            resource.getFileName()
                    ))
                    .resourceUrl(fileDataExtractor.blobToByteArray(
                            resource.getResourceUrl()
                    ))
                    .product(selectedProduct.get())
                    .build();

            productImageRepo.save(productImage);

        }catch (Exception e){
            fileService.deleteResource(
                    bucketName,
                    resource.getDirectory(),
                    fileDataExtractor.extractActualFileName(
                            new InputStreamReader(resource.getFileName().getBinaryStream())
                    ));
            throw new InternalServerErrorException("Something went wrong!");

        }



    }

    @Override
    public ResponseProductImageDto findById(String id) {
        return null;
    }

    @Override
    public void update(String id, RequestCustomerDto dto) {

    }

    @Override
    public void delete(String id) {

    }
}
