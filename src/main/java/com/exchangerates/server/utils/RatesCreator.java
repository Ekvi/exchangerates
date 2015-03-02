package com.exchangerates.server.utils;


import com.exchangerates.shared.Currency;
import com.exchangerates.shared.dto.BankRatesDto;
import com.exchangerates.shared.dto.MainRatesDto;
import com.exchangerates.shared.dto.SingleRatesDto;
import com.exchangerates.shared.model.MainRates;
import com.exchangerates.shared.model.Rates;

import java.util.ArrayList;
import java.util.List;

public class RatesCreator {

    public List<MainRatesDto> createMainRates(MainRates mainRates, MainRates difference) {
        List<MainRatesDto> list = new ArrayList<MainRatesDto>();

        list.add(createMainRates(mainRates.getAverageUsdBuying(), difference.getAverageUsdBuying()));
        list.add(createMainRates(mainRates.getAverageEurBuying(), difference.getAverageEurBuying()));
        list.add(createMainRates(mainRates.getAverageRubBuying(), difference.getAverageRubBuying()));
        list.add(createMainRates(mainRates.getAverageUsdCelling(), difference.getAverageUsdBuying()));
        list.add(createMainRates(mainRates.getAverageEurCelling(), difference.getAverageEurBuying()));
        list.add(createMainRates(mainRates.getAverageRubCelling(), difference.getAverageRubBuying()));
        list.add(createMainRates(mainRates.getBlackMarketUsd(), difference.getBlackMarketUsd()));
        list.add(createMainRates(mainRates.getBlackMarketEur(), difference.getBlackMarketEur()));
        list.add(createMainRates(mainRates.getBlackMarketRub(), difference.getBlackMarketRub()));
        list.add(createMainRates(mainRates.getNbuUsd(), difference.getNbuUsd()));
        list.add(createMainRates(mainRates.getNbuEur(), difference.getNbuEur()));
        list.add(createMainRates(mainRates.getNbuRub(), difference.getNbuRub()));

        return list;
    }

    public List<MainRatesDto> createMainRates(MainRates mainRates) {
        MainRates difference = new MainRates(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        return createMainRates(mainRates, difference);
    }

    private MainRatesDto createMainRates(double ratesValue, double difference) {
        if(difference > 0) {
            return new MainRatesDto(transform(ratesValue), "+" + transform(difference), "up");
        } else if(difference < 0) {
            return new MainRatesDto(transform(ratesValue), transform(difference), "down");
        } else {
            return new MainRatesDto(transform(ratesValue), "", "");
        }
    }

    public List<BankRatesDto> createBankRates(List<Rates> banksRates) {
        List<BankRatesDto> banksRatesList = new ArrayList<BankRatesDto>();

        for(Rates rates : banksRates) {
            banksRatesList.add(transformToBankRatesDto(rates));
        }

        return banksRatesList;
    }

    private BankRatesDto transformToBankRatesDto(Rates rates) {
        BankRatesDto bankRates = new BankRatesDto(rates.getName(), transform(rates.getUsdBuying()),
                transform(rates.getUsdCelling()), transform(rates.getEurBuying()), transform(rates.getEurCelling()),
                transform(rates.getRubBuying()), transform(rates.getRubCelling()));

        return bankRates;
    }

    public List<SingleRatesDto> createSingleRates(List<Rates> rates) {
        return createSingleRates(rates, Currency.USD.getName());
    }

    public List<SingleRatesDto> createSingleRates(List<Rates> rates, String currency) {
        List<SingleRatesDto> singleRatesList = new ArrayList<SingleRatesDto>();

        for(Rates rate : rates) {
            SingleRatesDto singleRates;
            if(currency.equals(Currency.USD.getName())) {
                singleRates = new SingleRatesDto(rate.getName(),
                        transform(rate.getUsdBuying()), transform(rate.getUsdCelling()));
            } else if(currency.equals(Currency.EUR.getName())) {
                singleRates = new SingleRatesDto(rate.getName(),
                        transform(rate.getEurBuying()), transform(rate.getEurCelling()));
            } else {
                singleRates = new SingleRatesDto(rate.getName(),
                        transform(rate.getRubBuying()), transform(rate.getRubCelling()));
            }
            singleRatesList.add(singleRates);
        }
        return singleRatesList;
    }

    private String transform(double value) {
        String format = value < 10 ? "%.4f" : "%.3f";
        return String.format(format, value);
    }
}
