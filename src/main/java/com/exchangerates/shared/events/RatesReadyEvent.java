package com.exchangerates.shared.events;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class RatesReadyEvent extends GwtEvent<RatesReadyHandler>{
    public static final Type<RatesReadyHandler> TYPE = new Type<RatesReadyHandler>();

    public static HandlerRegistration register(EventBus eventBus, RatesReadyHandler handler) {
        return eventBus.addHandler(TYPE, handler);
    }

    @Override
    public Type<RatesReadyHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RatesReadyHandler handler) {
        handler.onRatesReceived(this);
    }
}
