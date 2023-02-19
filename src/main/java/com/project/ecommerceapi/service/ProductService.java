package com.project.ecommerceapi.service;

import com.project.ecommerceapi.model.Product;
import com.project.ecommerceapi.model.User;
import com.project.ecommerceapi.repository.ProductRepository;
import com.project.ecommerceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getProduct(String productId) {

        List<Product> productList;

        if(null != productId) {
            productList = new ArrayList<>();
            productList.add(repository.findById(Integer.valueOf(productId)).get());
        } else {
            productList = repository.findAll();
        }
        return productList;
    }

    public Product getProductById(String productId) {

        return repository.findById(Integer.valueOf(productId)).get();
    }

    public void deleteProduct(int productId){
        repository.deleteById(productId);
    }
}
