package com.exchangerates.server.dao.impl;

import com.exchangerates.server.dao.MainRatesDao;

import com.exchangerates.shared.model.MainRates;

import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;


public class MainRatesDaoImpl extends AbstractHibernateDAO<MainRates> implements MainRatesDao {
    public MainRatesDaoImpl(Class<MainRates> entityClass) {
        super(entityClass);
    }

    @Override
    public List<MainRates> getRates(Date startD, Date endD) {
        Transaction transaction = null;
        List<MainRates> data = null;
        try {
            transaction = session().beginTransaction();
            data = session().createCriteria(entityClass).add(Restrictions.between("date", startD, endD)).list();
            transaction.commit();
        } catch(RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if(session().isOpen()) {
                session().close();
            }
        }
        return data;
    }

    @Override
    public MainRates getLastRates(String property) {
        Transaction transaction = null;
        MainRates mainRates = null;
        try {
            transaction = session().beginTransaction();

            int maxId = (Integer)session().createCriteria(entityClass)
                        .setProjection(Projections.max(property))
                        .uniqueResult();
            mainRates = (MainRates)session().createCriteria(entityClass)
                        .add(Restrictions.eq(property, maxId))
                        .uniqueResult();

            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session().isOpen()) {
                session().close();
            }
        }
        return mainRates;
    }

    @Override
    public void saveOrUpdate(MainRates rates) {
        Transaction transaction = null;
        try {
            transaction = session().beginTransaction();

            MainRates oldRates = (MainRates)session().createCriteria(entityClass)
                                .add(Restrictions.eq("date", rates.getDate()))
                                .uniqueResult();

            if(oldRates == null) {
                session().save(rates);
            } else {
                oldRates.setAverageUsdBuying(rates.getAverageUsdBuying());
                oldRates.setAverageEurBuying(rates.getAverageEurBuying());
                oldRates.setAverageRubBuying(rates.getAverageRubBuying());
                oldRates.setAverageUsdCelling(rates.getAverageUsdCelling());
                oldRates.setAverageEurCelling(rates.getAverageEurCelling());
                oldRates.setAverageRubCelling(rates.getAverageRubCelling());
                oldRates.setBlackMarketUsd(rates.getBlackMarketUsd());
                oldRates.setBlackMarketEur(rates.getBlackMarketEur());
                oldRates.setBlackMarketRub(rates.getBlackMarketRub());
                oldRates.setNbuUsd(rates.getNbuUsd());
                oldRates.setNbuEur(rates.getNbuEur());
                oldRates.setNbuRub(rates.getNbuRub());

                session().update(oldRates);
            }

            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session().isOpen()) {
                session().close();
            }
        }
    }
}
