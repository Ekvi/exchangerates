package com.exchangerates.server.utils;


import com.exchangerates.server.exceptions.SiteNotAvailableException;
import com.exchangerates.shared.model.BankInfo;
import com.exchangerates.shared.model.Rates;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;



public class TestRatesExtractor {
    private RatesExtractor extractor;
    private BankInfo bankInfo;

    @Before
    public void init() {
        extractor = new RatesExtractor();
        initBankInfo();
    }

    public void initBankInfo() {
        bankInfo = new BankInfo();
        /*bankInfo.setBankName("УкрСибБанк");
        bankInfo.setUrl("https://my.ukrsibbank.com/ua/personal");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d).*");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("Надра банк");
        bankInfo.setUrl("http://www.nadrabank.ua/site/page.php");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n.*\\n(.*\\d)");
        bankInfo.setReplaceRegexp(".*<b>");*/
        /*bankInfo.setBankName("Ощадбанк");
        bankInfo.setUrl("http://www.oschadnybank.com/ua/private/currency/currency_rates/");
        bankInfo.setMainRegexp(".*\\n.*\\n.*\\n.*\\n.*\\n(.*\\d).*\\n(.*\\d).*");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("Пивденный банк");
        bankInfo.setUrl("http://bank.com.ua/ru/");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d).*");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("Финансы и Кредит Банк");
        bankInfo.setUrl("http://www.fcbank.com.ua");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d).*");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("Universal банк");
        bankInfo.setUrl("http://www.universalbank.com.ua");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d).*");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("УкрЭксим банк");
        bankInfo.setUrl("http://www.eximb.com/ukr/personal/");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d).*");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("УкрГаз банк");
        bankInfo.setUrl("http://www.ukrgasbank.com/eng/?%20&/");
        bankInfo.setMainRegexp(".*\\n.*\\n.*\\n(.*\\d).*\\n(.*\\d)");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("СберБанк России");
        bankInfo.setUrl("http://www.sberbank.ua");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d)");
        bankInfo.setReplaceRegexp("\\D+");*/
        bankInfo.setBankName("OPT Bank");
        bankInfo.setUrl("http://www.otpbank.com.ua");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d)");
        bankInfo.setReplaceRegexp("\\D+");
        /*bankInfo.setBankName("НБУ");
        bankInfo.setUrl("http://www.bank.gov.ua/control/uk/index");
        bankInfo.setMainRegexp(".*\\n(.*\\d)");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("Проминвест банк");
        bankInfo.setUrl("http://www.pib.com.ua");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d)");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("Райффайзен Банк Аваль");
        bankInfo.setUrl("http://www.aval.ua");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d)");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("Unicredit Bank");
        bankInfo.setUrl("http://www.unicredit.ua");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d)");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("ПУМБ");
        bankInfo.setUrl("http://pumb.ua/ru/");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d)");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("Чёрный рынок");
        bankInfo.setUrl("http://finance.i.ua");
        bankInfo.setMainRegexp(".*\\n.*\\d.*\\n(.*\\d.*)");
        bankInfo.setReplaceRegexp(".*<big>(\\d+\\.\\d+|\\d+)<\\/big>");*/
        /*bankInfo.setBankName("Альфа Банк");
        bankInfo.setUrl("http://www.alfabank.ua");
        bankInfo.setMainRegexp(".*\\n.*\\n(.*\\d)((\\/\\d+.\\d+)|(\\/\\d+))");
        bankInfo.setReplaceRegexp("\\D+");*/
        /*bankInfo.setBankName("ВТБ");
        bankInfo.setUrl("http://vtb.ua/private/av_currency/cur_operations/");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d)");
        bankInfo.setReplaceRegexp(".*<b>");*/
        /*bankInfo.setBankName("Креди Агриколь Банк");
        bankInfo.setUrl("https://credit-agricole.ua");
        bankInfo.setMainRegexp(".*\\n(.*\\d).*\\n(.*\\d)");
        bankInfo.setReplaceRegexp("\\D+");*/


    }

    @Test
    public void testReadRates() throws IOException, SiteNotAvailableException {
        Rates rates = extractor.getRates(bankInfo);
        System.out.println(rates.getName());
        System.out.println("USD " + rates.getUsdBuying() + " " + rates.getUsdCelling());
        System.out.println("EUR " + rates.getEurBuying() + " " + rates.getEurCelling());
        System.out.println("RUB " + rates.getRubBuying() + " " + rates.getRubCelling());
    }

    @Test(expected = SiteNotAvailableException.class)
    public void testGetRatesThrowException() throws SiteNotAvailableException {
        extractor.getRates(new BankInfo("fake name", "fake url"));
    }


}
