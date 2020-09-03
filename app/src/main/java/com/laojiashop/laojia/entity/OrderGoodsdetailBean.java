package com.laojiashop.laojia.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 订单详情
 */
public class OrderGoodsdetailBean implements Serializable {

    /**
     * id : 22266
     * type : 2
     * order_no : 2007159436662753460
     * user_id : 59461
     * goods_info : [{"goods_id":380,"supplier_id":17,"goods_title":"舒蕾洗发水山茶花焗油莹亮600ml*2瓶","num":1,"price":45.9,"cost_price":"45.9","market_price":"","max_score":0,"sku":1664,"sku_name":"600ml*2瓶","path":"http://static.laojia99.com/Uploads/image/20200326/3344d3ca29f074399ac504cafcffd307.jpg","is_cross_border":0}]
     * mark :
     * address : {"id":319,"user_id":59461,"name":"但是发","phone":"18888888888","code":"500101","province":"重庆市","city":"重庆市","area":"万州区","address":"第三方","is_def":1,"create_time":"2020-07-09 17:01:43","post_code":"","p_code":500000,"c_code":500100,"a_code":500101,"ssq":"重庆市/重庆市/万州区"}
     * goods_ids : 380
     * total : 53.90
     * goods_pay : 45.90
     * discounts : 0.00
     * freight : 8.00
     * pay : 0.00
     * score : 0.00
     * coin : 53.90
     * create_time : 2020-07-10 15:37:07
     * total_num : 1
     * supplier_id : 0
     * status : 3
     * tuangou_id : 0
     * join_id : 0
     * pay_id : 146692
     * company_id : 2
     * express_no : 932684652123198
     * send_time : 2020-07-10 15:42:28
     * has_comment : 0
     * sid : 0
     * deleted : 0
     * is_cross_border : 0
     * identity_card :
     * name : 测试会员
     * status_txt : 待收货
     * status_txt2 : 发货
     * phone : 18888888880
     * log : [{"id":38033,"user_info":{"id":59461,"name":"测试会员"},"type":1,"type_txt":"创建","user_type":1,"user_type_txt":"用户","content":"创建订单","create_time":"2020-05-28 17:09:47","user_name":"测试会员"},{"id":39479,"user_info":{"id":59461,"name":"测试会员"},"type":1,"type_txt":"创建","user_type":1,"user_type_txt":"用户","content":"创建订单","create_time":"2020-07-10 15:37:07","user_name":"测试会员"},{"id":39480,"user_info":{"id":59461,"name":"测试会员"},"type":2,"type_txt":"支付","user_type":1,"user_type_txt":"用户","content":"订单支付-2007159436662753460","create_time":"2020-07-10 15:37:07","user_name":"测试会员"},{"id":39481,"user_info":{"id":17,"title":"润和美","name":"李旭亮"},"type":3,"type_txt":"发货","user_type":3,"user_type_txt":"供应商","content":"承运人:中通快递,单号:932684652123198","create_time":"2020-07-10 15:42:28","user_name":"润和美-李旭亮"}]
     * pay_way_txt : 金币
     * payment_no : C2007159436662720136
     * pay_time : 2020-07-10 15:37:07
     * pay_way : 5
     * express_title : 中通快递
     * wuliu :
     * commentd : false
     * names :
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
    private String status_txt2;
    private String phone;
    private String pay_way_txt;
    private String payment_no;
    private String pay_time;
    private int pay_way;
    private String express_title;
    private String wuliu;
    private boolean commentd;
   // private String names;
    private List<GoodsInfoBean> goods_info;
    private List<LogBean> log;

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

    public String getStatus_txt2() {
        return status_txt2;
    }

    public void setStatus_txt2(String status_txt2) {
        this.status_txt2 = status_txt2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPay_way_txt() {
        return pay_way_txt;
    }

    public void setPay_way_txt(String pay_way_txt) {
        this.pay_way_txt = pay_way_txt;
    }

    public String getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(String payment_no) {
        this.payment_no = payment_no;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public int getPay_way() {
        return pay_way;
    }

    public void setPay_way(int pay_way) {
        this.pay_way = pay_way;
    }

    public String getExpress_title() {
        return express_title;
    }

    public void setExpress_title(String express_title) {
        this.express_title = express_title;
    }

    public String getWuliu() {
        return wuliu;
    }

    public void setWuliu(String wuliu) {
        this.wuliu = wuliu;
    }

    public boolean isCommentd() {
        return commentd;
    }

    public void setCommentd(boolean commentd) {
        this.commentd = commentd;
    }

//    public String getNames() {
//        return names;
//    }
//
//    public void setNames(String names) {
//        this.names = names;
//    }

    public List<GoodsInfoBean> getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(List<GoodsInfoBean> goods_info) {
        this.goods_info = goods_info;
    }

    public List<LogBean> getLog() {
        return log;
    }

    public void setLog(List<LogBean> log) {
        this.log = log;
    }

    public static class AddressBean implements Serializable{
        /**
         * id : 319
         * user_id : 59461
         * name : 但是发
         * phone : 18888888888
         * code : 500101
         * province : 重庆市
         * city : 重庆市
         * area : 万州区
         * address : 第三方
         * is_def : 1
         * create_time : 2020-07-09 17:01:43
         * post_code :
         * p_code : 500000
         * c_code : 500100
         * a_code : 500101
         * ssq : 重庆市/重庆市/万州区
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

    public static class GoodsInfoBean implements Serializable{
        /**
         * goods_id : 380
         * supplier_id : 17
         * goods_title : 舒蕾洗发水山茶花焗油莹亮600ml*2瓶
         * num : 1
         * price : 45.9
         * cost_price : 45.9
         * market_price :
         * max_score : 0
         * sku : 1664
         * sku_name : 600ml*2瓶
         * path : http://static.laojia99.com/Uploads/image/20200326/3344d3ca29f074399ac504cafcffd307.jpg
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

    public static class LogBean implements Serializable{
        /**
         * id : 38033
         * user_info : {"id":59461,"name":"测试会员"}
         * type : 1
         * type_txt : 创建
         * user_type : 1
         * user_type_txt : 用户
         * content : 创建订单
         * create_time : 2020-05-28 17:09:47
         * user_name : 测试会员
         */

        private int id;
        private UserInfoBean user_info;
        private int type;
        private String type_txt;
        private int user_type;
        private String user_type_txt;
        private String content;
        private String create_time;
        private String user_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getType_txt() {
            return type_txt;
        }

        public void setType_txt(String type_txt) {
            this.type_txt = type_txt;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public String getUser_type_txt() {
            return user_type_txt;
        }

        public void setUser_type_txt(String user_type_txt) {
            this.user_type_txt = user_type_txt;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public static class UserInfoBean implements Serializable{
            /**
             * id : 59461
             * name : 测试会员
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
