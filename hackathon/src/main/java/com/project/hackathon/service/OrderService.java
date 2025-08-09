package com.project.hackathon.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.hackathon.model.Order;
import com.project.hackathon.model.OrderAction;
import com.project.hackathon.model.PortfolioItem;
import com.project.hackathon.model.Stock;
import com.project.hackathon.model.Transactions;
import com.project.hackathon.repository.OrderRepository;
import com.project.hackathon.repository.PortfolioItemRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PortfolioItemRepository portfolioItemRepository;
    private final PortfolioItemService portfolioItemService;
    private final StockService stockService;

    public OrderService(OrderRepository orderRepository, PortfolioItemRepository portfolioItemRepository,
            PortfolioItemService portfolioItemService, StockService stockService) {
        this.orderRepository = orderRepository;
        this.portfolioItemRepository = portfolioItemRepository;
        this.portfolioItemService = portfolioItemService;
        this.stockService = stockService;
    }

    public List<Order> getTransactionHistory() {
        List<Order> orderHistory = orderRepository.findAll();
        return orderHistory;
    }

    public boolean makeTransaction(Order stockOrder) {
        OrderAction action = stockOrder.getOrderAction();
        List<PortfolioItem> existingStocks = portfolioItemRepository
                .findByTickerSymbol(stockOrder.getTickerSymbol());

        if (action == OrderAction.BUY) {
            if (existingStocks.size() > 0) {
                buyStock(existingStocks.get(0), stockOrder);
                return true;
            } else {
                // check if ticker symbol exists

                // then create new stock
                PortfolioItem newPortfolioItem = new PortfolioItem();
                // buy that one

                buyStock(newPortfolioItem, stockOrder);
            }
        } else if (action == OrderAction.SELL) {
            sellStock();
            return true;
        }

        stockOrder.setExecutionDateTime(LocalDateTime.now());
        orderRepository.save(stockOrder);

        return false;
    }

    public void buyStock(PortfolioItem portfolioItem, Order stockOrder) {
        // Updating the price of portfolio item
        BigDecimal newPortfolioItemPrice = portfolioItem.getCurrentPrice().add(stockOrder.getDollarAmount());
        portfolioItem.setCurrentPrice(newPortfolioItemPrice);

        // Updating the quantity of portfolio item
        BigDecimal priceOfOneStock = portfolioItemService.getStockByTickerID(stockOrder.getTickerSymbol())
                .getCurrentPrice();
        BigDecimal quantityOfStock = newPortfolioItemPrice.divide(priceOfOneStock);
        portfolioItem.setQuantity(quantityOfStock);

        // Save the stock
        portfolioItemRepository.save(portfolioItem);
    }

    public void sellStock() {

    }

    public List<Transactions> convertOrdersToTransactions(List<Order> orders) {
        List<Transactions> transactions = new ArrayList<>();

        for (Order order : orders) {
            String tickerSymbol = order.getTickerSymbol();
            Stock stock = stockService.getStockInformation(tickerSymbol);
            BigDecimal currentValue = stock.getCurrentPrice();
            stock.setName(stock.getTickerSymbol());

            Transactions transaction = new Transactions(order.getOrderID(), stock, order.getExecutionDateTime(),
                    order.getDollarAmount(), currentValue, order.getOrderAction().toString());

            transactions.add(transaction);
        }
        return transactions;
    }
}
