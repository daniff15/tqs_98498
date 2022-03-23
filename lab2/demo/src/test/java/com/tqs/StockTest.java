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

    @BeforeEach
    public void setUp() {
        portfolio = new StocksPortfolio(marketMock);
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void getTotalValue() {

        Mockito.when(marketMock.lookUpPrice("EBAY")).thenReturn(42.0);

        Stock ebayStock = new Stock("EBAY", 2);
        portfolio.addStock(ebayStock);

        double result = portfolio.getTotalValue();

        Assertions.assertEquals(84.00, result, 0.001);
    }
}
