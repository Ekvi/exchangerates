package com.exchangerates.shared.dto;


import com.google.gwt.user.client.rpc.IsSerializable;

public class BankRatesDto implements IsSerializable {
    private String name;
    private String usdBuying;
    private String usdCelling;
    private String eurBuying;
    private String eurCelling;
    private String rubBuying;
    private String rubCelling;

    public BankRatesDto(){}

    public BankRatesDto(String name, String usdBuying,
                    String usdCelling, String eurBuying, String eurCelling, String rubBuying, String rubCelling) {
        this.name = name;
        this.usdBuying = usdBuying;
        this.usdCelling = usdCelling;
        this.eurBuying = eurBuying;
        this.eurCelling = eurCelling;
        this.rubBuying = rubBuying;
        this.rubCelling = rubCelling;
    }


    public String getName() {
        return name;
    }

    public String getUsdBuying() {
        return usdBuying;
    }

    public String getUsdCelling() {
        return usdCelling;
    }

    public String getEurBuying() {
        return eurBuying;
    }

    public String getEurCelling() {
        return eurCelling;
    }

    public String getRubBuying() {
        return rubBuying;
    }

    public String getRubCelling() {
        return rubCelling;
    }
}
