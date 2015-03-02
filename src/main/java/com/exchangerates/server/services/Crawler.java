package com.exchangerates.server.services;


import com.exchangerates.server.dao.BankInfoDao;
import com.exchangerates.server.dao.BanksRatesDao;
import com.exchangerates.server.dao.MainRatesDao;
import com.exchangerates.server.dao.impl.BankInfoDaoImpl;
import com.exchangerates.server.dao.impl.BanksRatesDaoImpl;
import com.exchangerates.server.dao.impl.MainRatesDaoImpl;
import com.exchangerates.server.exceptions.SiteNotAvailableException;
import com.exchangerates.server.listeners.RatesDownloadedListener;
import com.exchangerates.server.utils.RatesCalendar;
import com.exchangerates.server.utils.RatesExtractor;
import com.exchangerates.server.utils.RatesUtils;
import com.exchangerates.shared.Constants;
import com.exchangerates.shared.model.BankInfo;
import com.exchangerates.shared.model.MainRates;
import com.exchangerates.shared.model.Rates;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.*;


public class Crawler implements Job {
    private RatesUtils utils = RatesUtils.getRatesUtils();
    private Cache cache = Cache.getCache();
    private RatesExtractor extractor = new RatesExtractor();
    private MainRatesDao mainRatesDao = new MainRatesDaoImpl(MainRates.class);
    private BanksRatesDao banksRatesDao = new BanksRatesDaoImpl(Rates.class);
    private BankInfoDao bankInfoDao = new BankInfoDaoImpl(BankInfo.class);
    private RatesCalendar ratesCalendar = new RatesCalendar();
    private Logger logger = Logger.getLogger(Crawler.class);

    public void downloadSites() throws SchedulerException {
        JobKey jobKey = new JobKey("RatesDownloadedListener", "group1");
        JobDetail job = JobBuilder.newJob(Crawler.class)
                .withIdentity(jobKey).build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("schedule", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 5 2,5,8,11,17,23 * * ?"))    //corresponds 9,12,15,18 hours in Kiev
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.getListenerManager().addJobListener(
                new RatesDownloadedListener(), KeyMatcher.keyEquals(jobKey)
        );

        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public void execute(JobExecutionContext context) {
        initRates();
    }

    public void initRates() {
        MainRates oldMainRates = mainRatesDao.getLastRates("id");
        Map<String, Rates> ratesMap = downloadRates();

        List<Rates> banksRates = transformToList(ratesMap);
        Rates average = utils.calculateAverageRates(banksRates);

        MainRates mainRates = createMainRates(average, ratesMap.get(Constants.BLACK_MARKET), ratesMap.get(Constants.NBU));
        MainRates diff = utils.calculateDifference(mainRates, oldMainRates);

        cache.addDifference(diff);

        saveToDbMainRates(mainRates);
        saveToDbBanksRates(ratesMap);
    }

    Map<String, Rates> downloadRates() {
        Map<String, Rates> ratesMap = new HashMap<String, Rates>();

        for(BankInfo bank : getBanksInfo()) {
            try {
                Rates rates = extractor.getRates(bank);
                ratesMap.put(rates.getName(), rates);
            } catch (SiteNotAvailableException e) {
                logger.info("failed download " + bank.getBankName() + " rates");
                continue;
            }
        }
        return ratesMap;
    }

    List<BankInfo> getBanksInfo() {
        List<BankInfo> banks = bankInfoDao.getAll();
        cache.addBanksInfo(banks);
        return banks;
    }

    private List<Rates> transformToList(Map<String, Rates> map) {
        List<Rates> rates = new ArrayList<Rates>();

        for(Map.Entry<String, Rates> entry : map.entrySet()) {
            if(!entry.getKey().equals(Constants.NBU) && !entry.getKey().equals(Constants.BLACK_MARKET)) {
                rates.add(entry.getValue());
            }
        }
        return rates;
    }

    private void saveToDbMainRates(MainRates rates) {
        mainRatesDao.saveOrUpdate(rates);
    }

    private void saveToDbBanksRates(Map<String, Rates> rates) {
        banksRatesDao.update(rates);
    }

    private MainRates createMainRates(Rates averageRates, Rates blackRates, Rates nbuRates) {
        MainRates mainRates = new MainRates();
        mainRates.addAverageRates(averageRates);
        mainRates.addBlackMarketRates(blackRates);
        mainRates.addNbuRates(nbuRates);
        mainRates.setDate(ratesCalendar.getTodayDate());
        return mainRates;
    }
}
