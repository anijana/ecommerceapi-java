package com.project.ecommerceapi.controller;

import com.project.ecommerceapi.model.Product;
import com.project.ecommerceapi.model.User;
import com.project.ecommerceapi.service.ProductService;
import com.project.ecommerceapi.service.UserService;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping(value = "/add-product")
    public ResponseEntity<String> saveProduct(@RequestBody String requestProduct) {

        JSONObject json = new JSONObject(requestProduct);

        List<String> validationList = validateProduct(json);

        if (validationList.isEmpty()) {
            Product product = setProduct(json);
            service.saveProduct(product);
            return new ResponseEntity<>("product saved", HttpStatus.CREATED);
        } else {

            String[] answer = Arrays.copyOf(
                    validationList.toArray(), validationList.size(), String[].class);

            return new ResponseEntity<>("Please pass these mandatory parameters- " +
                    Arrays.toString(answer), HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(value = "/all-product")
    public List<Product> getProduct(@Nullable @RequestParam String productId) {

        return service.getProduct(productId);
    }


    private List<String> validateProduct(JSONObject json) {

        List<String> errorList = new ArrayList<>();

        if (!json.has("productId")) {
            errorList.add("productId");
        }

        if (!json.has("productName")) {
            errorList.add("productName");
        }


        if (!json.has("price")) {
            errorList.add("price");
        }
        if (!json.has("category")) {
            errorList.add("category");
        }
        return errorList;

    }


    public Product setProduct(JSONObject json) {
        Product product = new Product();

        String productId = json.getString("productId");
        product.setProductId(Integer.valueOf(productId));

        String productName = json.getString("productName");
        product.setProductName(productName);

        String price = json.getString("price");
        product.setPrice(Integer.valueOf(price));

        String category = json.getString("category");
        product.setCategory(category);

        if (json.has("description")) {
            String desc = json.getString("description");
            product.setDescription(desc);
        }
        if (json.has("brand")) {
            String brand = json.getString("brand");
            product.setBrand(brand);
        }

        return product;
    }

    @DeleteMapping("/delete-user/id/{id}")
    public void deleteProduct(@PathVariable int productId){
        service.deleteProduct(productId);
    }
}
