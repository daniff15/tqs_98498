package com.tqs;

import com.tqs.Stock;
import java.util.ArrayList;

public class StocksPortfolio {
    private ArrayList<Stock> stocks;
    private IStockMarketService stockmarket;

    public StocksPortfolio(IStockMarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock newstock) {
        this.stocks.add(newstock);
    }

    public double getTotalValue() {
        double total = 0;

        for (Stock s : this.stocks) {
            total += (s.getQuantity() * stockmarket.lookUpPrice(s.getLabel()));
        }

        return total;
    }
}
