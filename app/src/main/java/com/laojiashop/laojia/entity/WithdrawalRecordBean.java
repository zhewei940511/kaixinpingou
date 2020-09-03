package com.laojiashop.laojia.entity;

import java.util.List;

public class WithdrawalRecordBean {

    /**
     * total : 13
     * per_page : 10
     * current_page : 1
     * last_page : 2
     * data : [{"id":2411,"order_no":"T200815978231576481","user_id":59461,"pay":"100.00","status":3,"reason":"","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-19 15:45:57","update_time":"2020-08-19 15:45:57","act_user":{"name":"系统"},"act_time":"--","service":"1.00","act_pay":"99.00","send_times":0,"status_txt":"待打款","imgs":[]},{"id":2409,"order_no":"T200815978021274617","user_id":59461,"pay":"5000.00","status":2,"reason":"是","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-19 09:55:27","update_time":"2020-08-19 09:55:27","act_user":{"name":"管理员","phone":"18901010101"},"act_time":"2020-08-19 16:23:05","service":"30.00","act_pay":"4970.00","send_times":0,"status_txt":"已驳回","imgs":[]},{"id":2408,"order_no":"T200815978018605332","user_id":59461,"pay":"5000.00","status":2,"reason":"大方","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-19 09:51:00","update_time":"2020-08-19 09:51:00","act_user":{"name":"管理员","phone":"18901010101"},"act_time":"2020-08-19 16:23:05","service":"30.00","act_pay":"4970.00","send_times":0,"status_txt":"已驳回","imgs":[]},{"id":2407,"order_no":"T200815978018525044","user_id":59461,"pay":"200.00","status":3,"reason":"","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-19 09:50:52","update_time":"2020-08-19 09:50:52","act_user":{"name":"系统"},"act_time":"--","service":"1.20","act_pay":"198.80","send_times":0,"status_txt":"待打款","imgs":[]},{"id":2406,"order_no":"T200815977999696052","user_id":59461,"pay":"5000.00","status":2,"reason":"大多数","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-19 09:19:29","update_time":"2020-08-19 09:19:29","act_user":{"name":"管理员","phone":"18901010101"},"act_time":"2020-08-19 16:23:05","service":"30.00","act_pay":"4970.00","send_times":0,"status_txt":"已驳回","imgs":[]},{"id":2405,"order_no":"T200815977406152154","user_id":59461,"pay":"600.00","status":2,"reason":"nono","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-18 16:50:15","update_time":"2020-08-18 16:50:15","act_user":{"name":"管理员","phone":"18901010101"},"act_time":"2020-08-19 16:23:05","service":"3.60","act_pay":"596.40","send_times":0,"status_txt":"已驳回","imgs":[]},{"id":2404,"order_no":"T200815977403253455","user_id":59461,"pay":"200.00","status":3,"reason":"","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-18 16:45:25","update_time":"2020-08-18 16:45:25","act_user":{"name":"系统"},"act_time":"--","service":"1.20","act_pay":"198.80","send_times":0,"status_txt":"待打款","imgs":[]},{"id":2402,"order_no":"T200815977166772338","user_id":59461,"pay":"100.00","status":3,"reason":"","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-18 10:11:17","update_time":"2020-08-18 10:11:17","act_user":{"name":"系统"},"act_time":"--","service":"1.00","act_pay":"99.00","send_times":0,"status_txt":"待打款","imgs":[]},{"id":2401,"order_no":"T200815977158607710","user_id":59461,"pay":"200.00","status":3,"reason":"","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-18 09:57:40","update_time":"2020-08-18 09:57:40","act_user":{"name":"系统"},"act_time":"--","service":"1.20","act_pay":"198.80","send_times":0,"status_txt":"待打款","imgs":[]},{"id":2400,"order_no":"T200815977153177852","user_id":59461,"pay":"100.00","status":3,"reason":"","wx_payment_no":"","wx_payment_time":"","wx_text":"","create_time":"2020-08-18 09:48:37","update_time":"2020-08-18 09:48:37","act_user":{"name":"系统"},"act_time":"--","service":"1.00","act_pay":"99.00","send_times":0,"status_txt":"待打款","imgs":[]}]
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
         * id : 2411
         * order_no : T200815978231576481
         * user_id : 59461
         * pay : 100.00
         * status : 3
         * reason :
         * wx_payment_no :
         * wx_payment_time :
         * wx_text :
         * create_time : 2020-08-19 15:45:57
         * update_time : 2020-08-19 15:45:57
         * act_user : {"name":"系统"}
         * act_time : --
         * service : 1.00
         * act_pay : 99.00
         * send_times : 0
         * status_txt : 待打款
         * imgs : []
         */

        private int id;
        private String order_no;
        private int user_id;
        private String pay;
        private int status;
        private String reason;
        private String wx_payment_no;
        private String wx_payment_time;
        private String wx_text;
        private String create_time;
        private String update_time;
        private ActUserBean act_user;
        private String act_time;
        private String service;
        private String act_pay;
        private int send_times;
        private String status_txt;
        private List<?> imgs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getWx_payment_no() {
            return wx_payment_no;
        }

        public void setWx_payment_no(String wx_payment_no) {
            this.wx_payment_no = wx_payment_no;
        }

        public String getWx_payment_time() {
            return wx_payment_time;
        }

        public void setWx_payment_time(String wx_payment_time) {
            this.wx_payment_time = wx_payment_time;
        }

        public String getWx_text() {
            return wx_text;
        }

        public void setWx_text(String wx_text) {
            this.wx_text = wx_text;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public ActUserBean getAct_user() {
            return act_user;
        }

        public void setAct_user(ActUserBean act_user) {
            this.act_user = act_user;
        }

        public String getAct_time() {
            return act_time;
        }

        public void setAct_time(String act_time) {
            this.act_time = act_time;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getAct_pay() {
            return act_pay;
        }

        public void setAct_pay(String act_pay) {
            this.act_pay = act_pay;
        }

        public int getSend_times() {
            return send_times;
        }

        public void setSend_times(int send_times) {
            this.send_times = send_times;
        }

        public String getStatus_txt() {
            return status_txt;
        }

        public void setStatus_txt(String status_txt) {
            this.status_txt = status_txt;
        }

        public List<?> getImgs() {
            return imgs;
        }

        public void setImgs(List<?> imgs) {
            this.imgs = imgs;
        }

        public static class ActUserBean {
            /**
             * name : 系统
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
