package com.exchangerates.client.views.widgets;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class TitlePanel extends Composite {
    private HorizontalPanel panel = new HorizontalPanel();

    public TitlePanel() {
        initWidget(panel);
        init();
    }

    private void init() {
        Label name = new Label("Название банка");
        Label time = new Label("обновленно");
        Label buying = new Label("покупка");
        Label celling = new Label("продажа");

        name.addStyleName("titleBankName");
        time.addStyleName("titleUpdateTime");
        buying.addStyleName("titleBuying");
        celling.addStyleName("titleCelling");

        panel.add(name);
        panel.add(time);
        panel.add(buying);
        panel.add(celling);
        panel.addStyleName("titlePanel");
    }
}
