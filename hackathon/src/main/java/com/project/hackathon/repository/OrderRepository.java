package com.project.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hackathon.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}