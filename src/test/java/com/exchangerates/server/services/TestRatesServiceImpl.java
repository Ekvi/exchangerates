package com.exchangerates.server.services;


import com.exchangerates.shared.dto.AllRates;
import com.exchangerates.shared.dto.BankRatesDto;
import com.exchangerates.shared.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestRatesServiceImpl {
    private RatesServiceImpl service;

    @Before
    public void init() {
        service = new RatesServiceImpl();
    }

    @Test
    public void testGetBanksRates() {
        List<BankRatesDto> list = service.getBankRates();

        assertNotNull(list);
        assertNotNull(list.get(0));
        assertEquals(13, list.size());
    }

    @Test
    public void testGetMainRates() {
        assertNotNull(service.getMainRates());
    }

    @Test
    public void testGetAllRates() {
        AllRates allRates = service.getAllRates();

        assertNotNull(allRates.getMainRates());
        assertNotNull(allRates.getBankRates());
        assertNotNull(allRates.getSingleRates());
    }
}