package com.project.hackathon.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Stock {
    private String tickerSymbol;
    private BigDecimal currentPrice;
    private LocalDateTime timeUpdated;
}
