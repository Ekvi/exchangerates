package com.exchangerates.server.utils;


import com.exchangerates.server.exceptions.SiteNotAvailableException;
import com.exchangerates.shared.Constants;
import com.exchangerates.shared.model.*;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RatesExtractor {
    private RatesCalendar calendar = new RatesCalendar();
    private BankInfo bankInfo;
    private List<String> banksNames = Arrays.asList("Ощадбанк", "Финансы и Кредит Банк", "УкрГаз банк", "НБУ");
    private String[] defaultCurrency = {"USD", "EUR", "RUB"};
    private String[] nbuCurrency = {"Доларів США", "Євро</td>", "Російських рублів"};
    private String[] avalCurrency = {"\\$", "€", "Р<s>уб"};
    private String[] sberbankCurrency = {"1_img.gif", "2_img.gif", "3_img.gif"};
    private String userAgent = "OPR/26.0.1656.60 Chrome/33.0.1750.154";
    int ignore = 0;

    private Logger logger = Logger.getLogger(RatesExtractor.class);

    public Rates getRates(BankInfo bankInfo) throws SiteNotAvailableException {
        this.bankInfo = bankInfo;

        Document doc;
        try {
            doc = Jsoup.connect(bankInfo.getUrl()).userAgent(userAgent).timeout(30000).get();
        } catch (Exception e) {
            logger.info("can't read site for " + bankInfo.getBankName());
            logger.info(e.getMessage());
            throw new SiteNotAvailableException();
        }

        if(bankInfo.getBankName().equals("НБУ")) {
            return extractDefault(doc.body().html(), nbuCurrency);
        } else if(bankInfo.getBankName().equals("Райффайзен Банк Аваль")) {
            return extractDefault(doc.body().html(), avalCurrency);
        } else if(bankInfo.getBankName().equals("Чёрный рынок")) {
            return extractBlackMarket(doc.body().html(), defaultCurrency);
        } else if(bankInfo.getBankName().equals("СберБанк России")) {
            return extractDefault(doc.body().html(), sberbankCurrency);
        }
        else {
            return extractDefault(doc.body().html(), defaultCurrency);
        }
    }

    private Rates extractDefault(String content, String[] currency) {
        String[] usdRates = extractRates(content, currency[0]);
        String[] eurRates = extractRates(content, currency[1]);
        String[] rubRates = extractRates(content, currency[2]);

        if(usdRates[0].equals("0.0") && eurRates[0].equals("0.0") && rubRates[0].equals("0.0")) {
            RatesUtils.getRatesUtils().setSkip(1);
        }

        return createRates(usdRates, eurRates, rubRates);
    }

    private Rates extractBlackMarket(String content, String[] currency) {
        String regExp = "черный рынок(.*\\n)+.*Средний курс валют в банках";
        Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        String partContent = matcher.find() ? matcher.group() : "";

        String[] usdRates = extractBlackMarketRates(partContent, currency[0]);
        String[] eurRates = extractBlackMarketRates(partContent, currency[1]);
        String[] rubRates = extractBlackMarketRates(partContent, currency[2]);

        return createRates(usdRates, eurRates, rubRates);
    }

    private Rates createRates(String[] usdRates, String[] eurRates, String[] rubRates) {
        Rates rates = new Rates();
        int divider = banksNames.contains(bankInfo.getBankName()) ? Constants.USD_EUR_DIVIDER : 1;
        int rubDivider = banksNames.contains(bankInfo.getBankName()) ? Constants.RUB_DIVIDER : 1;

        rates.setName(bankInfo.getBankName());
        rates.setUsdBuying(Double.parseDouble(usdRates[0].replace(",", ".")) / divider);
        rates.setUsdCelling(Double.parseDouble(usdRates[1].replace(",", ".")) / divider);
        rates.setEurBuying(Double.parseDouble(eurRates[0].replace(",", ".")) / divider);
        rates.setEurCelling(Double.parseDouble(eurRates[1].replace(",", ".")) / divider);
        rates.setRubBuying(Double.parseDouble(rubRates[0].replace(",", ".")) / rubDivider);
        rates.setRubCelling(Double.parseDouble(rubRates[1].replace(",", ".")) / rubDivider);
        rates.setDate(calendar.getTodayDate());
        return rates;
    }

    private String[] extractRates(String content, String currency) {
        String[] rate = new String[2];
        Pattern pattern = Pattern.compile(currency + bankInfo.getMainRegexp(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find()) {
            if (currency.equals(nbuCurrency[0]) || currency.equals(nbuCurrency[1]) || currency.equals(nbuCurrency[2])) {
                rate[0] = matcher.group(1).replaceFirst(bankInfo.getReplaceRegexp(), "");
                rate[1] = "0,0";
            } else {
                rate[0] = matcher.group(1).replaceFirst(bankInfo.getReplaceRegexp(), "");
                rate[1] = matcher.group(2).replaceFirst(bankInfo.getReplaceRegexp(), "");
            }
        } else {
            rate[0] = "0.0";
            rate[1] = "0.0";
            ignore++;
        }

        return rate;
    }

    private String[] extractBlackMarketRates(String content, String currency) {
        String[] rates = new String[2];
        Pattern pattern = Pattern.compile(currency + bankInfo.getMainRegexp(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);

        if(matcher.find()) {
            rates[0] = extract(matcher.group(1), bankInfo.getReplaceRegexp());
        }
        rates[1] = "0,0";
        return rates;
    }

    private String extract(String content, String regexp) {
        String rate = "";
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find()) {
            rate = matcher.group(1);
        }
        return rate;
    }

    public int getIgnore() {
        return ignore;
    }

    public void setIgnore(int ignore) {
        this.ignore = ignore;
    }
}
