package com.laojiashop.laojia.entity;

import java.util.List;

public class PayWayBean {

    private List<PayWayListBean> payWayList;

    public List<PayWayListBean> getPayWayList() {
        return payWayList;
    }

    public void setPayWayList(List<PayWayListBean> payWayList) {
        this.payWayList = payWayList;
    }

    public static class PayWayListBean {
        /**
         * id : 1
         * payWayName : 微信
         * icon : http://static.laojia99.com/Uploads/image/20200803/0ed479c794979377e61d44e5e4e3a4b2.png
         * isDefault : 0
         */

        private int id;
        private String payWayName;
        private String icon;
        private int isDefault;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPayWayName() {
            return payWayName;
        }

        public void setPayWayName(String payWayName) {
            this.payWayName = payWayName;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }
    }
}
