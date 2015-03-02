package com.exchangerates.shared.model;


import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mainRates")
public class MainRates implements IsSerializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column
    private double averageUsdBuying;
    @Column
    private double averageUsdCelling;
    @Column
    private double averageEurBuying;
    @Column
    private double averageEurCelling;
    @Column
    private double averageRubBuying;
    @Column
    private double averageRubCelling;
    @Column
    private double blackMarketUsd;
    @Column
    private double blackMarketEur;
    @Column
    private double blackMarketRub;
    @Column
    private double nbuUsd;
    @Column
    private double nbuEur;
    @Column
    private double nbuRub;
    @Column
    private Date date;

    public MainRates() {}

    public MainRates(double averageUsdBuying, double averageUsdCelling, double averageEurBuying,
                     double averageEurCelling, double averageRubBuying, double averageRubCelling,
                     double blackMarketUsd, double blackMarketEur, double blackMarketRub, double nbuUsd,
                     double nbuEur, double nbuRub) {

        this.averageUsdBuying = averageUsdBuying;
        this.averageUsdCelling = averageUsdCelling;
        this.averageEurBuying = averageEurBuying;
        this.averageEurCelling = averageEurCelling;
        this.averageRubBuying = averageRubBuying;
        this.averageRubCelling = averageRubCelling;
        this.blackMarketUsd = blackMarketUsd;
        this.blackMarketEur = blackMarketEur;
        this.blackMarketRub = blackMarketRub;
        this.nbuUsd = nbuUsd;
        this.nbuEur = nbuEur;
        this.nbuRub = nbuRub;
    }

    public int getId() {
        return id;
    }

    public double getAverageUsdBuying() {
        return averageUsdBuying;
    }

    public void setAverageUsdBuying(double averageUsdBuying) {
        this.averageUsdBuying = averageUsdBuying;
    }

    public double getAverageUsdCelling() {
        return averageUsdCelling;
    }

    public void setAverageUsdCelling(double averageUsdCelling) {
        this.averageUsdCelling = averageUsdCelling;
    }

    public double getAverageEurBuying() {
        return averageEurBuying;
    }

    public void setAverageEurBuying(double averageEurBuying) {
        this.averageEurBuying = averageEurBuying;
    }

    public double getAverageEurCelling() {
        return averageEurCelling;
    }

    public void setAverageEurCelling(double averageEurCelling) {
        this.averageEurCelling = averageEurCelling;
    }

    public double getAverageRubBuying() {
        return averageRubBuying;
    }

    public void setAverageRubBuying(double averageRubBuying) {
        this.averageRubBuying = averageRubBuying;
    }

    public double getAverageRubCelling() {
        return averageRubCelling;
    }

    public void setAverageRubCelling(double averageRubCelling) {
        this.averageRubCelling = averageRubCelling;
    }

    public double getBlackMarketUsd() {
        return blackMarketUsd;
    }

    public void setBlackMarketUsd(double blackMarketUsd) {
        this.blackMarketUsd = blackMarketUsd;
    }

    public double getBlackMarketEur() {
        return blackMarketEur;
    }

    public void setBlackMarketEur(double blackMarketEur) {
        this.blackMarketEur = blackMarketEur;
    }

    public double getBlackMarketRub() {
        return blackMarketRub;
    }

    public void setBlackMarketRub(double blackMarketRub) {
        this.blackMarketRub = blackMarketRub;
    }

    public double getNbuUsd() {
        return nbuUsd;
    }

    public void setNbuUsd(double nbuUsd) {
        this.nbuUsd = nbuUsd;
    }

    public double getNbuEur() {
        return nbuEur;
    }

    public void setNbuEur(double nbuEur) {
        this.nbuEur = nbuEur;
    }

    public double getNbuRub() {
        return nbuRub;
    }

    public void setNbuRub(double nbuRub) {
        this.nbuRub = nbuRub;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addAverageRates(Rates average){
        setAverageUsdBuying(average.getUsdBuying());
        setAverageUsdCelling(average.getUsdCelling());
        setAverageEurBuying(average.getEurBuying());
        setAverageEurCelling(average.getEurCelling());
        setAverageRubBuying(average.getRubBuying());
        setAverageRubCelling(average.getRubCelling());
    }
    public void addBlackMarketRates(Rates blackMarketRates){
        setBlackMarketUsd(blackMarketRates.getUsdBuying());
        setBlackMarketEur(blackMarketRates.getEurBuying());
        setBlackMarketRub(blackMarketRates.getRubBuying());
    }
    public void addNbuRates(Rates nbuRates){
        setNbuUsd(nbuRates.getUsdBuying());
        setNbuEur(nbuRates.getEurBuying());
        setNbuRub(nbuRates.getRubBuying());
    }
}
