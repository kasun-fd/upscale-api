package com.devstack.ecom.upscale.api;

import com.devstack.ecom.upscale.dto.request.RequestCustomerDto;
import com.devstack.ecom.upscale.dto.request.RequestProductDto;
import com.devstack.ecom.upscale.service.ProductService;
import com.devstack.ecom.upscale.service.impl.ProductServiceImpl;
import com.devstack.ecom.upscale.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<StandardResponse> create(@RequestBody RequestProductDto dto) {
        productService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Product was created!..",null),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> get(@PathVariable String id) {
        return new ResponseEntity<>(
                new StandardResponse(200,"Product data!..",productService.findById(id)),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> update(@PathVariable String id,
                         @RequestBody RequestProductDto dto) {
        productService.update(id,dto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Product was updated!..",null),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id) {
       productService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Product was deleted!..",null),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> getAll(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ) {

        return new ResponseEntity<>(
                new StandardResponse(200,"Product list!..",
                        productService.findAll(searchText, page, size)),
                HttpStatus.OK
        );
    }
}
