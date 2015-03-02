package com.exchangerates.client.views.widgets;

import com.exchangerates.client.views.ScreenSizeChecker;
import com.exchangerates.shared.Constants;
import com.exchangerates.shared.dto.BankRatesDto;
import com.exchangerates.shared.dto.SingleRatesDto;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.*;

import java.util.Date;
import java.util.List;


public class BanksStackLayoutPanel extends ResizeComposite {
    private StackLayoutPanel panel;
    private final double HEADER_SIZE = 30;

    public BanksStackLayoutPanel() {
        panel = new StackLayoutPanel(Style.Unit.PX);

        initWidget(panel);
        setStyle();
        init();
    }

    private void init() {
        createBanksList();
        panel.forceLayout();
    }

    private void setStyle() {
        panel.setHeight(ScreenSizeChecker.getScreenSizeChecker().getStackLayoutPanelSize());
    }

    private void createBanksList() {
        for(int i = 0; i < Constants.BANKS_COUNT; i++) {
            SimplePanel simplePanel = new SimplePanel();
            CurrencyTable table = new CurrencyTable();
            simplePanel.add(table);

            BankWidget bank = new BankWidget();
            bank.setIcon("b" + i);

            bank.initParams();

            panel.add(simplePanel, bank, HEADER_SIZE);
        }
    }

    public void setCurrencyRates(List<SingleRatesDto> rates) {
        for(int i = 0; i < rates.size(); i++) {
            BankWidget bank = (BankWidget)panel.getHeaderWidget(i);
            bank.setRates(rates.get(i).getName(), rates.get(i).getBuying(), rates.get(i).getCelling());
            DateTimeFormat dateFormat = DateTimeFormat.getFormat("HH:mm");
            bank.setDate(dateFormat.format(new Date()) + "");
        }
    }

    public void fillBanksTables(List<BankRatesDto> rates) {
        for (int i = 0; i < rates.size(); i++) {
            SimplePanel simple = (SimplePanel) panel.getWidget(i);
            CurrencyTable table = (CurrencyTable) simple.getWidget();
            table.setRates(rates.get(i));
        }
    }
}
