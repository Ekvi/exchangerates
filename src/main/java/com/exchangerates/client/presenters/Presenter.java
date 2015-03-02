package com.exchangerates.client.presenters;


import com.google.gwt.user.client.ui.Panel;

import java.util.Date;

public interface Presenter {
    public void bind();
    public void display(Panel panel);
    //public void updateMainCurrencyTable(Date date);
}
