package com.project.ecommerceapi.service;

import com.project.ecommerceapi.model.Address;
import com.project.ecommerceapi.model.Order;
import com.project.ecommerceapi.repository.AddressRepository;
import com.project.ecommerceapi.repository.OrderRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;

    public void saveOrder(Order order) {
        repository.save(order);
    }

    public JSONArray getOrders() {
        List<Order> orderList = repository.findAll();

        JSONArray orderArr = new JSONArray();

        for (Order order: orderList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("order_id", order.getOrderId());
            jsonObject.put("userId", order.getUser().getUserId());
            jsonObject.put("productId", order.getProduct().getProductId());
            jsonObject.put("addressId", order.getAddress().getAddressId());
            orderArr.put(jsonObject);
        }

        return orderArr;

    }
}
