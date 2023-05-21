package com.example.kripta.models;

public class RecyclerViewItems {

    String name,description, ticker;
    float priceDollar, priceRubl;

    public RecyclerViewItems(String name, String description, String ticker, float priceDollar, float priceRubl) {
        this.name = name;
        this.description = description;
        this.ticker = ticker;
        this.priceDollar = priceDollar;
        this.priceRubl = priceRubl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public float getPriceDollar() {
        return priceDollar;
    }

    public void setPriceDollar(float priceDollar) {
        this.priceDollar = priceDollar;
    }

    public float getPriceRubl() {
        return priceRubl;
    }

    public void setPriceRubl(float priceRubl) {
        this.priceRubl = priceRubl;
    }
}
