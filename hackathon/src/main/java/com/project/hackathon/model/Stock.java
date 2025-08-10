package com.project.hackathon.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private String name;
    private String tickerSymbol;
    private BigDecimal currentPrice;
    private LocalDateTime timeUpdated;
}
