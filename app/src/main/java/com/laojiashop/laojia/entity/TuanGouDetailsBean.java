package com.laojiashop.laojia.entity;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;
import java.util.List;

public class TuanGouDetailsBean implements Serializable {


    /**
     * id : 168021609
     * goods_id : 395
     * title : 羊奶粉中老年成人营养羊初乳蛋白粉铁罐装350克*2罐
     * price : 99.00
     * market_price : 139.00
     * status : 9
     * status_txt : 拼团失败
     * number : 3
     * stitle :
     * name : 团队59469
     * phone : 18888888885
     * headimgurl : http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg
     * count_info : {"per":0,"list":[],"count":0,"number":3}
     * content :
     * path : http://static.laojia99.com/Uploads/image/20200329/474f7b1b96a2a111430c5aa6c8c22eaf.jpg
     * imgs : [{"name":"微信图片_20200327161549.jpg","url":"http://static.laojia99.com/Uploads/image/20200329/7f1411570355e4c789314a523449e9e8.jpg","uid":1585454527482,"status":"success"}]
     * freight : false
     * supplier_id : 20
     * sku_info : {"sku":"1681","sku_name":"2罐*350g","inventory":100097,"price":"139","market_price":"","cost_price":"1","score":0,"max":0}
     * price_id : 0
     * done_time : 0
     * stime : 0
     * etime : 0
     * create_time : 2020-07-27 16:11:17
     * ctime : 60328647421429323997fe363670e9c6
     * want : 0
     * intro : [{"name":"微信截图_20200325143256.png","url":"http://static.laojia99.com/Uploads/image/20200329/d2232454366763d93b71429200679ee1.png","uid":1585454949060,"status":"success"},{"name":"微信截图_20200325143325.png","url":"http://static.laojia99.com/Uploads/image/20200329/578e116e4d5ecd30e265f4bdb15d0475.png","uid":1585454956829,"status":"success"},{"name":"微信截图_20200325143411.png","url":"http://static.laojia99.com/Uploads/image/20200329/8d99479a0a6aa9b3ba4b2874e18c7282.png","uid":1585454962407,"status":"success"},{"name":"微信截图_20200325143426.png","url":"http://static.laojia99.com/Uploads/image/20200329/03f9f139f0c27d94c94709d1b31b1b66.png","uid":1585454969257,"status":"success"},{"name":"微信截图_20200325143446.png","url":"http://static.laojia99.com/Uploads/image/20200329/6ea234c33caa9e46dfac3b2d7453113e.png","uid":1585454974679,"status":"success"},{"name":"微信截图_20200325143458.png","url":"http://static.laojia99.com/Uploads/image/20200329/59b5a72e258a67a7533542cbcf66f2bc.png","uid":1585454982716,"status":"success"},{"name":"微信截图_20200325143511.png","url":"http://static.laojia99.com/Uploads/image/20200329/0610dc11bf29a8a94de4e6c79706ce7a.png","uid":1585454988546,"status":"success"},{"name":"微信图片_20200319115233.png","url":"http://static.laojia99.com/Uploads/image/20200329/6f8bb5c083642d93ddd65ae1235287ac.png","uid":1585455000334,"status":"success"},{"url":"http://static.laojia99.com/Uploads/image/20200316/397bd2f1390730c52ea4ed8df44e1e04.jpeg"}]
     * freight_list : []
     * is_cross_border : 0
     * freeze_coin :
     * can_buy : true
     * msg : ok
     * is_act : false
     * date : false
     * freight_tips : 全国包邮
     * goodsComemnt : {}
     * goodsContentUrl : http://sys.demo.laojia99.com/viewgoods/395.html
     */

    private int id;
    private int goods_id;
    private String title;
    private String price;
    private String market_price;
    private int status;
    private String status_txt;
    private int number;
    private String stitle;
    private String name;
    private String phone;
    private String headimgurl;
    private CountInfoBean count_info;
    private String content;
    private String path;
    private String freight;
    private int supplier_id;
    private SkuInfoBean sku_info;
    private int price_id;
    private int done_time;
    private int stime;
    private int etime;
    private String create_time;
    private String ctime;
    private int want;
    private int is_cross_border;
    private String freeze_coin;
    private boolean can_buy;
    private String msg;
    private boolean is_act;
    private boolean date;
    private String freight_tips;
    private GoodsComemntBean goodsComemnt;
    private String goodsContentUrl;
    private List<ImgsBean> imgs;
    private List<IntroBean> intro;
    private List<FreightListBean> freight_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_txt() {
        return status_txt;
    }

