package com.exchangerates.client.views.widgets;


import com.exchangerates.client.views.ScreenSizeChecker;
import com.exchangerates.shared.Constants;
import com.exchangerates.shared.model.MainRates;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;

import java.util.ArrayList;
import java.util.List;

public class RatesGraphicWidget extends VerticalPanel {
    private List<MainRates> rates = new ArrayList<MainRates>();
    private List<String> dates = new ArrayList<String>();

    public void createGraphic() {
        VisualizationUtils.loadVisualizationApi(new Runnable() {
            public void run() {
                LineChart lineChart = new LineChart(createTableUsd(), createOptions("Динамика изменений доллара"));
                lineChart.addStyleName("lineChart");
                add(lineChart);
            }
        }, LineChart.PACKAGE);

        VisualizationUtils.loadVisualizationApi(new Runnable() {
            public void run() {
                LineChart lineChart = new LineChart(createTableEur(), createOptions("Динамика изменений евро"));
                lineChart.addStyleName("lineChart");
                add(lineChart);
            }
        }, LineChart.PACKAGE);

        VisualizationUtils.loadVisualizationApi(new Runnable() {
            public void run() {
                LineChart lineChart = new LineChart(createTableRub(), createOptions("Динамика изменений рубля"));
                lineChart.addStyleName("lineChart");
                add(lineChart);
            }
        }, LineChart.PACKAGE);
    }

    private AbstractDataTable createTableUsd() {
        DataTable data = DataTable.create();
        data.addColumn(AbstractDataTable.ColumnType.STRING, "Date");
        data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Среднебанковский курс");
        data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Коммерческий курс");
        data.addRows(Constants.DAYS_FOR_GRAPHIC);

        for (int i = 0; i < rates.size(); i++) {
            data.setValue(i, 0, dates.get(i));
            data.setValue(i, 1, rates.get(i).getAverageUsdCelling());
            data.setValue(i, 2, rates.get(i).getBlackMarketUsd());
        }
        return data;
    }

    private AbstractDataTable createTableEur() {
        DataTable data = DataTable.create();
        data.addColumn(AbstractDataTable.ColumnType.STRING, "Date");
        data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Среднебанковский курс");
        data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Коммерческий курс");
        data.addRows(Constants.DAYS_FOR_GRAPHIC);

        for (int i = 0; i < rates.size(); i++) {
            data.setValue(i, 0, dates.get(i));
            data.setValue(i, 1, rates.get(i).getAverageEurCelling());
            data.setValue(i, 2, rates.get(i).getBlackMarketEur());
        }
        return data;
    }

    private AbstractDataTable createTableRub() {
        DataTable data = DataTable.create();
        data.addColumn(AbstractDataTable.ColumnType.STRING, "Date");
        data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Среднебанковский курс");
        data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Коммерческий курс");
        data.addRows(Constants.DAYS_FOR_GRAPHIC);

        for (int i = 0; i < rates.size(); i++) {
            data.setValue(i, 0, dates.get(i));
            data.setValue(i, 1, rates.get(i).getAverageRubCelling());
            data.setValue(i, 2, rates.get(i).getBlackMarketRub());
        }
        return data;
    }

    private Options createOptions(String title) {
        int width = ScreenSizeChecker.getScreenSizeChecker().getChartWidth();
        int height = ScreenSizeChecker.getScreenSizeChecker().getChartHeight();

        Options options = Options.create();
        options.setHeight(height);
        options.setWidth(width);
        options.setTitle(title);

        return options;
    }

    public void setGraphicRates(List<MainRates> rates) {
        this.rates = rates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}