package com.project.hackathon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.hackathon.model.Stock;
import com.project.hackathon.repository.StockRepository;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
}
