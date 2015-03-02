package com.exchangerates.server.services;

import com.exchangerates.server.exceptions.SiteNotAvailableException;
import com.exchangerates.server.utils.RatesExtractor;
import com.exchangerates.shared.model.BankInfo;
import com.exchangerates.shared.model.Rates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class UnitTestCrawler {
    @Mock
    private RatesExtractor extractor = new RatesExtractor();
    @Spy
    @InjectMocks
    private Crawler crawler = new Crawler();

    @Test
    public void testDownloadRates() throws SiteNotAvailableException {
        /*List<BankInfo> fakeBanksInfo = Arrays.asList(new BankInfo("http://www.otpbank.com.ua"),
                                        new BankInfo("fake url"), new BankInfo("http://www.unicredit.ua"),
                                        new BankInfo("fake url"), new BankInfo("https://privatbank.ua"));*/
        List<BankInfo> fakeBanksInfo = Arrays.asList(new BankInfo("opt", "http://www.otpbank.com.ua"),
                new BankInfo("private", "https://privatbank.ua"), new BankInfo("unicredit", "http://www.unicredit.ua"),
                new BankInfo("pib", "http://www.pib.com.ua"), new BankInfo("aval", "http://www.aval.ua"));

        Rates fakeRates = mock(Rates.class);
        //Rates fakeRates = new Rates("fake name", 0, 0, 0, 0, 0, 0);

        when(crawler.getBanksInfo()).thenReturn(fakeBanksInfo);
        doReturn(fakeRates).when(extractor).getRates(any(BankInfo.class));
        when(extractor.getRates(fakeBanksInfo.get(1))).thenThrow(new SiteNotAvailableException());
        when(extractor.getRates(fakeBanksInfo.get(3))).thenThrow(new SiteNotAvailableException());


        Map<String, Rates> rates = crawler.downloadRates();

        verify(extractor, times(5)).getRates(any(BankInfo.class));
        verify(fakeRates, times(3)).getName();
        //assertEquals(3, rates.size());

    }
}
