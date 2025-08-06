package com.project.hackathon.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.project.hackathon.model.Stock;

@Service
public class StockService {
    private final String API_KEY;
    private final String BASE_URL = "https://finnhub.io/api/v1/quote";

    @Autowired
    public StockService(@Value("${finnhub.api.key}") String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public Stock getStockInformation(String symbol) {
        String stockEndpoint = String.format("%s?symbol=%s&token=%s", BASE_URL, symbol,
                API_KEY);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(stockEndpoint))
                .GET()
                .build();

        Optional<HttpResponse<String>> httpResponse = Optional.empty();
        JsonObject stockInformation;

        try {
            httpResponse = Optional.of(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()));
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            stockInformation = JsonParser.parseString(httpResponse.get().body()).getAsJsonObject();
        }

        Stock stock = new Stock();
        stock.setCurrentPrice(stockInformation.get("c").getAsBigDecimal());
        stock.setTimeUpdated(LocalDateTime.now());
        stock.setTickerSymbol(symbol);

        return stock;
    }
}
