package com.exchangerates.client.services;


import com.exchangerates.shared.dto.AllRates;
import com.exchangerates.shared.dto.BankRatesDto;
import com.exchangerates.shared.dto.MainRatesDto;
import com.exchangerates.shared.dto.SingleRatesDto;
import com.exchangerates.shared.model.BankInfo;
import com.exchangerates.shared.model.MainRates;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Date;
import java.util.List;


@RemoteServiceRelativePath("ratesService")
public interface RatesService extends RemoteService {
    public AllRates getAllRates();

    public List<SingleRatesDto> getSingleRates();

    public List<SingleRatesDto> getSingleRates(String flag);

    public List<MainRatesDto> getOldMainRates(String date);
}
