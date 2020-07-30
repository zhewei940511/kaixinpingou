package com.laojiashop.laojia.entity;

import java.util.List;

public class MeHappyListBean  {
        /**
         * total : 16
         * per_page : 10
         * current_page : 1
         * last_page : 2
         * data : [{"id":2036371,"type":42,"user_id":59461,"score":"1000.00","remark":"升级账户等级#导购","order_no":"0","create_time":"2020-06-10 17:47:27","type_txt":"系统扣除","tag_str":"-"},{"id":2036370,"type":8,"user_id":59461,"score":"200.00","remark":"驳回兑换第三方平台积分#1","order_no":"0","create_time":"2020-06-03 15:20:32","type_txt":"驳回兑换第三方平台积分","tag_str":" "},{"id":2036369,"type":44,"user_id":59461,"score":"200.00","remark":"兑换第三方平台积分#7","order_no":"0","create_time":"2020-06-03 14:43:35","type_txt":"兑换第三方平台积分","tag_str":"-"},{"id":2036368,"type":44,"user_id":59461,"score":"200.00","remark":"兑换第三方平台积分#6","order_no":"0","create_time":"2020-06-03 10:33:55","type_txt":"兑换第三方平台积分","tag_str":"-"},{"id":2036367,"type":7,"user_id":59461,"score":"2000.00","remark":"退出分红权#2026754","order_no":"0","create_time":"2020-06-02 14:55:09","type_txt":"退出分红权","tag_str":" "},{"id":2036366,"type":7,"user_id":59461,"score":"1961.54","remark":"退出分红权#2026754","order_no":"0","create_time":"2020-06-02 14:49:03","type_txt":"退出分红权","tag_str":" "},{"id":2036361,"type":42,"user_id":59461,"score":"2000.00","remark":"购买分红权#2026754","order_no":"0","create_time":"2020-06-02 09:23:48","type_txt":"系统扣除","tag_str":"-"},{"id":2036360,"type":42,"user_id":59461,"score":"10000.00","remark":"购买分红权#2026752","order_no":"0","create_time":"2020-06-01 15:01:40","type_txt":"系统扣除","tag_str":"-"},{"id":2036355,"type":42,"user_id":59461,"score":"1000.00","remark":"升级账户等级#导购","order_no":"0","create_time":"2020-05-15 15:46:51","type_txt":"系统扣除","tag_str":"-"},{"id":2036352,"type":42,"user_id":59461,"score":"1000.00","remark":"升级账户等级#导购","order_no":"0","create_time":"2020-05-15 15:43:00","type_txt":"系统扣除","tag_str":"-"}]
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
                 * id : 2036371
                 * type : 42
                 * user_id : 59461
                 * score : 1000.00
                 * remark : 升级账户等级#导购
                 * order_no : 0
                 * create_time : 2020-06-10 17:47:27
                 * type_txt : 系统扣除
                 * tag_str : -
                 */

                private int id;
                private int type;
                private int user_id;
                private String score;
                private String remark;
                private String order_no;
                private String create_time;
                private String type_txt;
                private String tag_str;

                public int getId() {
                        return id;
                }

                public void setId(int id) {
                        this.id = id;
                }

                public int getType() {
                        return type;
                }

                public void setType(int type) {
                        this.type = type;
                }

                public int getUser_id() {
                        return user_id;
                }

                public void setUser_id(int user_id) {
                        this.user_id = user_id;
                }

                public String getScore() {
                        return score;
                }

                public void setScore(String score) {
                        this.score = score;
                }

                public String getRemark() {
                        return remark;
                }

                public void setRemark(String remark) {
                        this.remark = remark;
                }

                public String getOrder_no() {
                        return order_no;
                }

                public void setOrder_no(String order_no) {
                        this.order_no = order_no;
                }

                public String getCreate_time() {
                        return create_time;
                }

                public void setCreate_time(String create_time) {
                        this.create_time = create_time;
                }

                public String getType_txt() {
                        return type_txt;
                }

                public void setType_txt(String type_txt) {
                        this.type_txt = type_txt;
                }

                public String getTag_str() {
                        return tag_str;
                }

                public void setTag_str(String tag_str) {
                        this.tag_str = tag_str;
                }
        }
//    public List<DataListBean> dataListBeans;
//
//    public static class DataListBean {
//        public int id;
//        public int type;
//        public int user_id;
//        public String score;
//        public String remark;
//        public String order_no;
//        public String create_time;
//        public String type_txt;
//        public String tag_str;
   // }




}
