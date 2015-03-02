package com.exchangerates.client;


import com.exchangerates.client.presenters.Presenter;
import com.exchangerates.client.presenters.RatesPresenter;
import com.exchangerates.client.views.RatesView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;


public class ExchangeRates implements EntryPoint {

    public void onModuleLoad() {
        SimpleEventBus eventBus = new SimpleEventBus();
        RatesPresenter.Display view = new RatesView();
        Presenter presenter = new RatesPresenter(view, eventBus);
    }
}
