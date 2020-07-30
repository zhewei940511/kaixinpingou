package com.laojiashop.laojia.entity;

public class UserInfoBean {
    private String phone;
    private String name;
    private Number score;
    private Number coin;
    private String level_txt;
    private String headimgurl;
    public Bonus bonus;
    public static class Bonus
    {
        public Number getNow() {
            return now;
        }

        public void setNow(Number now) {
            this.now = now;
        }

        private Number now;

    }
    public Number getScore() {
        return score;
    }

    public void setScore(Number score) {
        this.score = score;
    }

    public Number getCoin() {
        return coin;
    }

    public void setCoin(Number coin) {
        this.coin = coin;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLevel_txt() {
        return level_txt;
    }

    public void setLevel_txt(String level_txt) {
        this.level_txt = level_txt;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }


}
