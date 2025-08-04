package com.project.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hackathon.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

}