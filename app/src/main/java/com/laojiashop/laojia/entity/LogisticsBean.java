package com.laojiashop.laojia.entity;

public class LogisticsBean {
    /** 时间 */
    private String acceptTime;

    public LogisticsBean(String acceptTime, String acceptStation) {
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getAcceptStation() {
        return acceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        this.acceptStation = acceptStation;
    }

    /** 描述 */
    private String acceptStation;
}
