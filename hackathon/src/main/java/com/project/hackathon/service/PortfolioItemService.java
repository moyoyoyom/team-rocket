package com.project.hackathon.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.project.hackathon.model.PortfolioItem;
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

    public PortfolioItem getStockByTickerID(String tickerID) {
        PortfolioItem portfolioItem = new PortfolioItem();
        stockService.getStockInformation("AAPL", "Apple");
        return portfolioItem;
    }
}
