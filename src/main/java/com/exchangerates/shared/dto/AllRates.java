package com.exchangerates.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;


public class AllRates implements IsSerializable {
    private List<MainRatesDto> mainRates;
    private List<BankRatesDto> bankRates;
    private List<SingleRatesDto> singleRates;
    private GraphicsRates graphicsRates;

    public AllRates(){}

    public AllRates(List<MainRatesDto> mainRates, List<BankRatesDto> bankRates,
                                        List<SingleRatesDto> singleRates, GraphicsRates graphicsRates) {
        this.mainRates = mainRates;
        this.bankRates = bankRates;
        this.singleRates = singleRates;
        this.graphicsRates = graphicsRates;
    }

    public List<MainRatesDto> getMainRates() {
        return mainRates;
    }

    public List<BankRatesDto> getBankRates() {
        return bankRates;
    }

    public List<SingleRatesDto> getSingleRates() {
        return singleRates;
    }

    public GraphicsRates getGraphicsRates() {
        return graphicsRates;
    }
}
