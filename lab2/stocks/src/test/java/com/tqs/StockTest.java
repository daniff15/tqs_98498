package com.tqs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
public class StockTest {
    @Mock
    IStockMarketService marketMock;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    public void getTotalValue() {

        Mockito.when(marketMock.lookUpPrice("WORTEN")).thenReturn(50.0);
        Mockito.when(marketMock.lookUpPrice("DELLOITE")).thenReturn(15.0);

        Stock wortenStock = new Stock("WORTEN", 2);
        Stock delloiteStock = new Stock("DELLOITE", 5);
        portfolio.addStock(wortenStock);
        portfolio.addStock(delloiteStock);

        double result = portfolio.getTotalValue();

        Assertions.assertEquals(175.00, result);
    }
}
