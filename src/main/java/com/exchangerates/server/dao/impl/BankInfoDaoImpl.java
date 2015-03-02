package com.exchangerates.server.dao.impl;


import com.exchangerates.server.dao.BankInfoDao;
import com.exchangerates.shared.model.BankInfo;

public class BankInfoDaoImpl extends AbstractHibernateDAO<BankInfo> implements BankInfoDao {
    public BankInfoDaoImpl(Class<BankInfo> entityClass) {
        super(entityClass);
    }
}
