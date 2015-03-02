package com.exchangerates.client.presenters;


import com.exchangerates.client.services.RatesService;
import com.exchangerates.client.services.RatesServiceAsync;
import com.exchangerates.shared.dto.AllRates;
import com.exchangerates.shared.events.RatesReadyEvent;
import com.exchangerates.shared.events.RatesReadyHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;


public class RatesPresenter implements Presenter {
    private Display view;
    private RatesServiceAsync service = GWT.create(RatesService.class);
    private SimpleEventBus eventBus;

    public interface Display {
        public Widget asWidget();
        public void setPresenter(RatesPresenter presenter);
        public void setAllRates(AllRates allRates);
    }

    public RatesPresenter(Display view, SimpleEventBus eventBus) {
        this.view = view;
        this.eventBus = eventBus;

        bind();

        RatesReadyEvent.register(eventBus, new RatesReadyHandler() {
            @Override
            public void onRatesReceived(RatesReadyEvent event) {
                display(RootLayoutPanel.get());
            }
        });
    }

    @Override
    public void bind() {
        view.setPresenter(this);

        service.getAllRates(new AsyncCallback<AllRates>() {
            @Override
            public void onFailure(Throwable throwable) {
            }

            @Override
            public void onSuccess(AllRates allRates) {
                view.setAllRates(allRates);
                eventBus.fireEvent(new RatesReadyEvent());
            }
        });
    }

    @Override
    public void display(Panel panel) {
        panel.add(view.asWidget());
    }
}
