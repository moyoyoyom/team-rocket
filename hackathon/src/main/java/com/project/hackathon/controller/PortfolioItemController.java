package com.project.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hackathon.model.PortfolioItem;
import com.project.hackathon.model.Stock;
import com.project.hackathon.service.PortfolioItemService;

@RestController
@RequestMapping("/api/teamrocket/stocks")
public class PortfolioItemController {

    private final PortfolioItemService portfolioItemService;

    @Autowired
    public PortfolioItemController(PortfolioItemService portfolioItemService) {
        this.portfolioItemService = portfolioItemService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getall")
    public ResponseEntity<List<PortfolioItem>> getUserAllStocks() {
        List<PortfolioItem> stocks = portfolioItemService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getstockinfo/{tickerID}")
    public ResponseEntity<?> getStockInfo(@PathVariable("tickerID") String tickerID) {
        Stock stock = portfolioItemService.getStockByTickerID(tickerID);
        return ResponseEntity.ok(stock);
    }
}
