package com.exchangerates.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.*;

@Entity
@Table(name = "banksInfo")
public class BankInfo implements IsSerializable {
    @Id
    @Column
    private String bankName;
    @Column
    private String url;
    @Column
    private String mainRegexp;
    @Column
    private String replaceRegexp;

    public BankInfo(){}

    public BankInfo(String name, String url) {
        this(name, url, "", "");
    }

    public BankInfo(String name, String url, String mainRegexp, String replaceRegexp) {
        this.bankName = name;
        this.url = url;
        this.mainRegexp = mainRegexp;
        this.replaceRegexp = replaceRegexp;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMainRegexp() {
        return mainRegexp;
    }

    public void setMainRegexp(String mainRegexp) {
        this.mainRegexp = mainRegexp;
    }

    public String getReplaceRegexp() {
        return replaceRegexp;
    }

    public void setReplaceRegexp(String replaceRegexp) {
        this.replaceRegexp = replaceRegexp;
    }
}
