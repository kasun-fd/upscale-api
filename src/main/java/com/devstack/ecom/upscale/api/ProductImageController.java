package com.devstack.ecom.upscale.api;

import com.devstack.ecom.upscale.service.ProductImageService;
import com.devstack.ecom.upscale.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/product_image")
@RequiredArgsConstructor
@CrossOrigin
public class ProductImageController {

    private final ProductImageService imageService;

    @PostMapping("/{product}")
    public ResponseEntity<StandardResponse> create(@RequestParam("productImage") MultipartFile file,
                                                   @PathVariable String id) throws SQLException, IOException {
        imageService.create(file,id);
        return new ResponseEntity<>(
                new StandardResponse(201,"Product Image was Saved!",null),
                HttpStatus.CREATED
        );
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<StandardResponse> findById(@PathVariable String id){
//        return new ResponseEntity<>(
//                new StandardResponse(200,"Order Data.",orderService.findById(id)),
//                HttpStatus.OK
//        );
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<StandardResponse> update(@PathVariable String id) {
//        orderService.update(id,dto);
//        return new ResponseEntity<>(
//                new StandardResponse(201,"Product was updated!..",null),
//                HttpStatus.CREATED
//        );
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<StandardResponse> delete(@PathVariable String id) {
//        orderService.delete(id);
//        return new ResponseEntity<>(
//                new StandardResponse(204,"Order was deleted!..",null),
//                HttpStatus.NO_CONTENT
//        );
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<StandardResponse> getAll(
//            @RequestParam String searchText,
//            @RequestParam int page,
//            @RequestParam int size
//    ) {
//
//        return new ResponseEntity<>(
//                new StandardResponse(200,"Orders list!..",
//                        orderService.findAll(searchText, page, size)),
//                HttpStatus.OK
//        );
//    }

}
