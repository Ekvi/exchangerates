package com.exchangerates.server.dao;

import com.exchangerates.server.dao.impl.BanksRatesDaoImpl;
import com.exchangerates.server.utils.RatesCalendar;
import com.exchangerates.shared.model.Rates;
import org.junit.Test;


public class TestBanksRatesDao {
    private BanksRatesDao banksRatesDao = new BanksRatesDaoImpl(Rates.class);
    private RatesCalendar ratesCalendar = new RatesCalendar();

    @Test
    public void testUpdate() {
        //banksRatesDao.update("date", ratesCalendar.getTodayDate(), "name", "ПУМБ", new Rates());
    }
}
