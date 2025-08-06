package com.project.hackathon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.hackathon.model.Order;
import com.project.hackathon.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getTransactionHistory() {
        List<Order> orderHistory = orderRepository.findAll();
        return orderHistory;
    }
}
