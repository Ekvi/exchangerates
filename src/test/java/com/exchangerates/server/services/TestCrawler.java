package com.exchangerates.server.services;

import com.exchangerates.shared.model.BankInfo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class TestCrawler {
    private Crawler crawler = new Crawler();

    @Test
    public void testGetBanksInfo() {
        List<BankInfo> banksInfo = crawler.getBanksInfo();

        assertEquals(4, banksInfo.size());
        assertEquals("УкрСибБанк", banksInfo.get(0).getBankName());
        assertEquals(".*\\n(.*\\d).*\\n(.*\\d).*", banksInfo.get(0).getMainRegexp());
    }

    @Test
    public void testInitRates() {
        crawler.initRates();
    }
}
