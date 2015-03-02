package com.exchangerates.client.views.widgets;


import com.google.gwt.user.client.ui.*;

public class BankWidget extends Composite {
    private HorizontalPanel panel = new HorizontalPanel();
    private SimplePanel imageSimplePanel = new SimplePanel();
    private SimplePanel nameSimplePanel = new SimplePanel();
    private SimplePanel timeSimplePanel = new SimplePanel();
    private SimplePanel buyingSimplePanel = new SimplePanel();
    private SimplePanel cellingSimplePanel = new SimplePanel();
    private Label bankName = new Label();
    private Label date = new Label();
    private Label buying = new Label();
    private Label celling = new Label();
    private Image image = new Image();

    public BankWidget() {
        initWidget(panel);
        setStyle();
    }

    public void setRates(String name, String buyingRate, String cellingRate) {
        bankName.setText(name);
        buying.setText(buyingRate);
        celling.setText(cellingRate);
    }

    public void setDate(String updateTime) {
        date.setText(updateTime);
    }

    public void initParams() {
        panel.add(imageSimplePanel);
        panel.add(nameSimplePanel);
        panel.add(timeSimplePanel);
        panel.add(buyingSimplePanel);
        panel.add(cellingSimplePanel);
        imageSimplePanel.add(image);
        nameSimplePanel.add(bankName);
        timeSimplePanel.add(date);
        buyingSimplePanel.add(buying);
        cellingSimplePanel.add(celling);
    }

    public void setIcon(String imageName) {
        image = new Image("/images/bankIcons/" + imageName + ".jpg");
        image.setSize("25px", "25px");
    }

    private void setStyle() {
        bankName.setWidth("200px");
        imageSimplePanel.addStyleName("imageSimplePanel");
        nameSimplePanel.addStyleName("nameSimplePanel");
        timeSimplePanel.addStyleName("timeSimplePanel");
        buyingSimplePanel.addStyleName("buyingSimplePanel");
        cellingSimplePanel.addStyleName("cellingSimplePanel");
        panel.addStyleName("banksPanel");
    }
}
