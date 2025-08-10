package com.project.hackathon.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat.Style;
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
                System.out.println("Adding to existing stock");
                buyStock(existingStocks.get(0), stockOrder);
                return true;
            } else {
                System.out.println("Buying new type of stock");
                // then create new stock
                PortfolioItem newPortfolioItem = new PortfolioItem();
                // buy that one
                buyStock(newPortfolioItem, stockOrder);
            }
        } else if (action == OrderAction.SELL) {
            PortfolioItem portfolioItem = existingStocks.get(0);
            sellStock(portfolioItem, stockOrder);
            return true;
        }

        stockOrder.setExecutionDateTime(LocalDateTime.now());
        orderRepository.save(stockOrder);

        return false;
    }

    public void buyStock(PortfolioItem portfolioItem, Order stockOrder) {
        // Getting the current price of one stock
        Stock stock = stockService.getStockInformation(portfolioItem.getTickerSymbol());
        portfolioItem.setCurrentPrice(stock.getCurrentPrice());

        // Updating the price of portfolio item
        BigDecimal newPortfolioItemPrice = portfolioItem.getCurrentPrice().add(stockOrder.getDollarAmount());
        portfolioItem.setCurrentPrice(newPortfolioItemPrice);

        // Updating the quantity of portfolio item
        BigDecimal priceOfOneStock = portfolioItemService.getStockByTickerID(stockOrder.getTickerSymbol())
                .getCurrentPrice();
        if (priceOfOneStock.compareTo(BigDecimal.ZERO) == 0) {
            priceOfOneStock = BigDecimal.valueOf(1);
        }
        BigDecimal quantityOfStock = newPortfolioItemPrice.divide(priceOfOneStock, 2, RoundingMode.HALF_EVEN);
        portfolioItem.setQuantity(quantityOfStock);

        portfolioItem.setName(stock.getTickerSymbol());
        portfolioItem.setPriceBoughtAt(stockOrder.getDollarAmount());
        portfolioItem.setTickerSymbol(stock.getTickerSymbol());
        stockOrder.setPriceOfOneShare(priceOfOneStock);

        System.out.println(portfolioItem);
        System.out.println(stockOrder);

        // Save the stock
        portfolioItemRepository.save(portfolioItem);

        // Save the transaction
        orderRepository.save(stockOrder);
    }

    public void sellStock(PortfolioItem portfolioItem, Order stockOrder) {
        // Update portfolio item
        BigDecimal newPortfolioItemPrice = portfolioItem.getCurrentPrice().subtract(stockOrder.getDollarAmount());
        portfolioItem.setCurrentPrice(newPortfolioItemPrice);

        BigDecimal priceOfOneStock = portfolioItemService.getStockByTickerID(stockOrder.getTickerSymbol())
                .getCurrentPrice();

        stockOrder.setPriceOfOneShare(priceOfOneStock);
        // Save the portfolio item
        portfolioItemRepository.save(portfolioItem);

        // Save the stock order
        orderRepository.save(stockOrder);

    }

    public List<Transactions> convertOrdersToTransactions(List<Order> orders) {
        List<Transactions> transactions = new ArrayList<>();

        for (Order order : orders) {
            String tickerSymbol = order.getTickerSymbol();
            Stock stock = stockService.getStockInformation(tickerSymbol);

            BigDecimal sharesBought = order.getDollarAmount().divide(order.getPriceOfOneShare(), 2,
                    RoundingMode.HALF_EVEN);
            BigDecimal currentValue = sharesBought.multiply(stock.getCurrentPrice());

            stock.setName(stock.getTickerSymbol());

            Transactions transaction = new Transactions(order.getOrderID(), stock, order.getExecutionDateTime(),
                    order.getDollarAmount(), currentValue, order.getOrderAction().toString());

            transactions.add(transaction);
        }
        return transactions;
    }
}
