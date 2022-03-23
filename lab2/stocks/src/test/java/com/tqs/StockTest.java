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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

        // assertEquals(175.00, result);
        assertThat(result, is(175.0));
        Mockito.verify(marketMock, Mockito.times(2)).lookUpPrice(Mockito.anyString());
    }
}
