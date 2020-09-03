package com.laojiashop.laojia.entity;

import java.util.List;

public class FeedbackconditionsBean {

    /**
     * total : 4
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":1,"typename":"订单","need_relation":1,"imgs":[]},{"id":2,"typename":"咨询","need_relation":0,"imgs":[]},{"id":3,"typename":"合作","need_relation":0,"imgs":[]},{"id":4,"typename":"建议反馈","need_relation":0,"imgs":[]}]
     */

    private int total;
    private int per_page;
    private String current_page;
    private int last_page;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * typename : 订单
         * need_relation : 1
         * imgs : []
         */

        private int id;
        private String typename;
        private int need_relation;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getNeed_relation() {
            return need_relation;
        }

        public void setNeed_relation(int need_relation) {
            this.need_relation = need_relation;
        }
    }
}
