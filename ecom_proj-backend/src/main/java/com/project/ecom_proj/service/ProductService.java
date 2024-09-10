package com.project.ecom_proj.service;

import com.project.ecom_proj.model.Product;
import com.project.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getProducts() {
        return repo.findAll();
    }
    public Product getProductById(int id){
        return repo.findById(id).get();
    }

    public Product createProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageDate(imageFile.getBytes());
        product.setImageType(imageFile.getContentType());
        return repo.save(product);
    }

    public List<Product> findProductByName(String productName) {
        return repo.findByNameContaining(productName);
    }

    public Product updateProduct(int id,Product product) {
        Product p= repo.findById(id).get();
        product.setImageName(p.getImageName());
        product.setImageDate(p.getImageDate());
        product.setImageType(p.getImageType());
        return repo.save(product);
    }
}
