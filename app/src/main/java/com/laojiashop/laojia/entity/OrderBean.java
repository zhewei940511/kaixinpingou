package com.laojiashop.laojia.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 订单列表实体类
 */
public class OrderBean implements Serializable {

    /**
     * total : 8
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":22265,"type":2,"order_no":"2006159167474045164","user_id":59461,"goods_info":[{"goods_id":311,"supplier_id":9,"goods_title":"益普利生牌维生素C咀嚼片30片","num":1,"price":5.9,"cost_price":"5.9","market_price":"","max_score":0,"sku":1642,"sku_name":"30片","path":"http://static.laojia99.com/Uploads/image/20200314/9ba17d09f988f6f132d2bf2158196271.jpg","is_cross_border":0}],"mark":"","address":{"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"},"goods_ids":"311","total":"13.90","goods_pay":"5.90","discounts":"0.00","freight":"8.00","pay":"0.00","score":"0.00","coin":"13.90","create_time":"2020-06-09 11:52:20","total_num":1,"supplier_id":0,"status":4,"tuangou_id":0,"join_id":0,"pay_id":146541,"company_id":1,"express_no":"5219845631785","send_time":"2020-06-02 11:03:42","has_comment":0,"sid":0,"deleted":0,"is_cross_border":0,"identity_card":"","name":"","status_txt":"已收货"},{"id":22264,"type":2,"order_no":"2006159167065474003","user_id":59461,"goods_info":[{"goods_id":377,"supplier_id":17,"goods_title":"强生婴儿牛奶沐浴露套装1000ml*2瓶","num":1,"price":50.9,"cost_price":"50.9","market_price":"","max_score":0,"sku":1661,"sku_name":"1000ml*2瓶","path":"http://static.laojia99.com/Uploads/image/20200326/527e5c5572f273c221598fbfa9fadd6b.jpg","is_cross_border":0}],"mark":"","address":{"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"},"goods_ids":"377","total":"50.90","goods_pay":"50.90","discounts":"0.00","freight":"0.00","pay":"0.00","score":"0.00","coin":"50.90","create_time":"2020-06-09 10:44:14","total_num":1,"supplier_id":0,"status":4,"tuangou_id":0,"join_id":0,"pay_id":146540,"company_id":2,"express_no":"21165565615615156","send_time":"2020-05-28 17:24:25","has_comment":0,"sid":0,"deleted":0,"is_cross_border":0,"identity_card":"","name":"","status_txt":"已收货"},{"id":22263,"type":2,"order_no":"2006159166574950383","user_id":59461,"goods_info":[{"goods_id":352,"supplier_id":17,"goods_title":"大宝SOD蜜乳液男女面霜100ml*3","num":1,"price":33.9,"cost_price":"33.9","market_price":"","max_score":0,"sku":1645,"sku_name":"100ml*3瓶","path":"http://static.laojia99.com/Uploads/image/20200319/261fbaa436647ee6233016b8392e9efb.jpg","is_cross_border":0}],"mark":"","address":{"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"},"goods_ids":"352","total":"33.90","goods_pay":"33.90","discounts":"0.00","freight":"0.00","pay":"33.90","score":"0.00","coin":"0.00","create_time":"2020-06-08 12:23:44","total_num":1,"supplier_id":0,"status":91,"tuangou_id":0,"join_id":0,"pay_id":0,"company_id":0,"express_no":"","send_time":"1970-01-01 08:00:00","has_comment":0,"sid":0,"deleted":0,"is_cross_border":0,"identity_card":"","name":"","status_txt":"过期"},{"id":22262,"type":2,"order_no":"2006159166573041283","user_id":59461,"goods_info":[{"goods_id":377,"supplier_id":17,"goods_title":"强生婴儿牛奶沐浴露套装1000ml*2瓶","num":2,"price":50.9,"cost_price":"50.9","market_price":"","max_score":0,"sku":1661,"sku_name":"1000ml*2瓶","path":"http://static.laojia99.com/Uploads/image/20200326/527e5c5572f273c221598fbfa9fadd6b.jpg","is_cross_border":0}],"mark":"","address":{"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"},"goods_ids":"377","total":"101.80","goods_pay":"101.80","discounts":"0.00","freight":"0.00","pay":"101.80","score":"0.00","coin":"0.00","create_time":"2020-06-08 12:23:44","total_num":2,"supplier_id":0,"status":91,"tuangou_id":0,"join_id":0,"pay_id":0,"company_id":0,"express_no":"","send_time":"1970-01-01 08:00:00","has_comment":0,"sid":0,"deleted":0,"is_cross_border":0,"identity_card":"","name":"","status_txt":"过期"},{"id":22261,"type":2,"order_no":"2006159160804410596","user_id":59461,"goods_info":[{"goods_id":352,"supplier_id":17,"goods_title":"大宝SOD蜜乳液男女面霜100ml*3","num":2,"price":45.9,"cost_price":"45.9","market_price":"","max_score":0,"sku":1646,"sku_name":"200ml*3瓶","path":"http://static.laojia99.com/Uploads/image/20200319/261fbaa436647ee6233016b8392e9efb.jpg","is_cross_border":0}],"mark":"","address":{"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"},"goods_ids":"352","total":"91.80","goods_pay":"91.80","discounts":"0.00","freight":"0.00","pay":"91.80","score":"0.00","coin":"0.00","create_time":"2020-06-08 12:23:44","total_num":2,"supplier_id":0,"status":91,"tuangou_id":0,"join_id":0,"pay_id":0,"company_id":0,"express_no":"","send_time":"1970-01-01 08:00:00","has_comment":0,"sid":0,"deleted":0,"is_cross_border":0,"identity_card":"","name":"","status_txt":"过期"},{"id":22260,"type":2,"order_no":"2006159160802124778","user_id":59461,"goods_info":[{"goods_id":380,"supplier_id":17,"goods_title":"舒蕾洗发水山茶花焗油莹亮600ml*2瓶","num":3,"price":45.9,"cost_price":"45.9","market_price":"","max_score":0,"sku":1664,"sku_name":"600ml*2瓶","path":"http://static.laojia99.com/Uploads/image/20200326/3344d3ca29f074399ac504cafcffd307.jpg","is_cross_border":0}],"mark":"","address":{"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"},"goods_ids":"380","total":"145.70","goods_pay":"137.70","discounts":"0.00","freight":"8.00","pay":"145.70","score":"0.00","coin":"0.00","create_time":"2020-06-08 12:23:44","total_num":3,"supplier_id":0,"status":91,"tuangou_id":0,"join_id":0,"pay_id":0,"company_id":0,"express_no":"","send_time":"1970-01-01 08:00:00","has_comment":0,"sid":0,"deleted":0,"is_cross_border":0,"identity_card":"","name":"","status_txt":"过期"},{"id":22259,"type":2,"order_no":"2006159160800783961","user_id":59461,"goods_info":[{"goods_id":311,"supplier_id":9,"goods_title":"益普利生牌维生素C咀嚼片30片","num":2,"price":5.9,"cost_price":"5.9","market_price":"","max_score":0,"sku":1642,"sku_name":"30片","path":"http://static.laojia99.com/Uploads/image/20200314/9ba17d09f988f6f132d2bf2158196271.jpg","is_cross_border":0}],"mark":"","address":{"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"},"goods_ids":"311","total":"19.80","goods_pay":"11.80","discounts":"0.00","freight":"8.00","pay":"19.80","score":"0.00","coin":"0.00","create_time":"2020-06-08 12:23:44","total_num":2,"supplier_id":0,"status":91,"tuangou_id":0,"join_id":0,"pay_id":0,"company_id":0,"express_no":"","send_time":"1970-01-01 08:00:00","has_comment":0,"sid":0,"deleted":0,"is_cross_border":0,"identity_card":"","name":"","status_txt":"过期"},{"id":22241,"type":1,"order_no":"2005158935225585157","user_id":59461,"goods_info":[{"goods_id":374,"supplier_id":20,"goods_title":"羊奶粉中老年成人营养羊初乳蛋白粉铁罐装350克*12罐","num":1,"price":"588.00","cost_price":"1","market_price":"","sku":1657,"sku_name":"12罐*350g","path":"http://static.laojia99.com/Uploads/image/20200327/15af4120ba40ddc03b7f7156a2ffd9c5.jpg"}],"mark":"拼团订单","address":{"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"},"goods_ids":"374","total":"588.00","goods_pay":"588.00","discounts":"0.00","freight":"0.00","pay":"0.00","score":"0.00","coin":"588.00","create_time":"2020-05-13 14:44:15","total_num":1,"supplier_id":0,"status":2,"tuangou_id":168021499,"join_id":168161676,"pay_id":146431,"company_id":0,"express_no":"","send_time":"1970-01-01 08:00:00","has_comment":0,"sid":0,"deleted":0,"is_cross_border":0,"identity_card":"","name":"","status_txt":"待发货"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 22265
         * type : 2
         * order_no : 2006159167474045164
         * user_id : 59461
         * goods_info : [{"goods_id":311,"supplier_id":9,"goods_title":"益普利生牌维生素C咀嚼片30片","num":1,"price":5.9,"cost_price":"5.9","market_price":"","max_score":0,"sku":1642,"sku_name":"30片","path":"http://static.laojia99.com/Uploads/image/20200314/9ba17d09f988f6f132d2bf2158196271.jpg","is_cross_border":0}]
         * mark :
         * address : {"id":309,"user_id":59461,"name":"测试会员","phone":"18888888880","code":"110101","province":"北京市","city":"北京市","area":"东城区","address":"酸豆角分配给发的","is_def":0,"create_time":"2020-05-13 11:27:38","post_code":"","p_code":110000,"c_code":110100,"a_code":110101,"ssq":"北京市/北京市/东城区"}
         * goods_ids : 311
         * total : 13.90
         * goods_pay : 5.90
         * discounts : 0.00
         * freight : 8.00
         * pay : 0.00
         * score : 0.00
         * coin : 13.90
         * create_time : 2020-06-09 11:52:20
         * total_num : 1
         * supplier_id : 0
         * status : 4
         * tuangou_id : 0
         * join_id : 0
         * pay_id : 146541
         * company_id : 1
         * express_no : 5219845631785
         * send_time : 2020-06-02 11:03:42
         * has_comment : 0
         * sid : 0
         * deleted : 0
         * is_cross_border : 0
         * identity_card :
         * name :
         * status_txt : 已收货
         */

