package com.exchangerates.client.views.widgets;


import com.exchangerates.shared.dto.BankRatesDto;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;


public class CurrencyTable extends Composite {
    private FlexTable currencyTable = new FlexTable();

    public CurrencyTable() {
        initWidget(this.currencyTable);

        initTable();
    }

    private void initTable() {
        currencyTable.setText(0, 0, "Валюта");
        currencyTable.setText(0, 1, "Покупка");
        currencyTable.setText(0, 2, "Продажа");

        currencyTable.getRowFormatter().addStyleName(0, "watchListHeader");
        currencyTable.addStyleName("watchList");
        currencyTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        currencyTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");

        currencyTable.setText(1, 0, "USD");
        currencyTable.setText(2, 0, "EUR");
        currencyTable.setText(3, 0, "RUB");
    }

    public void setRates(BankRatesDto rates) {
        currencyTable.setText(1, 1, rates.getUsdBuying());
        currencyTable.setText(1, 2, rates.getUsdCelling());
        currencyTable.setText(2, 1, rates.getEurBuying());
        currencyTable.setText(2, 2, rates.getEurCelling());
        currencyTable.setText(3, 1, rates.getRubBuying());
        currencyTable.setText(3, 2, rates.getRubCelling());
    }
}
