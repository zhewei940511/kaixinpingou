package com.laojiashop.laojia.entity;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.List;

public class HomePageBean {


    private List<BannerBean> banner;
    private List<AdNewBean> ad_new;
    private List<SpecialBean> special;
    public List<AdNewBean> getAd_new() {
        return ad_new;
    }
    public void setAd_new(List<AdNewBean> ad_new) {
        this.ad_new = ad_new;
    }
    public List<BannerBean> getBanner() {
       return banner;
   }
    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }
    public List<SpecialBean> getSpecial() {
        return special;
    }
    public void setSpecial(List<SpecialBean> special) {
        this.special = special;
    }
    public static class SpecialBean
    {
        public String title;//标题
        public List<GoodsListBean> goods_list;
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }


        public static class GoodsListBean
        {
            public int id;
            public String path;
            public String title;
            public String stitle;
            public String market_price;//零售价
            public String price;//商城价
            public ShowPriceBean show_price;//团购价
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

            public ShowPriceBean getShow_price() {
                return show_price;
            }

            public void setShow_price(ShowPriceBean show_price) {
                this.show_price = show_price;
            }


            public static class ShowPriceBean
            {
                public String min;
                public String max;
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
    public static  class BannerBean extends SimpleBannerInfo
    {
        public int id;
        public String path;
        public String title;
        public String url;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


        @Override
        public Object getXBannerUrl() {
            return null;
        }
    }
    public static class AdNewBean{
        public int id;
        public String title;
        public String path;
        public String url;

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


    }

//    private List<TypesBean> types;
//    private List<SpecialBean> special;
//    private List<BannerBean> banner;
//    private List<AdNewBean> ad_new;
//
//    public List<TypesBean> getTypes() {
//        return types;
//    }
//
//    public void setTypes(List<TypesBean> types) {
//        this.types = types;
//    }
//
//    public List<SpecialBean> getSpecial() {
//        return special;
//    }
//
//    public void setSpecial(List<SpecialBean> special) {
//        this.special = special;
//    }
//
//    public List<BannerBean> getBanner() {
//        return banner;
//    }
//
//    public void setBanner(List<BannerBean> banner) {
//        this.banner = banner;
//    }
//
//    public List<AdNewBean> getAd_new() {
//        return ad_new;
//    }
//
//    public void setAd_new(List<AdNewBean> ad_new) {
//        this.ad_new = ad_new;
//    }
//
//    public static class TypesBean {
//        /**
//         * id : 21
//         * typename : 食品生鲜
//         * pid : 0
//         * path : http://static.laojia99.com/Uploads/image/20200606/498a7e690a66d0879f7bb798df849382.png
//         * banner : [{"name":"5.jpeg","url":"http://static.laojia99.com/Uploads/image/20200608/f88d333c969fca0ff789a521c248b6bd.jpeg","uid":1591584300008,"status":"success","path":"/mall/list?typeid=1"}]
//         * level : [{"id":39,"typename":"饮料","pid":21,"path":"http://static.laojia99.com/Uploads/image/20200610/97e37aaab9d75af60ffea050c50dda36.png"},{"id":38,"typename":"测试五五","pid":21,"path":null},{"id":37,"typename":"测试四四","pid":21,"path":"http://static.laojia99.com/Uploads/image/20200610/838d3f7d63ae1e38cef24a1d4194d29e.png"},{"id":36,"typename":"测试三三","pid":21,"path":"http://static.laojia99.com/Uploads/image/20200610/801332bc2639d0214feaf1eac5d894f1.png"},{"id":35,"typename":"测试二二","pid":21,"path":null}]
//         */
//
//        private int id;
//        private String typename;
//        private int pid;
//        private String path;
//        private List<BannerBean> banner;
//        private List<LevelBean> level;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getTypename() {
//            return typename;
//        }
//
//        public void setTypename(String typename) {
//            this.typename = typename;
//        }
//
//        public int getPid() {
//            return pid;
//        }
//
//        public void setPid(int pid) {
//            this.pid = pid;
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
//        public List<BannerBean> getBanner() {
//            return banner;
//        }
//
//        public void setBanner(List<BannerBean> banner) {
//            this.banner = banner;
//        }
//
//        public List<LevelBean> getLevel() {
//            return level;
//        }
//
//        public void setLevel(List<LevelBean> level) {
//            this.level = level;
//        }
//
//        public static class BannerBean {
//            /**
//             * name : 5.jpeg
//             * url : http://static.laojia99.com/Uploads/image/20200608/f88d333c969fca0ff789a521c248b6bd.jpeg
//             * uid : 1591584300008
//             * status : success
//             * path : /mall/list?typeid=1
//             */
//
//            private String name;
//            private String url;
//            private long uid;
//            private String status;
//            private String path;
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getUrl() {
//                return url;
//            }
//
//            public void setUrl(String url) {
//                this.url = url;
//            }
//
//            public long getUid() {
//                return uid;
//            }
//
//            public void setUid(long uid) {
//                this.uid = uid;
//            }
//
//            public String getStatus() {
//                return status;
//            }
//
//            public void setStatus(String status) {
//                this.status = status;
//            }
//
//            public String getPath() {
//                return path;
//            }
//
//            public void setPath(String path) {
//                this.path = path;
//            }
//        }
//
//        public static class LevelBean {
//            /**
//             * id : 39
//             * typename : 饮料
//             * pid : 21
//             * path : http://static.laojia99.com/Uploads/image/20200610/97e37aaab9d75af60ffea050c50dda36.png
//             */
//
//            private int id;
//            private String typename;
//            private int pid;
//            private String path;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getTypename() {
//                return typename;
//            }
//
//            public void setTypename(String typename) {
//                this.typename = typename;
//            }
//
//            public int getPid() {
//                return pid;
//            }
//
//            public void setPid(int pid) {
//                this.pid = pid;
//            }
//
//            public String getPath() {
//                return path;
//            }
//
//            public void setPath(String path) {
//                this.path = path;
//            }
//        }
//    }
//
//    public static class SpecialBean {
//        /**
//         * id : 56
//         * title : 拼购专区
//         * path : http://static.lanxuankeji.com/Uploads/image/20200107/0f7730243eed7aa7dac5bb107ea029bd.jpg
//         * show_img : 1
//         * goods_list : [{"show_price":{"min":"50.9","max":"50.9"},"shequ":[],"id":377,"freeze_coin":0,"path":"http://static.laojia99.com/Uploads/image/20200326/527e5c5572f273c221598fbfa9fadd6b.jpg","title":"强生婴儿牛奶沐浴露套装1000ml*2瓶","stitle":"婴儿洗浴无刺激护肤男女儿童沐浴液","market_price":"99.90","price":"50.90","tags":[],"tuangou":[],"price_arr":[{"sku":1661,"sku_name":"1000ml*2瓶","inventory":100421,"price":"50.9","market_price":"","cost_price":"50.9","score":0,"max":0,"goods_num":"","path":"","title1":"1000ml*2瓶"}],"price_range":"50.90","lowest_price":"50.90"},{"show_price":{"min":"0","max":"0"},"shequ":[],"id":380,"freeze_coin":5,"path":"http://static.laojia99.com/Uploads/image/20200326/3344d3ca29f074399ac504cafcffd307.jpg","title":"舒蕾洗发水山茶花焗油莹亮600ml*2瓶","stitle":"深度滋养精华洗发水 家庭装","market_price":"99.90","price":"45.90","tags":[],"tuangou":[],"price_arr":[{"sku":1664,"sku_name":"600ml*2瓶","inventory":100005,"price":"0","market_price":"","cost_price":"45.9","score":0,"max":"5","path":"","goods_num":"","title1":"600ml*2瓶"},{"sku":1670,"sku_name":"100ml","inventory":99988,"price":"0","market_price":"","cost_price":"1110","score":0,"max":0,"path":"","goods_num":"","title1":"100ml"}],"price_range":"45.90","lowest_price":"45.90"},{"show_price":{"min":"99","max":"99"},"shequ":[],"id":395,"freeze_coin":0,"path":"http://static.laojia99.com/Uploads/image/20200329/474f7b1b96a2a111430c5aa6c8c22eaf.jpg","title":"羊奶粉中老年成人营养羊初乳蛋白粉铁罐装350克*2罐","stitle":"","market_price":"159.00","price":"139.00","tags":["健康自我，健康中国","提供关键营养","无糖放心喝"],"tuangou":[{"title":"","price_id":0,"number":"3","price":"99","sku":[],"max":"0"},{"title":"","price_id":0,"number":"6","price":"99","sku":[],"max":"0"}],"price_arr":[{"sku":1681,"sku_name":"2罐*350g","inventory":100137,"price":"139","market_price":"","cost_price":"1","score":0,"max":0,"goods_num":"","path":"","title1":"2罐*350g"}],"price_range":"139.00","lowest_price":"139.00"},{"show_price":{"min":"1","max":"1"},"shequ":[],"id":333,"freeze_coin":20,"path":"http://static.laojia99.com/Uploads/image/20200321/7169bb85a38d2fd058643c38310b22e1.jpg","title":"新起点免疫球蛋1盒","stitle":"央视推荐 驻扎武汉 驰援疫情一线专用产品","market_price":"298.00","price":"228.00","tags":["免疫力，真实力","20年专注免疫力研究","CCTV广告产品"],"tuangou":[],"price_arr":[{"sku":1603,"sku_name":"6g","inventory":100565,"price":"1","market_price":"","cost_price":"1","score":0,"max":0,"goods_num":"","path":"","title1":"6g"}],"price_range":"1.00","lowest_price":"1.00"},{"show_price":{"min":"90","max":"100"},"shequ":[{"title":"","price_id":0,"number":10,"price":"90","max":0,"sku":[]},{"title":"","price_id":0,"number":10,"price":"100","max":0,"sku":[]}],"id":419,"freeze_coin":0,"path":"http://static.laojia99.com/Uploads/image/20200330/24b09ecaf17883046119afa801b79ea8.jpg","title":"唐贵人护肤、洗护、美妆十件套","stitle":"+100开心豆","market_price":"2228.00","price":"1613.00","tags":["100开心豆+50现金"],"tuangou":[],"price_arr":[{"sku":1654,"sku_name":"200ml","inventory":100147,"price":"50","market_price":"","cost_price":"10","score":"20","max":0,"goods_num":"","path":"","title1":"200ml"},{"sku":1655,"sku_name":"150ml","inventory":100255,"price":"80","market_price":"","cost_price":"10","score":"1000","max":0,"goods_num":"","path":"","title1":"150ml"}],"price_range":"50.00","lowest_price":"50.00"},{"show_price":{"min":"169","max":"169"},"shequ":[],"id":332,"freeze_coin":0,"path":"http://static.laojia99.com/Uploads/image/20200326/bfc6f059417b51c8da5bc7a73e656b9b.jpg","title":"三千米甜高品质藏巢蜜500g*2瓶","stitle":"\u201c吃10瓶普通蜂蜜，不如吃一瓶真正的藏巢蜜\u201d\n蜂蜜产品已远销欧洲、中东、日本、马来西亚、新加坡等40多个国家和地区！","market_price":"236.00","price":"218.00","tags":["出口品质，蜂场直供","嚼着吃的蜂蜜","橘子花的秘密"],"tuangou":[{"title":"","price_id":0,"number":"3","price":"169","sku":[],"max":"1"},{"title":"","price_id":0,"number":"6","price":"169","sku":[],"max":"1"}],"price_arr":[{"sku":1613,"sku_name":"500g*2瓶","inventory":99970,"price":"218","market_price":"","cost_price":"1","score":0,"max":0,"goods_num":"","path":"","title1":"500g*2瓶"}],"price_range":"218.00","lowest_price":"218.00"},{"show_price":{"min":"289","max":"998"},"shequ":[],"id":334,"freeze_coin":0,"path":"http://static.laojia99.com/Uploads/image/20200315/6973fdbdf595c121e708b96e3013e96d.jpg","title":"汤臣倍健白质粉600g+摇摇杯 礼盒装","stitle":" 男女性成人中老年孕妇增强免疫力","market_price":"499.00","price":"289.00","tags":[],"tuangou":[],"price_arr":[{"sku":1623,"sku_name":"礼盒装","inventory":100030,"price":"289","market_price":"","cost_price":"289","score":0,"max":0,"path":"","goods_num":"","title1":"礼盒装"},{"sku":1625,"sku_name":"30g*2盒","inventory":100013,"price":"388","market_price":"","cost_price":"388","score":0,"max":0,"path":"","goods_num":"","title1":"30g*2盒"},{"sku":1626,"sku_name":"72g*4盒","inventory":99999,"price":"998","market_price":"","cost_price":"998","score":0,"max":0,"path":"","goods_num":"","title1":"72g*4盒"}],"price_range":"289.00~998.00","lowest_price":"289.00"},{"show_price":{"min":"33.9","max":"45.9"},"shequ":[],"id":352,"freeze_coin":0,"path":"http://static.laojia99.com/Uploads/image/20200319/261fbaa436647ee6233016b8392e9efb.jpg","title":"大宝SOD蜜乳液男女面霜100ml*3","stitle":"30年口碑之选 SOD抗氧化 长效滋润 补水保湿","market_price":"69.90","price":"33.90","tags":[],"tuangou":[],"price_arr":[{"sku":1645,"sku_name":"100ml*3瓶","inventory":100097,"price":"33.9","market_price":"","cost_price":"33.9","score":0,"max":0,"goods_num":"","path":"","title1":"100ml*3瓶"},{"sku":1646,"sku_name":"200ml*3瓶","inventory":100033,"price":"45.9","market_price":"","cost_price":"45.9","score":0,"max":0,"goods_num":"","path":"","title1":"200ml*3瓶"}],"price_range":"33.90","lowest_price":"33.90"},{"show_price":{"min":"24.8","max":"24.8"},"shequ":[],"id":354,"freeze_coin":0,"path":"http://static.laojia99.com/Uploads/image/20200319/0fd4bc0a6b49a455f4e25bbb8f390bc9.jpg","title":"大宝 SOD蛋白霜50g","stitle":"男女面霜 补水保湿 深层滋养肌肤","market_price":"89.00","price":"24.80","tags":[],"tuangou":[],"price_arr":[{"sku":1648,"sku_name":"50g","inventory":99998,"price":"24.8","market_price":"","cost_price":"24.8","score":0,"max":0,"goods_num":"","path":"","title1":"50g"}],"price_range":"24.80","lowest_price":"24.80"},{"show_price":{"min":"13.9","max":"13.9"},"shequ":[],"id":355,"freeze_coin":0,"path":"http://static.laojia99.com/Uploads/image/20200319/408ab6c1943ea4ead21ee254cf29cdfd.jpg","title":"大宝 SOD滋润霜50g","stitle":"面霜男女 补水保湿霜 长效滋润","market_price":"69.00","price":"13.90","tags":[],"tuangou":[],"price_arr":[{"sku":1648,"sku_name":"50g","inventory":99999,"price":"13.9","market_price":"","cost_price":"13.9","score":0,"max":0,"goods_num":"","path":"","title1":"50g"}],"price_range":"13.90","lowest_price":"13.90"}]
//         * display_the_number_of_goods : 0
//         */
//
//        private int id;
//        private String title;
//        private String path;
//        private int show_img;
//        private int display_the_number_of_goods;
//        private List<GoodsListBean> goods_list;
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
//        public int getShow_img() {
//            return show_img;
//        }
//
//        public void setShow_img(int show_img) {
//            this.show_img = show_img;
//        }
//
//        public int getDisplay_the_number_of_goods() {
//            return display_the_number_of_goods;
//        }
//
//        public void setDisplay_the_number_of_goods(int display_the_number_of_goods) {
//            this.display_the_number_of_goods = display_the_number_of_goods;
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
//        public static class GoodsListBean {
//            /**
//             * show_price : {"min":"50.9","max":"50.9"}
//             * shequ : []
//             * id : 377
//             * freeze_coin : 0
//             * path : http://static.laojia99.com/Uploads/image/20200326/527e5c5572f273c221598fbfa9fadd6b.jpg
//             * title : 强生婴儿牛奶沐浴露套装1000ml*2瓶
//             * stitle : 婴儿洗浴无刺激护肤男女儿童沐浴液
//             * market_price : 99.90
//             * price : 50.90
//             * tags : []
//             * tuangou : []
//             * price_arr : [{"sku":1661,"sku_name":"1000ml*2瓶","inventory":100421,"price":"50.9","market_price":"","cost_price":"50.9","score":0,"max":0,"goods_num":"","path":"","title1":"1000ml*2瓶"}]
//             * price_range : 50.90
//             * lowest_price : 50.90
//             */
//
//            private ShowPriceBean show_price;
//            private int id;
//            private int freeze_coin;
//            private String path;
//            private String title;
//            private String stitle;
//            private String market_price;
//            private String price;
//            private String price_range;
//            private String lowest_price;
//            private List<?> shequ;
//            private List<?> tags;
//            private List<?> tuangou;
//            private List<PriceArrBean> price_arr;
//
//            public ShowPriceBean getShow_price() {
//                return show_price;
//            }
//
//            public void setShow_price(ShowPriceBean show_price) {
//                this.show_price = show_price;
//            }
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getFreeze_coin() {
//                return freeze_coin;
//            }
//
//            public void setFreeze_coin(int freeze_coin) {
//                this.freeze_coin = freeze_coin;
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
//            public String getPrice_range() {
//                return price_range;
//            }
//
//            public void setPrice_range(String price_range) {
//                this.price_range = price_range;
//            }
//
//            public String getLowest_price() {
//                return lowest_price;
//            }
//
//            public void setLowest_price(String lowest_price) {
//                this.lowest_price = lowest_price;
//            }
//
//            public List<?> getShequ() {
//                return shequ;
//            }
//
//            public void setShequ(List<?> shequ) {
//                this.shequ = shequ;
//            }
//
//            public List<?> getTags() {
//                return tags;
//            }
//
//            public void setTags(List<?> tags) {
//                this.tags = tags;
//            }
//
//            public List<?> getTuangou() {
//                return tuangou;
//            }
//
//            public void setTuangou(List<?> tuangou) {
//                this.tuangou = tuangou;
//            }
//
//            public List<PriceArrBean> getPrice_arr() {
//                return price_arr;
//            }
//
//            public void setPrice_arr(List<PriceArrBean> price_arr) {
//                this.price_arr = price_arr;
//            }
//
//            public static class ShowPriceBean {
//                /**
//                 * min : 50.9
//                 * max : 50.9
//                 */
//
//                private String min;
//                private String max;
//
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
//            }
//
//            public static class PriceArrBean {
//                /**
//                 * sku : 1661
//                 * sku_name : 1000ml*2瓶
//                 * inventory : 100421
//                 * price : 50.9
//                 * market_price :
//                 * cost_price : 50.9
//                 * score : 0
//                 * max : 0
//                 * goods_num :
//                 * path :
//                 * title1 : 1000ml*2瓶
//                 */
//
//                private int sku;
//                private String sku_name;
//                private int inventory;
//                private String price;
//                private String market_price;
//                private String cost_price;
//                private int score;
//                private int max;
//                private String goods_num;
//                private String path;
//                private String title1;
//
//                public int getSku() {
//                    return sku;
//                }
//
//                public void setSku(int sku) {
//                    this.sku = sku;
//                }
//
//                public String getSku_name() {
//                    return sku_name;
//                }
//
//                public void setSku_name(String sku_name) {
//                    this.sku_name = sku_name;
//                }
//
//                public int getInventory() {
//                    return inventory;
//                }
//
//                public void setInventory(int inventory) {
//                    this.inventory = inventory;
//                }
//
//                public String getPrice() {
//                    return price;
//                }
//
//                public void setPrice(String price) {
//                    this.price = price;
//                }
//
//                public String getMarket_price() {
//                    return market_price;
//                }
//
//                public void setMarket_price(String market_price) {
//                    this.market_price = market_price;
//                }
//
//                public String getCost_price() {
//                    return cost_price;
//                }
//
//                public void setCost_price(String cost_price) {
//                    this.cost_price = cost_price;
//                }
//
//                public int getScore() {
//                    return score;
//                }
//
//                public void setScore(int score) {
//                    this.score = score;
//                }
//
//                public int getMax() {
//                    return max;
//                }
//
//                public void setMax(int max) {
//                    this.max = max;
//                }
//
//                public String getGoods_num() {
//                    return goods_num;
//                }
//
//                public void setGoods_num(String goods_num) {
//                    this.goods_num = goods_num;
//                }
//
//                public String getPath() {
//                    return path;
//                }
//
//                public void setPath(String path) {
//                    this.path = path;
//                }
//
//                public String getTitle1() {
//                    return title1;
//                }
//
//                public void setTitle1(String title1) {
//                    this.title1 = title1;
//                }
//            }
//        }
//    }
//
//    public static class BannerBean extends SimpleBannerInfo {
//        /**
//         * id : 185
//         * title : 商城1
//         * path : http://static.laojia99.com/Uploads/image/20200317/4ca2ee30bd97a077e87552a9057c63ce.jpg
//         * url : #
//         */
//
//        private int id;
//        private String title;
//        private String path;
//        private String url;
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
//        @Override
//        public Object getXBannerUrl() {
//            return null;
//        }
//    }
//
//    public static class AdNewBean {
//        /**
//         * id : 195
//         * title : 食品生鲜
//         * path : http://static.laojia99.com/Uploads/image/20200513/390afb0bd38c8da13a2631a19488a0e9.png
//         * url : /mall/list?typeid=21
//         */
//
//        private int id;
//        private String title;
//        private String path;
//        private String url;
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
//    }
}
