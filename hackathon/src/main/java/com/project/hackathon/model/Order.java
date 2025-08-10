package com.project.hackathon.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @Column(nullable = false)
    private String tickerSymbol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderAction orderAction;

    @Column(nullable = false)
    private BigDecimal dollarAmount;

    @Column(nullable = false)
    private BigDecimal priceOfOneShare;

    @Column(nullable = false)
    private LocalDateTime executionDateTime;
}
