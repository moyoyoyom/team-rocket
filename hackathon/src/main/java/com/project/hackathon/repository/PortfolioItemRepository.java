package com.project.hackathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hackathon.model.PortfolioItem;

@Repository
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Integer> {
    List<PortfolioItem> findByTickerSymbol(String tickerSymbol);

}