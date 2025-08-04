package com.project.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hackathon.model.PortfolioItem;
import com.project.hackathon.service.PortfolioItemService;

import yahoofinance.Stock;

@RestController
@RequestMapping("/api/teamrocket/stocks")
public class PortfolioItemController {

    private final PortfolioItemService stockService;

    @Autowired
    public PortfolioItemController(PortfolioItemService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<PortfolioItem>> getUserAllStocks() {
        List<PortfolioItem> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/getstockinfo/{tickerID}")
    public ResponseEntity<?> getStockInfo(@PathVariable("tickerID") String tickerID) {
        Stock stock = stockService.getStockByTickerID(tickerID);
        return ResponseEntity.ok(stock);
    }
}