    public void setStatus_txt(String status_txt) {
        this.status_txt = status_txt;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
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

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public CountInfoBean getCount_info() {
        return count_info;
    }

    public void setCount_info(CountInfoBean count_info) {
        this.count_info = count_info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public SkuInfoBean getSku_info() {
        return sku_info;
    }

    public void setSku_info(SkuInfoBean sku_info) {
        this.sku_info = sku_info;
    }

    public int getPrice_id() {
        return price_id;
    }

    public void setPrice_id(int price_id) {
        this.price_id = price_id;
    }

    public int getDone_time() {
        return done_time;
    }

    public void setDone_time(int done_time) {
        this.done_time = done_time;
    }

    public int getStime() {
        return stime;
    }

    public void setStime(int stime) {
        this.stime = stime;
    }

    public int getEtime() {
        return etime;
    }

    public void setEtime(int etime) {
        this.etime = etime;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public int getWant() {
        return want;
    }

    public void setWant(int want) {
        this.want = want;
    }

    public int getIs_cross_border() {
        return is_cross_border;
    }

    public void setIs_cross_border(int is_cross_border) {
        this.is_cross_border = is_cross_border;
    }

    public String getFreeze_coin() {
        return freeze_coin;
    }

    public void setFreeze_coin(String freeze_coin) {
        this.freeze_coin = freeze_coin;
    }

    public boolean isCan_buy() {
        return can_buy;
    }

    public void setCan_buy(boolean can_buy) {
        this.can_buy = can_buy;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isIs_act() {
        return is_act;
    }

    public void setIs_act(boolean is_act) {
        this.is_act = is_act;
    }

    public boolean isDate() {
        return date;
    }

    public void setDate(boolean date) {
        this.date = date;
    }

    public String getFreight_tips() {
        return freight_tips;
    }

    public void setFreight_tips(String freight_tips) {
        this.freight_tips = freight_tips;
    }

    public GoodsComemntBean getGoodsComemnt() {
        return goodsComemnt;
    }

    public void setGoodsComemnt(GoodsComemntBean goodsComemnt) {
        this.goodsComemnt = goodsComemnt;
    }

    public String getGoodsContentUrl() {
        return goodsContentUrl;
    }

    public void setGoodsContentUrl(String goodsContentUrl) {
        this.goodsContentUrl = goodsContentUrl;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public List<IntroBean> getIntro() {
        return intro;
    }

    public void setIntro(List<IntroBean> intro) {
        this.intro = intro;
    }

    public static class FreightListBean implements Serializable {
        /**
         * id : 110000
         * name : 北京市
         * freight : 6
         * freight_price : 600
         */

        private int id;
        private String name;
        private int freight;
        private int freight_price;

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

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getFreight_price() {
            return freight_price;
        }

        public void setFreight_price(int freight_price) {
            this.freight_price = freight_price;
        }
    }
    public List<FreightListBean> getFreight_list() {
        return freight_list;
    }

    public void setFreight_list(List<FreightListBean> freight_list) {
        this.freight_list = freight_list;
    }


    public static class CountInfoBean implements Serializable{
        /**
         * per : 0
         * list : []
         * count : 0
         * number : 3
         */

        private int per;
        private int count;
        private int number;
        private List<?> list;

        public int getPer() {
            return per;
        }

        public void setPer(int per) {
            this.per = per;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }
    }

    public static class SkuInfoBean implements Serializable{
        /**
         * sku : 1681
         * sku_name : 2罐*350g
         * inventory : 100097
         * price : 139
         * market_price :
         * cost_price : 1
         * score : 0
         * max : 0
         */

        private String sku;
        private String sku_name;
        private int inventory;
        private String price;
        private String market_price;
        private String cost_price;
        private int score;
        private int max;

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

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getCost_price() {
            return cost_price;
        }

        public void setCost_price(String cost_price) {
            this.cost_price = cost_price;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }

    public static class GoodsComemntBean implements Serializable{
    }

    public static class ImgsBean  extends SimpleBannerInfo implements Serializable{
        /**
         * name : 微信图片_20200327161549.jpg
         * url : http://static.laojia99.com/Uploads/image/20200329/7f1411570355e4c789314a523449e9e8.jpg
         * uid : 1585454527482
         * status : success
         */

        private String name;
        private String url;
        private long uid;
        private String status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public Object getXBannerUrl() {
            return null;
        }
    }

    public static class IntroBean implements Serializable{
        /**
         * name : 微信截图_20200325143256.png
         * url : http://static.laojia99.com/Uploads/image/20200329/d2232454366763d93b71429200679ee1.png
         * uid : 1585454949060
         * status : success
         */

        private String name;
        private String url;
        private long uid;
        private String status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
