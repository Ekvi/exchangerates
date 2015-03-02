package com.exchangerates.server.dao;


import com.exchangerates.server.dao.impl.MainRatesDaoImpl;
import com.exchangerates.shared.model.MainRates;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestMainRatesDao {
    private MainRatesDao mainRatesDao = new MainRatesDaoImpl(MainRates.class);

    @Test
    public void testSaveOrUpdate() {
        MainRates oldRates = mainRatesDao.getLastRates("id");

        oldRates.setAverageUsdBuying(23.0);
        oldRates.setAverageEurBuying(23.0);
        oldRates.setAverageRubBuying(22.0);
        oldRates.setAverageUsdCelling(22.0);
        oldRates.setAverageUsdCelling(22.0);
        oldRates.setAverageUsdCelling(22.0);
        oldRates.setBlackMarketUsd(22.0);
        oldRates.setBlackMarketEur(22.0);
        oldRates.setBlackMarketRub(22.0);
        oldRates.setNbuUsd(22.0);
        oldRates.setNbuEur(22.0);
        oldRates.setNbuRub(22.0);

        mainRatesDao.saveOrUpdate(oldRates);

        MainRates actual = mainRatesDao.getLastRates("id");

        assertEquals(oldRates.getAverageUsdBuying(), actual.getAverageUsdBuying(), 0.0001);
        assertEquals(oldRates.getAverageEurBuying(), actual.getAverageEurBuying(), 0.0001);
        assertEquals(oldRates.getAverageRubBuying(), actual.getAverageRubBuying(), 0.0001);
        assertEquals(oldRates.getNbuUsd(), actual.getNbuUsd(), 0.0001);
        assertEquals(oldRates.getNbuEur(), actual.getNbuEur(), 0.0001);
        assertEquals(oldRates.getNbuRub(), actual.getNbuRub(), 0.0001);
    }

    @Test
    public void testSaveOrUpdateAddNewData() {
        MainRates lastRates = mainRatesDao.getLastRates("id");
        MainRates rates = new MainRates();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 01, 9, 0, 0, 0);
        Date date = calendar.getTime();
        rates.setDate(date);

        mainRatesDao.saveOrUpdate(rates);

        MainRates newRates = mainRatesDao.getLastRates("id");

        assertEquals(lastRates.getId(), (newRates.getId() + 1));
    }
}
