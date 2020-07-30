package com.laojiashop.laojia.entity;

import java.util.List;

public class MycollectionBean {

    /**
     * total : 8
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":885,"goods_id":422,"goods_name":"苹果Apple iPhone11手机","tags":["4g","双卡双待","A13处理器"],"status":1,"show_price":{"min":"4599","max":"5799"}},{"id":884,"goods_id":341,"goods_name":"飞天天然乳胶多功能枕1对","tags":[],"status":1,"show_price":{"min":"1680","max":"1680"}},{"id":883,"goods_id":384,"goods_name":"ob卫生棉条指入式量多*2 （32支）","tags":[],"status":1,"show_price":{"min":"59.9","max":"59.9"}},{"id":882,"goods_id":381,"goods_name":"云南白药热敷蒸汽眼罩12片","tags":[],"status":1,"show_price":{"min":"49","max":"49"}},{"id":881,"goods_id":312,"goods_name":"益生菌固体饮料4盒","tags":["高活性益生菌","更适合中国人体质","拒绝腹泻，赶走便秘"],"status":1,"show_price":{"min":"459","max":"459"}},{"id":880,"goods_id":421,"goods_name":"强生婴儿牛奶沐浴露套装1000ml*1瓶","tags":[],"status":1,"show_price":{"min":"","max":""}},{"id":879,"goods_id":419,"goods_name":"唐贵人护肤、洗护、美妆十件套","tags":[],"status":1,"show_price":{"min":"50","max":"50"}},{"id":878,"goods_id":377,"goods_name":"强生婴儿牛奶沐浴露套装1000ml*2瓶","tags":[],"status":1,"show_price":{"min":"50.9","max":"50.9"}}]
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
         * id : 885
         * goods_id : 422
         * goods_name : 苹果Apple iPhone11手机
         * tags : ["4g","双卡双待","A13处理器"]
         * status : 1
         * show_price : {"min":"4599","max":"5799"}
         */

        private int id;
        private int goods_id;
        private String goods_name;
        private int status;


        private String path;
        private ShowPriceBean show_price;
        private List<String> tags;

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

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public ShowPriceBean getShow_price() {
            return show_price;
        }

        public void setShow_price(ShowPriceBean show_price) {
            this.show_price = show_price;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
        public static class ShowPriceBean {
            /**
             * min : 4599
             * max : 5799
             */

            private String min;
            private String max;

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }
        }
    }
}
