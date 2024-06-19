package com.devstack.ecom.upscale.api;

import com.devstack.ecom.upscale.dto.request.RequestCustomerOrdersDto;
import com.devstack.ecom.upscale.dto.request.RequestProductDto;
import com.devstack.ecom.upscale.dto.response.ResponseCustomerOrderDto;
import com.devstack.ecom.upscale.service.impl.CustomerOrderServiceImpl;
import com.devstack.ecom.upscale.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer_orders")
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerOrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<StandardResponse> create(@RequestBody RequestCustomerOrdersDto dto){
        orderService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Order was placed!",null),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findById(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponse(200,"Order Data.",orderService.findById(id)),
                HttpStatus.OK
        );
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<StandardResponse> update(@PathVariable String id) {
//        orderService.update(id,dto);
//        return new ResponseEntity<>(
//                new StandardResponse(201,"Product was updated!..",null),
//                HttpStatus.CREATED
//        );
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id) {
        orderService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Order was deleted!..",null),
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
                new StandardResponse(200,"Orders list!..",
                        orderService.findAll(searchText, page, size)),
                HttpStatus.OK
        );
    }

}
