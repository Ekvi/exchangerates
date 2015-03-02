package com.exchangerates.server.utils;


import com.exchangerates.shared.model.Rates;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestRatesUtils {
    private RatesUtils utils;

    @Before
    public void init() {
        utils = RatesUtils.getRatesUtils();
    }

    @Test
    public void testCalculateDifference() {
        Rates newRates = new Rates("average", 16.300, 16.35, 22.89, 23.20, 0.28, 0.32);
        Rates oldRates = new Rates("old", 16.300, 16.31, 22.79, 23.15, 0.31, 0.34);
        double delta = 0.0001;

        Rates difference = utils.calculateDifference(newRates, oldRates);

        assertEquals(0, difference.getUsdBuying(), delta);
        assertEquals(0.04, difference.getUsdCelling(), delta);
        assertEquals(0.10, difference.getEurBuying(), delta);
        assertEquals(0.05, difference.getEurCelling(), delta);
        assertEquals(-0.03, difference.getRubBuying(), delta);
        assertEquals(-0.02, difference.getRubCelling(), delta);
    }

    @Test
    public void testCalculateDifferenceWhenOldRatesIsEmpty() {
        Rates newRates = new Rates("average", 16.300, 16.35, 22.89, 23.20, 0.28, 0.32);
        Rates oldRates = new Rates();
        double delta = 0.0001;

        Rates difference = utils.calculateDifference(newRates, oldRates);

        assertEquals(0, difference.getUsdBuying(), delta);
        assertEquals(0.0, difference.getUsdCelling(), delta);
        assertEquals(0.0, difference.getEurBuying(), delta);
        assertEquals(0.0, difference.getEurCelling(), delta);
        assertEquals(0.0, difference.getRubBuying(), delta);
        assertEquals(0.0, difference.getRubCelling(), delta);
    }

    @Test
    public void testCalculateAverage() {
        Rates rates1 = new Rates("rates1", 16.300, 16.34, 22.89, 23.29, 0.30, 0.36);
        Rates rates2 = new Rates("rates2", 16.340, 16.32, 22.79, 23.20, 0.28, 0.34);
        Rates rates3 = new Rates("rates3", 16.280, 16.35, 22.83, 23.27, 0.29, 0.38);
        Rates rates4 = new Rates("rates4", 16.150, 16.25, 22.69, 23.20, 0.31, 0.33);
        Rates rates5 = new Rates("rates5", 16.390, 16.45, 22.82, 23.22, 0.28, 0.32);
        Rates rates6 = new Rates("rates6", 16.150, 16.35, 22.69, 23.20, 0.00, 0.00);
        Rates rates7 = new Rates("rates7", 16.290, 16.40, 22.84, 23.45, 0.00, 0.00);

        List<Rates> list = Arrays.asList(rates1, rates2, rates3, rates4, rates5, rates6, rates7);

        double delta = 0.0001;
        Rates average = utils.calculateAverageRates(list);

        assertEquals(16.2714, average.getUsdBuying(), delta);
        assertEquals(16.3514, average.getUsdCelling(), delta);
        assertEquals(22.7928, average.getEurBuying(), delta);
        assertEquals(23.2614, average.getEurCelling(), delta);
        assertEquals(0.2085, average.getRubBuying(), delta);
        assertEquals(0.2471, average.getRubCelling(), delta);
    }


}
