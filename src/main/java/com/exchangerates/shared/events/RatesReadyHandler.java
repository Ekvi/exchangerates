package com.exchangerates.shared.events;

import com.google.gwt.event.shared.EventHandler;


public interface RatesReadyHandler extends EventHandler {
    void onRatesReceived(RatesReadyEvent event);
}
