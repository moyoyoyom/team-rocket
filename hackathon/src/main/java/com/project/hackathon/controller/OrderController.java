package com.project.hackathon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hackathon.model.Order;

@RestController
@RequestMapping("/api/teamrocket/order")
public class OrderController {

    @GetMapping("/history")
    public ResponseEntity<?> getUserTransactionHistory() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/makeorder")
    public ResponseEntity<?> makeOrder(@RequestBody Order stockOrder) {

        return ResponseEntity.ok(null);
    }
}
