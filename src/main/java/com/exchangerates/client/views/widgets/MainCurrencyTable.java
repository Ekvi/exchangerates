package com.exchangerates.client.views.widgets;


import com.exchangerates.shared.dto.MainRatesDto;
import com.google.gwt.user.client.ui.*;

import java.util.List;


public class MainCurrencyTable extends Composite {
    private final int WIDTH = 4;
    private final int HEIGHT = 3;
    private FlexTable currencyTable = new FlexTable();


    public MainCurrencyTable() {
        initWidget(this.currencyTable);

        initTable();
    }

   private void initTable() {
       currencyTable.addStyleName("mainCurrencyTable");
       currencyTable.setText(0, 0, "Валюта");
       currencyTable.setText(0, 1, "Покупка");
       currencyTable.setText(0, 2, "Продажа");
       currencyTable.setText(0, 3, "Коммерческий курс");
       currencyTable.setText(0, 4, "Курс НБУ");

       currencyTable.getRowFormatter().addStyleName(0, "watchListHeader");
       currencyTable.addStyleName("watchList");
       currencyTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
       currencyTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
       currencyTable.getCellFormatter().addStyleName(0, 3, "watchListNumericColumn");
       currencyTable.getCellFormatter().addStyleName(0, 4, "watchListNumericColumn");

       currencyTable.setText(1, 0, "USD");
       currencyTable.setText(2, 0, "EUR");
       currencyTable.setText(3, 0, "RUB");

       initPanels();
   }

    private void initPanels() {
        for(int i = 1; i <= WIDTH; i++) {
            for(int j = 1; j <= HEIGHT; j++) {
                HorizontalPanel panel = new HorizontalPanel();
                panel.addStyleName("panelInsideTable");

                Label rate = new Label();
                rate.setWidth("50px");
                Label difference = new Label();

                Image image = new Image();
                image.setPixelSize(9, 9);
                image.setVisible(false);
                image.addStyleName("ratesDifferenceIm");

                panel.add(rate);
                panel.add(difference);
                panel.add(image);

                currencyTable.setWidget(j, i, panel);
            }
        }
    }


    public void updateRates(List<MainRatesDto> rates) {
        int count = 0;

        for(int i = 1; i <= WIDTH; i++) {
            for (int j = 1; j <= HEIGHT; j++) {
                HorizontalPanel panel = (HorizontalPanel)currencyTable.getWidget(j, i);
                Label r = (Label)panel.getWidget(0);
                Label d = (Label)panel.getWidget(1);
                Image image = (Image)panel.getWidget(2);

                r.setText(rates.get(count).getRate());
                d.setText(rates.get(count).getDifference());

                if(!rates.get(count).getImageName().isEmpty()) {
                    image.setUrl("/images/" + rates.get(count).getImageName() + ".png");
                    image.setVisible(true);

                    String style = rates.get(count).getImageName().equals("up") ? "differenceUp" : "differenceDown";
                    d.addStyleName(style);
                }
                else {
                    image.setVisible(false);
                }
                count++;
            }
        }
    }
}
