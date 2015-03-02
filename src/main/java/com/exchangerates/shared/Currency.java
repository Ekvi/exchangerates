package com.exchangerates.shared;


public enum Currency {
    USD("USD - доллар"), EUR("EUR - евро"), RUB("RUB - рубль");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