        private int id;
        private int type;
        private String order_no;
        private int user_id;
        private String mark;
        private AddressBean address;
        private String goods_ids;
        private String total;
        private String goods_pay;
        private String discounts;
        private String freight;
        private String pay;
        private String score;
        private String coin;
        private String create_time;
        private int total_num;
        private int supplier_id;
        private int status;
        private int tuangou_id;
        private int join_id;
        private int pay_id;
        private int company_id;
        private String express_no;
        private String send_time;
        private int has_comment;
        private int sid;
        private int deleted;
        private int is_cross_border;
        private String identity_card;
        private String name;
        private String status_txt;
        private List<GoodsInfoBean> goods_info;

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

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public String getGoods_ids() {
            return goods_ids;
        }

        public void setGoods_ids(String goods_ids) {
            this.goods_ids = goods_ids;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getGoods_pay() {
            return goods_pay;
        }

        public void setGoods_pay(String goods_pay) {
            this.goods_pay = goods_pay;
        }

        public String getDiscounts() {
            return discounts;
        }

        public void setDiscounts(String discounts) {
            this.discounts = discounts;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTuangou_id() {
            return tuangou_id;
        }

        public void setTuangou_id(int tuangou_id) {
            this.tuangou_id = tuangou_id;
        }

        public int getJoin_id() {
            return join_id;
        }

        public void setJoin_id(int join_id) {
            this.join_id = join_id;
        }

        public int getPay_id() {
            return pay_id;
        }

        public void setPay_id(int pay_id) {
            this.pay_id = pay_id;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        public int getHas_comment() {
            return has_comment;
        }

        public void setHas_comment(int has_comment) {
            this.has_comment = has_comment;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public int getIs_cross_border() {
            return is_cross_border;
        }

        public void setIs_cross_border(int is_cross_border) {
            this.is_cross_border = is_cross_border;
        }

        public String getIdentity_card() {
            return identity_card;
        }

        public void setIdentity_card(String identity_card) {
            this.identity_card = identity_card;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus_txt() {
            return status_txt;
        }

        public void setStatus_txt(String status_txt) {
            this.status_txt = status_txt;
        }

        public List<GoodsInfoBean> getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(List<GoodsInfoBean> goods_info) {
            this.goods_info = goods_info;
        }

        public static class AddressBean {
            /**
             * id : 309
             * user_id : 59461
             * name : 测试会员
             * phone : 18888888880
             * code : 110101
             * province : 北京市
             * city : 北京市
             * area : 东城区
             * address : 酸豆角分配给发的
             * is_def : 0
             * create_time : 2020-05-13 11:27:38
             * post_code :
             * p_code : 110000
             * c_code : 110100
             * a_code : 110101
             * ssq : 北京市/北京市/东城区
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

        public static class GoodsInfoBean {
            /**
             * goods_id : 311
             * supplier_id : 9
             * goods_title : 益普利生牌维生素C咀嚼片30片
             * num : 1
             * price : 5.9
             * cost_price : 5.9
             * market_price :
             * max_score : 0
             * sku : 1642
             * sku_name : 30片
             * path : http://static.laojia99.com/Uploads/image/20200314/9ba17d09f988f6f132d2bf2158196271.jpg
             * is_cross_border : 0
             */

            private int goods_id;
            private int supplier_id;
            private String goods_title;
            private int num;
            private double price;
            private String cost_price;
            private String market_price;
            private int max_score;


            private String sku;
            private String sku_name;
            private String path;
            private int is_cross_border;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(int supplier_id) {
                this.supplier_id = supplier_id;
            }

            public String getGoods_title() {
                return goods_title;
            }

            public void setGoods_title(String goods_title) {
                this.goods_title = goods_title;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getCost_price() {
                return cost_price;
            }

            public void setCost_price(String cost_price) {
                this.cost_price = cost_price;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public int getMax_score() {
                return max_score;
            }

            public void setMax_score(int max_score) {
                this.max_score = max_score;
            }

            //            public int getSku() {
//                return sku;
//            }
//
//            public void setSku(int sku) {
//                this.sku = sku;
//            }
            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public String getSku_name() {
                return sku_name;
            }

            public void setSku_name(String sku_name) {
                this.sku_name = sku_name;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getIs_cross_border() {
                return is_cross_border;
            }

            public void setIs_cross_border(int is_cross_border) {
                this.is_cross_border = is_cross_border;
            }
        }
    }
}
