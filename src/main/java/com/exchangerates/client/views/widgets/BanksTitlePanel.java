package com.exchangerates.client.views.widgets;


import com.exchangerates.client.services.RatesService;
import com.exchangerates.client.services.RatesServiceAsync;
import com.exchangerates.shared.Currency;
import com.exchangerates.shared.dto.SingleRatesDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import java.util.List;

public class BanksTitlePanel extends Composite {
    private HorizontalPanel panel;
    private BanksStackLayoutPanel bankPanel;

    public BanksTitlePanel(BanksStackLayoutPanel bankPanel) {
        this.bankPanel = bankPanel;
        panel = new HorizontalPanel();
        initWidget(panel);

        initPanel();
    }

    private void initPanel() {
        final Label label = new Label("Курсы валют в банках Украины");
        label.addStyleName("label");

        final ListBox currencyList = new ListBox();
        currencyList.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                RatesServiceAsync ratesService = GWT.create(RatesService.class);
                ratesService.getSingleRates(currencyList.getSelectedValue(), new AsyncCallback<List<SingleRatesDto>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                    }

                    @Override
                    public void onSuccess(List<SingleRatesDto> result) {
                         bankPanel.setCurrencyRates(result);
                    }
                });
            }
        });

        currencyList.addItem(Currency.USD.getName());
        currencyList.addItem(Currency.EUR.getName());
        currencyList.addItem(Currency.RUB.getName());

        currencyList.setVisibleItemCount(1);
        currencyList.addStyleName("currencyList");

        panel.add(label);
        panel.add(currencyList);
        panel.addStyleName("banksTitlePanel");
    }
}
