package com.project.hackathon.controller;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.hackathon.model.PortfolioItem;
import com.project.hackathon.service.PortfolioItemService;

public class PortfolioItemControllerTest {
    private PortfolioItemService mockPortfolioItemService;
    private PortfolioItemController mockPortfolioItemController;

    @BeforeEach
    public void setUp() {
        mockPortfolioItemService = mock(PortfolioItemService.class);
        mockPortfolioItemController = new PortfolioItemController(mockPortfolioItemService);

        PortfolioItem mockPortfolioItemOne = new PortfolioItem(
                1, "Apple", "AAPL", BigDecimal.valueOf(7.29),
                BigDecimal.valueOf(129.98), BigDecimal.valueOf(120.32),
                LocalDateTime.of(2024, 11, 3, 11, 5));
        PortfolioItem mockPortfolioItemTwo = new PortfolioItem(
                2, "Intel Corp", "INTC", BigDecimal.valueOf(89.23),
                BigDecimal.valueOf(20.98), BigDecimal.valueOf(30.89),
                LocalDateTime.of(2025, 2, 8, 7, 2, 56));
        PortfolioItem mockPortfolioItemThree = new PortfolioItem(
                3, "Coca-Cola Co", "KO", BigDecimal.valueOf(30.6),
                BigDecimal.valueOf(58.29), BigDecimal.valueOf(70.89),
                LocalDateTime.of(2026, 5, 30, 9, 58));

        List<PortfolioItem> mockPortfolioItems = List.of(mockPortfolioItemOne, mockPortfolioItemTwo,
                mockPortfolioItemThree);

        when(mockPortfolioItemService.getAllStocks()).thenReturn(mockPortfolioItems);
    }

    @Test
    public void getUserAllStocksTest() {
        List<PortfolioItem> expectedStocks = List.of(new PortfolioItem(
                1, "Apple", "AAPL", BigDecimal.valueOf(7.29),
                BigDecimal.valueOf(129.98), BigDecimal.valueOf(120.32),
                LocalDateTime.of(2024, 11, 3, 11, 5)),
                new PortfolioItem(
                        2, "Intel Corp", "INTC", BigDecimal.valueOf(89.23),
                        BigDecimal.valueOf(20.98), BigDecimal.valueOf(30.89),
                        LocalDateTime.of(2025, 2, 8, 7, 2, 56)),
                new PortfolioItem(
                        3, "Coca-Cola Co", "KO", BigDecimal.valueOf(30.6),
                        BigDecimal.valueOf(58.29), BigDecimal.valueOf(70.89),
                        LocalDateTime.of(2026, 5, 30, 9, 58)));

        List<PortfolioItem> actualStocks = mockPortfolioItemController.getUserAllStocks().getBody();

        assertThat(actualStocks).containsExactlyElementsOf(expectedStocks);
    }

}
