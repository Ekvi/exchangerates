package com.exchangerates.shared.model;


import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "banksRates")
public class Rates implements IsSerializable, Comparable<Rates> {
    @Id
    @Column
    private String name;
    @Column
    private double usdBuying;
    @Column
    private double usdCelling;
    @Column
    private double eurBuying;
    @Column
    private double eurCelling;
    @Column
    private double rubBuying;
    @Column
    private double rubCelling;
    @Column
    private Date date;

    public Rates(){}

    public Rates(String name, double usdBuying, double usdCelling,
                 double eurBuying, double eurCelling, double rubBuying, double rubCelling){
        this.name = name;
        this.usdBuying = usdBuying;
        this.usdCelling = usdCelling;
        this.eurBuying = eurBuying;
        this.eurCelling = eurCelling;
        this.rubBuying = rubBuying;
        this.rubCelling = rubCelling;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUsdBuying() {
        return usdBuying;
    }

    public void setUsdBuying(double usdBuying) {
        this.usdBuying = usdBuying;
    }

    public double getUsdCelling() {
        return usdCelling;
    }

    public void setUsdCelling(double usdCelling) {
        this.usdCelling = usdCelling;
    }

    public double getEurBuying() {
        return eurBuying;
    }

    public void setEurBuying(double eurBuying) {
        this.eurBuying = eurBuying;
    }

    public double getEurCelling() {
        return eurCelling;
    }

    public void setEurCelling(double eurCelling) {
        this.eurCelling = eurCelling;
    }

    public double getRubBuying() {
        return rubBuying;
    }

    public void setRubBuying(double rubBuying) {
        this.rubBuying = rubBuying;
    }

    public double getRubCelling() {
        return rubCelling;
    }

    public void setRubCelling(double rubCelling) {
        this.rubCelling = rubCelling;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Rates rates) {
        return this.getName().compareToIgnoreCase(rates.getName());
    }
}
