package com.exchangerates.server.dao;


import com.exchangerates.shared.model.Rates;

import java.util.Map;

public interface BanksRatesDao extends BasicCrudDao<Rates> {
    public void update(Map<String, Rates> values);
}
