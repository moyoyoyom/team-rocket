package com.project.hackathon.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transactions {
    private Integer orderID;
    private Stock stock;
    private LocalDateTime date;
    private BigDecimal valueAtDate;
    private String type;
}
