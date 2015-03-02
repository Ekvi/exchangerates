package com.exchangerates.shared.dto;


import com.google.gwt.user.client.rpc.IsSerializable;

public class SingleRatesDto implements IsSerializable {
    private String name;
    private String buying;
    private String celling;

    public SingleRatesDto(){}

    public SingleRatesDto(String name, String buying, String celling) {
        this.name = name;
        this.buying = buying;
        this.celling = celling;
    }

    public String getName() {
        return name;
    }

    public String getBuying() {
        return buying;
    }

    public String getCelling() {
        return celling;
    }
}
