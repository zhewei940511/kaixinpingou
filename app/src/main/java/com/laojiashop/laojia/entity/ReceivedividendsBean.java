package com.laojiashop.laojia.entity;

import java.util.List;

/**
 * 分红权实体类
 */
public class ReceivedividendsBean {

    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":2026754,"user_id":59461,"num":1,"start_time":1591632000,"end_time":1623254400,"next_time":1591113600,"create_time":"2020-06-02 09:23:48","remark":"兑换分红权","send_now":0,"send_count":52,"status":3,"score":2000,"status_txt":"已退出","stime":"2020-06-09 00:00:00","etime":"2021-06-10 00:00:00","ntime":"2020-06-03","per_bonus":0},{"id":2026752,"user_id":59461,"num":5,"start_time":1591545600,"end_time":1623168000,"next_time":1591718400,"create_time":"2020-06-01 15:01:40","remark":"兑换分红权","send_now":1,"send_count":52,"status":1,"score":10000,"status_txt":"生效中","stime":"2020-06-08 00:00:00","etime":"2021-06-09 00:00:00","ntime":"2020-06-10","per_bonus":0}]
     */

    private int total;
    private String per_page;
    private String current_page;
    private int last_page;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
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
         * id : 2026754
         * user_id : 59461
         * num : 1
         * start_time : 1591632000
         * end_time : 1623254400
         * next_time : 1591113600
         * create_time : 2020-06-02 09:23:48
         * remark : 兑换分红权
         * send_now : 0
         * send_count : 52
         * status : 3
         * score : 2000
         * status_txt : 已退出
         * stime : 2020-06-09 00:00:00
         * etime : 2021-06-10 00:00:00
         * ntime : 2020-06-03
         * per_bonus : 0
         */

        private int id;
        private int user_id;
        private int num;
        private int start_time;
        private int end_time;
        private int next_time;
        private String create_time;
        private String remark;
        private int send_now;
        private int send_count;
        private int status;
        private int score;
        private String status_txt;
        private String stime;
        private String etime;
        private String ntime;
        private int per_bonus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public int getNext_time() {
            return next_time;
        }

        public void setNext_time(int next_time) {
            this.next_time = next_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSend_now() {
            return send_now;
        }

        public void setSend_now(int send_now) {
            this.send_now = send_now;
        }

        public int getSend_count() {
            return send_count;
        }

        public void setSend_count(int send_count) {
            this.send_count = send_count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getStatus_txt() {
            return status_txt;
        }

        public void setStatus_txt(String status_txt) {
            this.status_txt = status_txt;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getNtime() {
            return ntime;
        }

        public void setNtime(String ntime) {
            this.ntime = ntime;
        }

        public int getPer_bonus() {
            return per_bonus;
        }

        public void setPer_bonus(int per_bonus) {
            this.per_bonus = per_bonus;
        }
    }
}
