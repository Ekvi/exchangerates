package com.exchangerates.client.views.widgets;


import com.exchangerates.client.services.RatesService;
import com.exchangerates.client.services.RatesServiceAsync;
import com.exchangerates.shared.dto.MainRatesDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;

import java.util.Date;
import java.util.List;

public class TodayPanel extends Composite {
    private MainCurrencyTable table;
    private HorizontalPanel panel;
    private Label dateLabel;
    private PushButton calendarButton;
    private PopupPanel showCalendar;
    private DatePicker datePicker;
    private RatesServiceAsync ratesService = GWT.create(RatesService.class);
    private String defaultDateFormat = "dd MM yyyy";

    public TodayPanel(MainCurrencyTable table) {
        this.table = table;
        panel = new HorizontalPanel();
        initWidget(panel);

        initPanel();
        setUpEventHandling();
    }

    private void initPanel() {
        Date date = new Date();
        Label label = new Label();
        label.setText("Курс валют в Украине на " + dateFormat(date, defaultDateFormat));
        label.addStyleName("label");

        dateLabel = new Label(dateFormat(date, defaultDateFormat));
        dateLabel.addStyleName("todayDateButton");

        Image image = new Image("/images/calendar.jpg");
        image.setSize("80", "30");

        calendarButton = new PushButton(image);

        datePicker = new DatePicker();
        datePicker.setValue(date);
        datePicker.addStyleName("gwt-DatePicker");

        panel.add(label);
        panel.add(dateLabel);
        panel.add(calendarButton);
        panel.addStyleName("todayTitlePanel");
    }

    private String dateFormat(Date date, String format) {
        DateTimeFormat dateFormat = DateTimeFormat.getFormat(format);
        return dateFormat.format(date);
    }

    public void setUpEventHandling() {

        calendarButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                FlowPanel qAnswer;
                showCalendar = new PopupPanel();
                qAnswer = new FlowPanel();
                qAnswer.add(datePicker);

                showCalendar.add(qAnswer);
                showCalendar.setAnimationEnabled(true);
                showCalendar.showRelativeTo(calendarButton);
                showCalendar.setAutoHideEnabled(true);
                showCalendar.setAutoHideOnHistoryEventsEnabled(true);
            }
        });

        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                String date = dateFormat(event.getValue(), "dd M yyyy");
                dateLabel.setText(dateFormat(event.getValue(), defaultDateFormat));

                ratesService.getOldMainRates(date, new AsyncCallback<List<MainRatesDto>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                    }

                    @Override
                    public void onSuccess(List<MainRatesDto> result) {
                        if(!result.isEmpty()) {
                            table.updateRates(result);
                        }
                    }
                });
            }
        });
    }
}
