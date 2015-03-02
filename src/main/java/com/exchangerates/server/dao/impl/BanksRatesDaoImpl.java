package com.exchangerates.server.dao.impl;


import com.exchangerates.server.dao.BanksRatesDao;
import com.exchangerates.shared.model.Rates;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

public class BanksRatesDaoImpl extends AbstractHibernateDAO<Rates> implements BanksRatesDao {
    public BanksRatesDaoImpl(Class<Rates> entityClass) {
        super(entityClass);
    }

    @Override
    public void update(Map<String, Rates> values) {
        Transaction transaction = null;
        try {
            transaction = session().beginTransaction();

            List<Rates> list = session().createCriteria(entityClass).list();

            for(Rates rates : list) {
                String name = rates.getName();
                Rates newRates = values.get(name);
                if(newRates != null) {
                    rates.setUsdBuying(newRates.getUsdBuying());
                    rates.setEurBuying(newRates.getEurBuying());
                    rates.setRubBuying(newRates.getRubBuying());
                    rates.setUsdCelling(newRates.getUsdCelling());
                    rates.setEurCelling(newRates.getEurCelling());
                    rates.setRubCelling(newRates.getRubCelling());
                    rates.setDate(newRates.getDate());
                    session().update(rates);
                }
            }
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if(session().isOpen()) {
                session().close();
            }
        }
    }
}
