package com.exchangerates.client.views;


import com.exchangerates.client.presenters.RatesPresenter;
import com.exchangerates.client.views.widgets.*;
import com.exchangerates.shared.dto.AllRates;
import com.exchangerates.shared.dto.GraphicsRates;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;


public class RatesView extends Composite implements RatesPresenter.Display {
    private RatesPresenter presenter;
    private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.PCT);
    private MainCurrencyTable mainCurrencyTable = new MainCurrencyTable();
    private VerticalPanel mainRates = new VerticalPanel();
    private VerticalPanel banksRates = new VerticalPanel();
    private VerticalPanel graphicsPanel = new VerticalPanel();
    private VerticalPanel contentPanel = new VerticalPanel();
    private ScrollPanel scrollPanel = new ScrollPanel();
    private BanksStackLayoutPanel banksStack = new BanksStackLayoutPanel();
    private TodayPanel todayPanel = new TodayPanel(mainCurrencyTable);
    private BanksTitlePanel banksTitle = new BanksTitlePanel(banksStack);

    public RatesView() {
        initWidget(mainPanel);
        setUpGui();
    }

    @Override
    public void setPresenter(RatesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setAllRates(AllRates allRates) {
        mainCurrencyTable.updateRates(allRates.getMainRates());
        banksStack.fillBanksTables(allRates.getBankRates());
        banksStack.setCurrencyRates(allRates.getSingleRates());

        createGraphics(allRates.getGraphicsRates());
    }

    private void setUpGui()  {
        SimplePanel west = new SimplePanel();
        SimplePanel east = new SimplePanel();
        SimplePanel east2 = new SimplePanel();
        SimplePanel north = new SimplePanel();
        SimplePanel south = new SimplePanel();
        SimplePanel center = new SimplePanel();

        setUpHeader(north);
        setUpFooter(south);
        setUpMainRates();

        banksStack.addStyleName("stackPanel");
        setSizeForScrollPanel();
        scrollPanel.add(banksStack);

        setUpBankRates();
        setUpContentPanel();

        center.add(contentPanel);
        center.addStyleName("centerPanel");

        east2.add(graphicsPanel);

        north.addStyleName("north");
        south.addStyleName("south");
        west.addStyleName("west");
        east.addStyleName("east");
        east2.addStyleName("east2");

        mainPanel.addWest(west, 17);
        mainPanel.addEast(east, 17);
        mainPanel.addNorth(north, 5);
        mainPanel.addSouth(south, 5);
        mainPanel.addEast(east2, 20);
        mainPanel.add(center);
    }

    private void setUpHeader(SimplePanel north) {
        Label header = new Label("Актуальные курсы валют");
        header.addStyleName("headerLabel");
        north.add(header);
    }
    private void setUpFooter(SimplePanel south) {
        Label footer = new Label("© Курсы валют 2015");
        footer.addStyleName("footerLabel");
        south.add(footer);
    }

    private void setUpMainRates() {
        mainRates.addStyleName("mainRates");
        mainRates.add(todayPanel);
        mainRates.add(mainCurrencyTable);
    }

    private void setUpBankRates() {
        banksRates.addStyleName("banksRates");
        banksRates.add(banksTitle);
        banksRates.add(new TitlePanel());
        banksRates.add(scrollPanel);
    }

    private void setUpContentPanel() {
        contentPanel.add(mainRates);
        contentPanel.add(banksRates);
        contentPanel.addStyleName("contentPanel");
    }

    private void createGraphics(GraphicsRates graphicsRates) {
        RatesGraphicWidget ratesGraphic = new RatesGraphicWidget();

        ratesGraphic.setGraphicRates(graphicsRates.getRates());
        ratesGraphic.setDates(graphicsRates.getDates());
        graphicsPanel.add(ratesGraphic);
        ratesGraphic.createGraphic();
    }

    private void setSizeForScrollPanel() {
        scrollPanel.setHeight(ScreenSizeChecker.getScreenSizeChecker().getScrollPanelHeight());
    }
}
