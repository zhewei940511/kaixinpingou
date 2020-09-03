package com.laojiashop.laojia.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;
import java.util.List;

public class HomePageTestBean {
    private List<SpecialBean> special;
    private List<BannerBean> banner;
    private List<AdNewBean> ad_new;

    public List<SpecialBean> getSpecial() {
        return special;
    }

    public void setSpecial(List<SpecialBean> special) {
        this.special = special;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<AdNewBean> getAd_new() {
        return ad_new;
    }

    public void setAd_new(List<AdNewBean> ad_new) {
        this.ad_new = ad_new;
    }

    public static class SpecialBean implements MultiItemEntity {
        /**
         * id : 56
         * title : 爆款推荐
         * path : //gw.alicdn.com/imgextra/i1/183/O1CN01qoFSGE1DDr5ubDsLI_!!183-0-lubanu.jpg
         * display : 1
         * show_img : 1
         * goods_list : [{"show_price":{"min":"3.9","max":"19.99","priceTypeStr":"商城价"},"id":311,"path":"http://static.laojia99.com/Uploads/image/20200314/9ba17d09f988f6f132d2bf2158196271.jpg","title":"益普利生牌维生素C咀嚼片30片","stitle":"增加体质，预防感冒的作用","market_price":"69.00","price":"5.90","tags":["增加体质","预防感冒的作用"]},{"show_price":{"min":"4399","max":"4999","priceTypeStr":"拼团价"},"id":422,"path":"http://static.laojia99.com/Uploads/image/20200717/efb88c606339cf50e00af310337005e7.jpg","title":"苹果Apple iPhone11手机","stitle":"移动联通电信4G手机 双卡双待","market_price":"6199.00","price":"5999.00","tags":["4g","双卡双待","A13处理器"]},{"show_price":{"min":"5988","max":"6488","priceTypeStr":"商城价"},"id":423,"path":"http://static.laojia99.com/Uploads/image/20200723/45832ec235b12751d17d8b11cb673826.jpg","title":"华为 HUAWEI P40 Pro","stitle":"华为 HUAWEI P40 Pro 麒麟990 5G SoC芯片 5000万超感知徕卡四摄 50倍数字变焦 8GB+128GB亮黑色全网通5G手机","market_price":"6888.00","price":"5788.00","tags":["麒麟990","5G"," 5000万超感知徕卡四摄","SoC芯片","50倍数字变焦","全网通"]},{"show_price":{"min":"50.9","max":"50.9","priceTypeStr":"商城价"},"id":377,"path":"http://static.laojia99.com/Uploads/image/20200326/527e5c5572f273c221598fbfa9fadd6b.jpg","title":"强生婴儿牛奶沐浴露套装1000ml*2瓶","stitle":"婴儿洗浴无刺激护肤男女儿童沐浴液","market_price":"99.90","price":"50.90","tags":[]},{"show_price":{"min":"0.01","max":"0.01","priceTypeStr":"拼团价"},"id":380,"path":"http://static.laojia99.com/Uploads/image/20200326/3344d3ca29f074399ac504cafcffd307.jpg","title":"舒蕾洗发水山茶花焗油莹亮600ml*2瓶","stitle":"深度滋养精华洗发水 家庭装","market_price":"99.90","price":"45.90","tags":[]},{"show_price":{"min":"99","max":"99","priceTypeStr":"拼团价"},"id":395,"path":"http://static.laojia99.com/Uploads/image/20200329/474f7b1b96a2a111430c5aa6c8c22eaf.jpg","title":"羊奶粉中老年成人营养羊初乳蛋白粉铁罐装350克*2罐","stitle":"","market_price":"159.00","price":"139.00","tags":["健康自我，健康中国","提供关键营养","无糖放心喝"]},{"show_price":{"min":"158","max":"158","priceTypeStr":"拼团价"},"id":333,"path":"http://static.laojia99.com/Uploads/image/20200321/7169bb85a38d2fd058643c38310b22e1.jpg","title":"新起点免疫球蛋1盒","stitle":"央视推荐 驻扎武汉 驰援疫情一线专用产品","market_price":"298.00","price":"228.00","tags":["免疫力，真实力","20年专注免疫力研究","CCTV广告产品"]},{"show_price":{"min":"50","max":"50","priceTypeStr":"商城价"},"id":419,"path":"http://static.laojia99.com/Uploads/image/20200330/24b09ecaf17883046119afa801b79ea8.jpg","title":"唐贵人护肤、洗护、美妆十件套","stitle":"","market_price":"2228.00","price":"1613.00","tags":[]},{"show_price":{"min":"169","max":"169","priceTypeStr":"拼团价"},"id":332,"path":"http://static.laojia99.com/Uploads/image/20200326/bfc6f059417b51c8da5bc7a73e656b9b.jpg","title":"三千米甜高品质藏巢蜜500g*2瓶","stitle":"\u201c吃10瓶普通蜂蜜，不如吃一瓶真正的藏巢蜜\u201d\n蜂蜜产品已远销欧洲、中东、日本、马来西亚、新加坡等40多个国家和地区！","market_price":"236.00","price":"218.00","tags":["出口品质，蜂场直供","嚼着吃的蜂蜜","橘子花的秘密"]},{"show_price":{"min":"289","max":"289","priceTypeStr":"商城价"},"id":334,"path":"http://static.laojia99.com/Uploads/image/20200315/6973fdbdf595c121e708b96e3013e96d.jpg","title":"汤臣倍健白质粉600g+摇摇杯 礼盒装","stitle":" 男女性成人中老年孕妇增强免疫力","market_price":"499.00","price":"289.00","tags":[]}]
         * limit : 0
         */
        public static final int STYLEONE = 1;
        public static final int STYLETWO = 2;
        public static final int STYLETHREE = 3;
        private int id;
        private String title;
        private String path;
        private  int display;
        private int show_img;
        private int limit;
        private List<GoodsListBean> goods_list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public int getShow_img() {
            return show_img;
        }

        public void setShow_img(int show_img) {
            this.show_img = show_img;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        @Override
        public int getItemType() {
            return display;
        }

        public static class GoodsListBean implements MultiItemEntity {
            /**
             * show_price : {"min":"3.9","max":"19.99","priceTypeStr":"商城价"}
             * id : 311
             * path : http://static.laojia99.com/Uploads/image/20200314/9ba17d09f988f6f132d2bf2158196271.jpg
             * title : 益普利生牌维生素C咀嚼片30片
             * stitle : 增加体质，预防感冒的作用
             * market_price : 69.00
             * price : 5.90
             * tags : ["增加体质","预防感冒的作用"]
             */

            private ShowPriceBean show_price;
            private int id;
            private String path;
            private String title;
            private String stitle;
            private String market_price;
            private String price;
            private List<String> tags;

            public ShowPriceBean getShow_price() {
                return show_price;
            }

            public void setShow_price(ShowPriceBean show_price) {
                this.show_price = show_price;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStitle() {
                return stitle;
            }

            public void setStitle(String stitle) {
                this.stitle = stitle;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }

            @Override
            public int getItemType() {
                return 0;
            }

            public static class ShowPriceBean {
                /**
                 * min : 3.9
                 * max : 19.99
                 * priceTypeStr : 商城价
                 */

                private String min;
                private String max;
                private String priceTypeStr;

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

                public String getPriceTypeStr() {
                    return priceTypeStr;
                }

                public void setPriceTypeStr(String priceTypeStr) {
                    this.priceTypeStr = priceTypeStr;
                }
            }
        }
    }

    public static class BannerBean extends SimpleBannerInfo {
        /**
         * id : 185
         * title : 商城1
         * path : http://static.laojia99.com/Uploads/image/20200317/4ca2ee30bd97a077e87552a9057c63ce.jpg
         * url : #
         * type : 1
         */

        private int id;
        private String title;
        private String path;
        private String url;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public Object getXBannerUrl() {
            return null;
        }
    }

    public static class AdNewBean {
        /**
         * id : 204
         * title : 家居生活
         * path : http://static.laojia99.com/Uploads/image/20200723/d9ed6a155b3b3e1fe21a160e52e7bc13.png
         * url : 1
         * type : 1
         * t 1ed itle : 拼购商品
         */

        private int id;
        private String title;
        private String path;
        private String url;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


//    private List<BannerBean> banner;
//    private List<AdNewBean> ad_new;
//    private List<SpecialBean> special;
//    public List<AdNewBean> getAd_new() {
//        return ad_new;
//    }
//    public void setAd_new(List<AdNewBean> ad_new) {
//        this.ad_new = ad_new;
//    }
//    public List<BannerBean> getBanner() {
//       return banner;
//   }
//    public void setBanner(List<BannerBean> banner) {
//        this.banner = banner;
//    }
//    public List<SpecialBean> getSpecial() {
//        return special;
//    }
//    public void setSpecial(List<SpecialBean> special) {
//        this.special = special;
//    }
//    public static class SpecialBean
//    {
//        public String title;//标题
//        public List<GoodsListBean> goods_list;
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public List<GoodsListBean> getGoods_list() {
//            return goods_list;
//        }
//
//        public void setGoods_list(List<GoodsListBean> goods_list) {
//            this.goods_list = goods_list;
//        }
//
//
//        public static class GoodsListBean
//        {
//            public int id;
//            public String path;
//            public String title;
//            public String stitle;
//            public String market_price;//零售价
//            public String price;//商城价
//            public ShowPriceBean show_price;//团购价
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getPath() {
//                return path;
//            }
//
//            public void setPath(String path) {
//                this.path = path;
//            }
//
//            public String getTitle() {
//                return title;
//            }
//
//            public void setTitle(String title) {
//                this.title = title;
//            }
//
//            public String getStitle() {
//                return stitle;
//            }
//
//            public void setStitle(String stitle) {
//                this.stitle = stitle;
//            }
//
//            public String getMarket_price() {
//                return market_price;
//            }
//
//            public void setMarket_price(String market_price) {
//                this.market_price = market_price;
//            }
//
//            public String getPrice() {
//                return price;
//            }
//
//            public void setPrice(String price) {
//                this.price = price;
//            }
//
//            public ShowPriceBean getShow_price() {
//                return show_price;
//            }
//
//            public void setShow_price(ShowPriceBean show_price) {
//                this.show_price = show_price;
//            }
//
//
//            public static class ShowPriceBean
//            {
//                public String min;
//                public String max;
//                public String getMin() {
//                    return min;
//                }
//
//                public void setMin(String min) {
//                    this.min = min;
//                }
//
//                public String getMax() {
//                    return max;
//                }
//
//                public void setMax(String max) {
//                    this.max = max;
//                }
//
//
//            }
//
//        }
//    }
//    public static  class BannerBean extends SimpleBannerInfo
//    {
//        public int id;
//        public String path;
//        public String title;
//        public String url;
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getPath() {
//            return path;
//        }
//
//        public void setPath(String path) {
//            this.path = path;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//
//        @Override
//        public Object getXBannerUrl() {
//            return null;
//        }
//    }
//    public static class AdNewBean{
//        public int id;
//        public String title;
//        public String path;
//        public String url;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getPath() {
//            return path;
//        }
//
//        public void setPath(String path) {
//            this.path = path;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//
//    }



}
