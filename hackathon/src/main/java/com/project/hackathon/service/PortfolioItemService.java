package com.project.hackathon.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.hackathon.model.PortfolioItem;
import com.project.hackathon.repository.PortfolioItemRepository;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class PortfolioItemService {
    private final PortfolioItemRepository stockRepository;

    public PortfolioItemService(PortfolioItemRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<PortfolioItem> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockByTickerID(String tickerID) {
        Optional<Stock> foundStock = Optional.empty();
        try {
            foundStock = Optional.of(YahooFinance.get(tickerID));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return foundStock.get();
    }
}
