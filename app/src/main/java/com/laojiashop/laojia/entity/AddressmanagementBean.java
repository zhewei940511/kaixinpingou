package com.laojiashop.laojia.entity;

import java.util.List;

public class AddressmanagementBean {

    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":317,"user_id":59461,"name":"士大夫","phone":"18888888888","code":"310101","province":"上海市","city":"上海市","area":"黄浦区","address":"士大夫","is_def":0,"create_time":"2020-07-09 09:21:21","post_code":"","p_code":310000,"c_code":310100,"a_code":310101,"ssq":"上海市/上海市/黄浦区"},{"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的按时","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"}]
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
         * id : 317
         * user_id : 59461
         * name : 士大夫
         * phone : 18888888888
         * code : 310101
         * province : 上海市
         * city : 上海市
         * area : 黄浦区
         * address : 士大夫
         * is_def : 0
         * create_time : 2020-07-09 09:21:21
         * post_code :
         * p_code : 310000
         * c_code : 310100
         * a_code : 310101
         * ssq : 上海市/上海市/黄浦区
         */

        private int id;
        private int user_id;
        private String name;
        private String phone;
        private String code;
        private String province;
        private String city;
        private String area;
        private String address;
        private int is_def;
        private String create_time;
        private String post_code;
        private int p_code;
        private int c_code;
        private int a_code;
        private String ssq;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIs_def() {
            return is_def;
        }

        public void setIs_def(int is_def) {
            this.is_def = is_def;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPost_code() {
            return post_code;
        }

        public void setPost_code(String post_code) {
            this.post_code = post_code;
        }

        public int getP_code() {
            return p_code;
        }

        public void setP_code(int p_code) {
            this.p_code = p_code;
        }

        public int getC_code() {
            return c_code;
        }

        public void setC_code(int c_code) {
            this.c_code = c_code;
        }

        public int getA_code() {
            return a_code;
        }

        public void setA_code(int a_code) {
            this.a_code = a_code;
        }

        public String getSsq() {
            return ssq;
        }

        public void setSsq(String ssq) {
            this.ssq = ssq;
        }
    }
}
