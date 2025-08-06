package com.project.hackathon.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.project.hackathon.model.PortfolioItem;
import com.project.hackathon.model.Stock;
import com.project.hackathon.repository.PortfolioItemRepository;

@Service
public class PortfolioItemService {
    private final PortfolioItemRepository stockRepository;
    private final StockService stockService;

    public PortfolioItemService(PortfolioItemRepository stockRepository, StockService stockService) {
        this.stockRepository = stockRepository;
        this.stockService = stockService;
    }

    public List<PortfolioItem> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockByTickerID(String tickerID) {
        Stock stock = stockService.getStockInformation(tickerID);
        return stock;
    }
}
