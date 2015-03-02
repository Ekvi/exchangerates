package com.exchangerates.server.services;


import com.exchangerates.server.dao.BanksRatesDao;
import com.exchangerates.server.dao.MainRatesDao;
import com.exchangerates.server.dao.impl.BanksRatesDaoImpl;
import com.exchangerates.server.dao.impl.MainRatesDaoImpl;
import com.exchangerates.server.utils.RatesCalendar;
import com.exchangerates.server.utils.RatesCreator;
import com.exchangerates.shared.dto.*;
import com.exchangerates.client.services.RatesService;
import com.exchangerates.shared.model.MainRates;
import com.exchangerates.shared.model.Rates;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import java.util.*;

import static java.util.Calendar.*;

public class RatesServiceImpl extends RemoteServiceServlet implements RatesService {
    private Cache cache = Cache.getCache();
    private MainRatesDao mainRatesDao = new MainRatesDaoImpl(MainRates.class);
    private BanksRatesDao banksRatesDao = new BanksRatesDaoImpl(Rates.class);
    private RatesCreator ratesCreator = new RatesCreator();
    private RatesCalendar ratesCalendar = new RatesCalendar();

    static Logger logger = Logger.getLogger(RatesServiceImpl.class);

    static {
        Crawler crawler = new Crawler();
        try {
            crawler.downloadSites();
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.info("Scheduler exception " + e.getMessage());
        }
    }

    @Override
    public AllRates getAllRates() {
        List<MainRatesDto> mainRates = getMainRates();
        List<BankRatesDto> bankRates = getBankRates();
        List<SingleRatesDto> singleRates = getSingleRates();
        GraphicsRates graphicRates = new GraphicsRates(getDataForGraphic(), getDates(new Date()));

        return new AllRates(mainRates, bankRates, singleRates, graphicRates);
    }

    public List<MainRatesDto> getMainRates() {
        MainRates mainRates = mainRatesDao.getLastRates("id");

        List<MainRatesDto> ratesDto = new ArrayList<MainRatesDto>();
        if(mainRates != null) {
            ratesDto = ratesCreator.createMainRates(mainRates, cache.getDifference());
        }
        return ratesDto;
    }

    public List<BankRatesDto> getBankRates() {
        return ratesCreator.createBankRates(getRatesFromDB());
    }

    @Override
    public List<SingleRatesDto> getSingleRates() {
        return ratesCreator.createSingleRates(getRatesFromDB());
    }

    @Override
    public List<SingleRatesDto> getSingleRates(String flag) {
        return ratesCreator.createSingleRates(getRatesFromDB(), flag);
    }

    private List<Rates> getRatesFromDB() {
        return banksRatesDao.getAll();
    }

    @Override
    public List<MainRatesDto> getOldMainRates(String strDate) {
        Date date = ratesCalendar.convertStringToDate(strDate);
        MainRates mainRates = mainRatesDao.get("date", date);

        List<MainRatesDto> mainRatesList = new ArrayList<MainRatesDto>();
        if(mainRates != null) {
            Calendar old = Calendar.getInstance();
            old.setTime(date);
            Calendar today = Calendar.getInstance();
            today.setTime(ratesCalendar.getTodayDate());

            if(old.get(DAY_OF_MONTH) == today.get(DAY_OF_MONTH)
                                        && old.get(MONTH) == today.get(MONTH) && old.get(YEAR) == today.get(YEAR)) {
                mainRatesList = ratesCreator.createMainRates(mainRates, cache.getDifference());
            }
            else {
                mainRatesList = ratesCreator.createMainRates(mainRates);
            }
        }
        return mainRatesList;
    }

    public List<MainRates> getDataForGraphic() {
        List<MainRates> rates = mainRatesDao.getRates(ratesCalendar.getStartDate(new Date()), ratesCalendar.getTodayDate());
        return rates;
    }

    private List<String> getDates(Date date) {
        return ratesCalendar.getDaysAndMonth(date);
    }
}
