package com.laojiashop.laojia.entity;

public class MyCoinInfoBean {

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFreeze_coin() {
        return freeze_coin;
    }

    public void setFreeze_coin(String freeze_coin) {
        this.freeze_coin = freeze_coin;
    }

    public double getCoin() {
        return coin;
    }

    public void setCoin(double coin) {
        this.coin = coin;
    }

    /**
     * total : 22,063.65
     * list : [{"title":"拼团奖励","today":0,"week":0,"month":0},{"title":"导购奖励","today":0,"week":0,"month":0},{"title":"培育奖励","today":0,"week":0,"month":0},{"title":"团队奖","today":0,"week":0,"month":0}]
     * freeze_coin : 6000.00
     * coin : 39922.59
     */

    private String total;
    private String freeze_coin;
    private double coin;
}
