package com.project.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hackathon.model.Order;
import com.project.hackathon.service.OrderService;



@RestController
@RequestMapping("/api/teamrocket/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/history")
    public ResponseEntity<?> getUserTransactionHistory() {
        List<Order> transactionHistory = orderService.getTransactionHistory();
        return ResponseEntity.ok(transactionHistory);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/makeorder")
    public ResponseEntity<?> makeOrder(@RequestBody Order stockOrder) {
        boolean orderIsSuccessful = orderService.makeTransaction(stockOrder);
        return orderIsSuccessful ? ResponseEntity.ok("Order was successful")
                : ResponseEntity.ok("Order was unsuccessful");
    }
}
