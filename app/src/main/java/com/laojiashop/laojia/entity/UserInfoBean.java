package com.laojiashop.laojia.entity;

import java.util.List;

public class UserInfoBean {
    private String phone;
    private String name;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    private String score;
    private String coin;
    private String my_code;

    public List<VariousTypesBalancesListBean> getVariousTypesBalancesList() {
        return variousTypesBalancesList;
    }

    public void setVariousTypesBalancesList(List<VariousTypesBalancesListBean> variousTypesBalancesList) {
        this.variousTypesBalancesList = variousTypesBalancesList;
    }

    private List<VariousTypesBalancesListBean> variousTypesBalancesList;

    private Number freeze_coin;
    private String level_txt;
    private String headimgurl;


    public Bonus bonus;
    public static class Bonus {
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        private String intro;//分红权规则
        private String price;//比例
        private String now;//有效

        public String getNow() {
            return now;
        }

        public void setNow(String now) {
            this.now = now;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        private String count;//总数
        private String end;//结束

    }
    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public static class VariousTypesBalancesListBean {
        /**
         * type : 1
         * name : 开心积分
         * value : 20
         */

        private int type;
        private String name;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        private String value;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }





    public Number getFreeze_coin() {
        return freeze_coin;
    }

    public void setFreeze_coin(Number freeze_coin) {
        this.freeze_coin = freeze_coin;
    }

    public String getPhone() {
        return phone;
    }

    public String getMy_code() {
        return my_code;
    }

    public void setMy_code(String my_code) {
        this.my_code = my_code;
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
