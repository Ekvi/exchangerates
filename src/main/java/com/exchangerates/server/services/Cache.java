package com.exchangerates.server.services;


import com.exchangerates.shared.model.BankInfo;
import com.exchangerates.shared.model.MainRates;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private List<BankInfo> banks = new ArrayList<BankInfo>();
    private MainRates difference = new MainRates();

    private static Cache cache;

    private Cache(){}

    public static Cache getCache() {
        if(cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public List<BankInfo> getBanksInfo() {
        return banks;
    }

    public void addBanksInfo(List<BankInfo> banks) {
        this.banks = banks;
    }

    public MainRates getDifference() {
        return difference;
    }

    public void addDifference(MainRates difference) {
        this.difference = difference;
    }
}
