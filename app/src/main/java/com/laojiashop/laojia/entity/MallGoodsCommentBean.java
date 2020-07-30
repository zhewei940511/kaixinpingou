package com.laojiashop.laojia.entity;

import java.util.List;

public class MallGoodsCommentBean {

    /**
     * total : 5
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":236,"user_id":59461,"order_id":22298,"content":"真的很棒棒哦","imgs":[{"name":"6b7169bffea920094e79938f76d90ced.jpg","url":"http://static.laojia99.com/Uploads/image/20200715/89856b9149dec7748c8364a4e4f74635.jpg"}],"create_time":"2020-07-15 15:40:24","update_time":"2020-07-15 15:40:24","status":0,"replay":"","replay_time":"1970-01-01 08:00:00","star":5,"goods_id":377,"user_name":"测试会员","phone_txt":"18888888880","headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","status_txt":"待审核"},{"id":232,"user_id":59461,"order_id":22259,"content":"121sdf","imgs":[],"create_time":"2020-05-29 17:19:44","update_time":"2020-05-29 17:19:44","status":0,"replay":"","replay_time":"1970-01-01 08:00:00","star":5,"goods_id":377,"user_name":"测试会员","phone_txt":"18888888880","headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","status_txt":"待审核"},{"id":230,"user_id":59461,"order_id":22264,"content":"第三方是大法官","imgs":[],"create_time":"2020-05-29 11:22:15","update_time":"2020-05-29 11:22:15","status":0,"replay":"","replay_time":"1970-01-01 08:00:00","star":5,"goods_id":377,"user_name":"测试会员","phone_txt":"18888888880","headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","status_txt":"待审核"},{"id":229,"user_id":59461,"order_id":22264,"content":"地方法规的","imgs":[],"create_time":"2020-05-29 11:20:05","update_time":"2020-05-29 11:20:05","status":0,"replay":"","replay_time":"1970-01-01 08:00:00","star":5,"goods_id":377,"user_name":"测试会员","phone_txt":"18888888880","headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","status_txt":"待审核"},{"id":226,"user_id":59461,"order_id":22265,"content":"很棒棒","imgs":[],"create_time":"2020-05-28 14:37:39","update_time":"2020-05-28 14:37:39","status":0,"replay":"","replay_time":"1970-01-01 08:00:00","star":5,"goods_id":377,"user_name":"测试会员","phone_txt":"18888888880","headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","status_txt":"待审核"}]
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
         * id : 236
         * user_id : 59461
         * order_id : 22298
         * content : 真的很棒棒哦
         * imgs : [{"name":"6b7169bffea920094e79938f76d90ced.jpg","url":"http://static.laojia99.com/Uploads/image/20200715/89856b9149dec7748c8364a4e4f74635.jpg"}]
         * create_time : 2020-07-15 15:40:24
         * update_time : 2020-07-15 15:40:24
         * status : 0
         * replay :
         * sku_name:1000ml*2瓶
         * replay_time : 1970-01-01 08:00:00
         * star : 5
         * goods_id : 377
         * user_name : 测试会员
         * phone_txt : 18888888880
         * headimgurl : http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg
         * status_txt : 待审核
         */

        private int id;
        private int user_id;
        private int order_id;
        private String content;
        private String create_time;
        private String update_time;
        private String sku_name;
        private int status;
        private String replay;
        private String replay_time;
        private int star;
        private int goods_id;
        private String user_name;
        private String phone_txt;
        private String headimgurl;
        private String status_txt;
        private List<ImgsBean> imgs;
        public String getSku_name() {
            return sku_name;
        }

        public void setSku_name(String sku_name) {
            this.sku_name = sku_name;
        }

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

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
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

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getReplay() {
            return replay;
        }

        public void setReplay(String replay) {
            this.replay = replay;
        }

        public String getReplay_time() {
            return replay_time;
        }

        public void setReplay_time(String replay_time) {
            this.replay_time = replay_time;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPhone_txt() {
            return phone_txt;
        }

        public void setPhone_txt(String phone_txt) {
            this.phone_txt = phone_txt;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getStatus_txt() {
            return status_txt;
        }

        public void setStatus_txt(String status_txt) {
            this.status_txt = status_txt;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * name : 6b7169bffea920094e79938f76d90ced.jpg
             * url : http://static.laojia99.com/Uploads/image/20200715/89856b9149dec7748c8364a4e4f74635.jpg
             */

            private String name;
            private String url;

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
        }
    }
}
