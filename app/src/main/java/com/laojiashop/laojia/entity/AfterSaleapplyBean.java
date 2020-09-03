package com.laojiashop.laojia.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AfterSaleapplyBean {


    /**
     * goods_info : [{"goods_id":"311","supplier_id":9,"goods_title":"益普利生牌维生素C咀嚼片30片","num":"1","price":"19.99","cost_price":"1.00","market_price":"69.00","max_score":0,"sku":"1642","sku_name":"","path":"http://static.laojia99.com/Uploads/image/20200314/9ba17d09f988f6f132d2bf2158196271.jpg","is_cross_border":1}]
     * sale_text : [{"id":1,"name":"退货"},{"id":2,"name":"换货"}]
     * cause_text : {"1":[{"id":1,"name":"退货\u2014\u2014不想要了"},{"id":2,"name":"退货\u2014\u2014和描述不符"},{"id":3,"name":"退货\u2014\u2014质量问题"},{"id":4,"name":"退货\u2014\u2014发错货"},{"id":5,"name":"退货\u2014\u2014缺少件"}],"2":[{"id":1,"name":"不想要了"},{"id":2,"name":"和描述不符"},{"id":3,"name":"质量问题"},{"id":4,"name":"发错货"},{"id":5,"name":"缺少件"}]}
     */

    private CauseTextBean cause_text;
    private List<GoodsInfoBean> goods_info;
    private List<SaleTextBean> sale_text;

    public CauseTextBean getCause_text() {
        return cause_text;
    }

    public void setCause_text(CauseTextBean cause_text) {
        this.cause_text = cause_text;
    }

    public List<GoodsInfoBean> getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(List<GoodsInfoBean> goods_info) {
        this.goods_info = goods_info;
    }

    public List<SaleTextBean> getSale_text() {
        return sale_text;
    }

    public void setSale_text(List<SaleTextBean> sale_text) {
        this.sale_text = sale_text;
    }

    public static class CauseTextBean {
        @SerializedName("1")
        private List<_$1Bean> _$1;
        @SerializedName("2")
        private List<_$2Bean> _$2;
        public List<_$1Bean> get_$1() {
            return _$1;
        }

        public void set_$1(List<_$1Bean> _$1) {
            this._$1 = _$1;
        }

        public List<_$2Bean> get_$2() {
            return _$2;
        }

        public void set_$2(List<_$2Bean> _$2) {
            this._$2 = _$2;
        }

        public static class _$1Bean {
            /**
             * id : 1
             * name : 退货——不想要了
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

        public static class _$2Bean {
            /**
             * id : 1
             * name : 不想要了
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

    public static class GoodsInfoBean {
        /**
         * goods_id : 311
         * supplier_id : 9
         * goods_title : 益普利生牌维生素C咀嚼片30片
         * num : 1
         * price : 19.99
         * cost_price : 1.00
         * market_price : 69.00
         * max_score : 0
         * sku : 1642
         * sku_name :
         * path : http://static.laojia99.com/Uploads/image/20200314/9ba17d09f988f6f132d2bf2158196271.jpg
         * is_cross_border : 1
         */

        private String goods_id;
        private int supplier_id;
        private String goods_title;
        private String num;
        private String price;
        private String cost_price;
        private String market_price;
        private int max_score;
        private String sku;
        private String sku_name;
        private String path;
        private int is_cross_border;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
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

    public static class SaleTextBean {
        /**
         * id : 1
         * name : 退货
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
