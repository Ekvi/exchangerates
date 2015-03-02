package com.exchangerates.server.dao;


import com.exchangerates.shared.model.MainRates;

import java.util.Date;
import java.util.List;


public interface MainRatesDao extends BasicCrudDao<MainRates> {

    public List<MainRates> getRates(Date startD, Date endD);

    public MainRates getLastRates(String property);

    public void saveOrUpdate(MainRates mainRates);

}
