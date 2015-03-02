package com.exchangerates.server.utils;


import com.exchangerates.shared.model.MainRates;
import com.exchangerates.shared.model.Rates;

import java.util.List;

public class RatesUtils {
    private static RatesUtils ratesUtils;
    private RatesExtractor extractor = new RatesExtractor();
    private int skip = 0;

    private RatesUtils(){}

    public static RatesUtils getRatesUtils() {
        if(ratesUtils == null) {
            ratesUtils = new RatesUtils();
        }
        return ratesUtils;
    }

    public void setSkip(int skip) {
        this.skip += skip;
    }

    public Rates calculateDifference(Rates newRates, Rates oldRates) {
        Rates difRates = new Rates();

        difRates.setUsdBuying(getDifference(newRates.getUsdBuying(), oldRates.getUsdBuying()));
        difRates.setUsdCelling(getDifference(newRates.getUsdCelling(), oldRates.getUsdCelling()));
        difRates.setEurBuying(getDifference(newRates.getEurBuying(), oldRates.getEurBuying()));
        difRates.setEurCelling(getDifference(newRates.getEurCelling(), oldRates.getEurCelling()));
        difRates.setRubBuying(getDifference(newRates.getRubBuying(), oldRates.getRubBuying()));
        difRates.setRubCelling(getDifference(newRates.getRubCelling(), oldRates.getRubCelling()));

        return difRates;
    }

    public MainRates calculateDifference(MainRates newRates, MainRates oldRates) {
        MainRates difRates = new MainRates();

        difRates.setAverageUsdBuying(getDifference(newRates.getAverageUsdBuying(), oldRates.getAverageUsdBuying()));
        difRates.setAverageEurBuying(getDifference(newRates.getAverageEurBuying(), oldRates.getAverageEurBuying()));
        difRates.setAverageRubBuying(getDifference(newRates.getAverageRubBuying(), oldRates.getAverageRubBuying()));
        difRates.setAverageUsdCelling(getDifference(newRates.getAverageUsdCelling(), oldRates.getAverageUsdCelling()));
        difRates.setAverageEurCelling(getDifference(newRates.getAverageEurCelling(), oldRates.getAverageEurCelling()));
        difRates.setAverageRubCelling(getDifference(newRates.getAverageRubCelling(), oldRates.getAverageRubCelling()));
        difRates.setBlackMarketUsd(getDifference(newRates.getBlackMarketUsd(), oldRates.getBlackMarketUsd()));
        difRates.setBlackMarketEur(getDifference(newRates.getBlackMarketEur(), oldRates.getBlackMarketEur()));
        difRates.setBlackMarketRub(getDifference(newRates.getBlackMarketRub(), oldRates.getBlackMarketRub()));
        difRates.setNbuUsd(getDifference(newRates.getNbuUsd(), oldRates.getNbuUsd()));
        difRates.setNbuEur(getDifference(newRates.getNbuEur(), oldRates.getNbuEur()));
        difRates.setNbuRub(getDifference(newRates.getNbuRub(), oldRates.getNbuRub()));

        return difRates;
    }

    private double getDifference(double rates, double old) {
        return old != 0 ? rates - old : 0;
    }

    public Rates calculateAverageRates(List<Rates> ratesList) {
        Rates averageRates = new Rates();

        for(Rates rates : ratesList) {
            averageRates.setUsdBuying(averageRates.getUsdBuying() + rates.getUsdBuying());
            averageRates.setUsdCelling(averageRates.getUsdCelling() + rates.getUsdCelling());
            averageRates.setEurBuying(averageRates.getEurBuying() + rates.getEurBuying());
            averageRates.setEurCelling(averageRates.getEurCelling() + rates.getEurCelling());
            averageRates.setRubBuying(averageRates.getRubBuying() + rates.getRubBuying());
            averageRates.setRubCelling(averageRates.getRubCelling() + rates.getRubCelling());
        }

        averageRates.setName("average");
        averageRates.setUsdBuying(averageRates.getUsdBuying() / (ratesList.size() - skip - extractor.getIgnore()));
        averageRates.setUsdCelling(averageRates.getUsdCelling() / (ratesList.size() - skip - extractor.getIgnore()));
        averageRates.setEurBuying(averageRates.getEurBuying() / (ratesList.size() - skip - extractor.getIgnore()));
        averageRates.setEurCelling(averageRates.getEurCelling() / (ratesList.size() - skip - extractor.getIgnore()));
        averageRates.setRubBuying(averageRates.getRubBuying() / (ratesList.size() - skip - extractor.getIgnore()));
        averageRates.setRubCelling(averageRates.getRubCelling() / (ratesList.size()- skip- extractor.getIgnore()));

        skip = 0;
        extractor.setIgnore(0);

        return averageRates;
    }
}
