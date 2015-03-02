package com.exchangerates.shared.dto;


import com.google.gwt.user.client.rpc.IsSerializable;

public class MainRatesDto implements IsSerializable {
    private String rate;
    private String difference;
    private String imageName;

    public MainRatesDto(){}

    public MainRatesDto(String rate, String difference, String imageName) {
        this.rate = rate;
        this.difference = difference;
        this.imageName = imageName;
    }

    public String getRate() {
        return rate;
    }

    public String getDifference() {
        return difference;
    }

    public String getImageName() {
        return imageName;
    }
}
