package com.exchangerates.shared.dto;


import com.exchangerates.shared.model.MainRates;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

public class GraphicsRates implements IsSerializable {
    private List<MainRates> rates;
    private List<String> dates;

    public GraphicsRates(){}

    public GraphicsRates(List<MainRates> rates, List<String> dates) {
        this.rates = rates;
        this.dates = dates;
    }

    public List<MainRates> getRates() {
        return rates;
    }

    public List<String> getDates() {
        return dates;
    }
}
