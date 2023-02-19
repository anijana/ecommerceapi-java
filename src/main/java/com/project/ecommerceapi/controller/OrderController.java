package com.project.ecommerceapi.controller;

import com.project.ecommerceapi.model.Address;
import com.project.ecommerceapi.model.Order;
import com.project.ecommerceapi.model.Product;
import com.project.ecommerceapi.model.User;
import com.project.ecommerceapi.repository.AddressRepository;
import com.project.ecommerceapi.repository.ProductRepository;
import com.project.ecommerceapi.repository.UserRepository;
import com.project.ecommerceapi.service.OrderService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderService service;

    @PostMapping(value = "/add-order")
    public String saveOrder(@RequestBody String orderRequest) {

        JSONObject json = new JSONObject(orderRequest);
        Order order = setOrder(json);
        service.saveOrder(order);

        return "Saved Order";

    }

    private Order setOrder(JSONObject json) {

        Order order = new Order();

        order.setOrderId(json.getInt("orderId"));

        String userId = json.getString("userId");
        User user = userRepository.findById(Integer.valueOf(userId)).get();
        order.setUser(user);

        String productId = json.getString("productId");
        Product product = productRepository.findById(Integer.valueOf(productId)).get();
        order.setProduct(product);

        String addressId = json.getString("addressId");
        Address address = addressRepository.findById(Integer.valueOf(addressId)).get();
        order.setAddress(address);

        order.setProductQuantity(json.getInt("productQuantity"));

        return order;


    }


    @GetMapping(value = "/order")
    public ResponseEntity getPatients(@Nullable @RequestParam String userId,
                                      @Nullable @RequestParam String productId,
                                      @Nullable @RequestParam String addressId,
                                      @Nullable @RequestParam String orderId) {

        JSONArray patientDetails = service.getOrders();

        return new ResponseEntity<>(patientDetails.toString(), HttpStatus.OK);


    }
}
