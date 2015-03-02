package com.exchangerates.client.services;


import com.exchangerates.shared.dto.AllRates;
import com.exchangerates.shared.dto.BankRatesDto;
import com.exchangerates.shared.dto.MainRatesDto;
import com.exchangerates.shared.dto.SingleRatesDto;
import com.exchangerates.shared.model.BankInfo;
import com.exchangerates.shared.model.MainRates;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;
import java.util.List;


public interface RatesServiceAsync {

    void getAllRates(AsyncCallback<AllRates> async);

    void getSingleRates(AsyncCallback<List<SingleRatesDto>> async);

    void getSingleRates(String flag, AsyncCallback<List<SingleRatesDto>> async);

    void getOldMainRates(String date, AsyncCallback<List<MainRatesDto>> async);
}
