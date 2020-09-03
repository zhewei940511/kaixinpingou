package com.laojiashop.laojia.entity;

import java.util.List;

public class GoldcoinsBean {

    /**
     * total : 71
     * per_page : 10
     * current_page : 1
     * last_page : 8
     * data : [{"id":163099,"user_id":59461,"type":42,"coin":"36.90","remark":"参与记录#168161899","order_no":"168161899","create_time":"2020-06-23 17:27:40","user_name":"测试会员","user_phone":"18888888880","tag_str":"-","type_txt":"消费"},{"id":163096,"user_id":59461,"type":42,"coin":"2088.00","remark":"参与记录#168161896","order_no":"168161896","create_time":"2020-06-23 17:26:23","user_name":"测试会员","user_phone":"18888888880","tag_str":"-","type_txt":"消费"},{"id":163089,"user_id":59461,"type":42,"coin":"158.00","remark":"参与记录#168161889","order_no":"168161889","create_time":"2020-06-23 17:22:54","user_name":"测试会员","user_phone":"18888888880","tag_str":"-","type_txt":"消费"},{"id":163042,"user_id":59461,"type":23,"coin":"9807.69","remark":"退出分红权#2026752","order_no":"0","create_time":"2020-06-22 17:44:32","user_name":"测试会员","user_phone":"18888888880","tag_str":" ","type_txt":"返还服务费"},{"id":163039,"user_id":59461,"type":42,"coin":"99.00","remark":"参与记录#168161838","order_no":"168161838","create_time":"2020-06-20 17:49:14","user_name":"测试会员","user_phone":"18888888880","tag_str":"-","type_txt":"消费"},{"id":163038,"user_id":59461,"type":10,"coin":"300.00","remark":"提现失败","order_no":"2395","create_time":"2020-06-20 11:45:37","user_name":"测试会员","user_phone":"18888888880","tag_str":" ","type_txt":"退款"},{"id":163030,"user_id":59461,"type":22,"coin":"10000.00","remark":"下级用户服务费升级为代理商奖励代理商0.5%销售补贴#9","order_no":"0","create_time":"2020-06-19 14:33:53","user_name":"测试会员","user_phone":"18888888880","tag_str":" ","type_txt":"销售补贴"},{"id":163027,"user_id":59461,"type":22,"coin":"10000.00","remark":"下级用户服务费升级为代理商奖励代理商0.5%销售补贴#9","order_no":"0","create_time":"2020-06-19 14:33:23","user_name":"测试会员","user_phone":"18888888880","tag_str":" ","type_txt":"销售补贴"},{"id":162943,"user_id":59461,"type":43,"coin":"9000.00","remark":"用户提现#2399","order_no":"2399","create_time":"2020-06-18 15:00:10","user_name":"测试会员","user_phone":"18888888880","tag_str":"-","type_txt":"提现"},{"id":162940,"user_id":59461,"type":43,"coin":"10000.00","remark":"用户提现#2396","order_no":"2396","create_time":"2020-06-18 14:53:09","user_name":"测试会员","user_phone":"18888888880","tag_str":"-","type_txt":"提现"}]
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
         * id : 163099
         * user_id : 59461
         * type : 42
         * coin : 36.90
         * remark : 参与记录#168161899
         * order_no : 168161899
         * create_time : 2020-06-23 17:27:40
         * user_name : 测试会员
         * user_phone : 18888888880
         * tag_str : -
         * type_txt : 消费
         */

        private int id;
        private int user_id;
        private int type;
        private String coin;
        private String remark;
        private String order_no;
        private String create_time;
        private String user_name;
        private String user_phone;
        private String tag_str;
        private String type_txt;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getTag_str() {
            return tag_str;
        }

        public void setTag_str(String tag_str) {
            this.tag_str = tag_str;
        }

        public String getType_txt() {
            return type_txt;
        }

        public void setType_txt(String type_txt) {
            this.type_txt = type_txt;
        }
    }
}
