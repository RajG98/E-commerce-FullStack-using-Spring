package com.project.ecom_proj.controller;

import com.project.ecom_proj.model.Product;
import com.project.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getProducts(),HttpStatus.OK);
    }
    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> fetchImage(@PathVariable int id){
        Product p= service.getProductById(id);
        return ResponseEntity.ok().contentType(MediaType.valueOf(p.getImageType())).body(p.getImageDate());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        return new ResponseEntity<>(service.getProductById(id),HttpStatus.OK);
    }
    @GetMapping("/products/search")
    public List<Product> findProductByName(@RequestParam(name = "name") String productName){
        return service.findProductByName(productName);
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            Product product1 = service.createProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart Product product){
//        System.out.println(product);
        try {
            Product product1 = service.updateProduct(id,product);
            return new ResponseEntity<>(product1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
