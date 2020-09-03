package com.laojiashop.laojia.entity;

import java.util.List;

public class FeedbackBean {

    /**
     * total : 4
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":209,"user_id":59461,"content":"水电费公司的","imgs":[{"name":"会员中心.png","url":"http://static.laojia99.com/Uploads/image/20200710/387f9cf019ec6da3cf985a7f0387bc02.png"},{"name":"编辑地址.png","url":"http://static.laojia99.com/Uploads/image/20200710/61ea11a994e22188e9c7168e9d6f0812.png"}],"create_time":"2020-07-10 11:29:41","update_time":"2020-07-10 11:29:41","typeid":1,"status":1,"phone":"18888888880","relation_id":"0","level":1,"now_worker":0,"feed_no":"F2020071053545056","status_txt":"待处理","typename":"订单"},{"id":208,"user_id":59461,"content":"水电费公司的","imgs":"","create_time":"2020-07-10 11:25:27","update_time":"2020-07-10 11:25:27","typeid":1,"status":1,"phone":"18888888880","relation_id":"5215641561","level":1,"now_worker":0,"feed_no":"F2020071055571015","status_txt":"待处理","typename":"订单"},{"id":207,"user_id":59461,"content":"的双方各梵蒂冈","imgs":[{"name":"分红权兑换.png","url":"http://static.laojia99.com/Uploads/image/20200709/c07b229970eee530152fc94fc8ffdc52.png"},{"name":"订单详情-已取消.png","url":"http://static.laojia99.com/Uploads/image/20200709/6f58ab19b918e43650c99fc80f0c5d21.png"}],"create_time":"2020-07-09 17:15:48","update_time":"2020-07-09 17:15:48","typeid":3,"status":1,"phone":"18888888897","relation_id":"0","level":1,"now_worker":0,"feed_no":"F2020070952535256","status_txt":"待处理","typename":"合作"},{"id":206,"user_id":59461,"content":"但是的法国人","imgs":[],"create_time":"2020-05-29 11:39:07","update_time":"2020-05-29 11:39:07","typeid":1,"status":1,"phone":"18888888880","relation_id":"2005159054831767200","level":1,"now_worker":0,"feed_no":"F2020052998999857","status_txt":"待处理","typename":"订单"}]
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
         * id : 209
         * user_id : 59461
         * content : 水电费公司的
         * imgs : [{"name":"会员中心.png","url":"http://static.laojia99.com/Uploads/image/20200710/387f9cf019ec6da3cf985a7f0387bc02.png"},{"name":"编辑地址.png","url":"http://static.laojia99.com/Uploads/image/20200710/61ea11a994e22188e9c7168e9d6f0812.png"}]
         * create_time : 2020-07-10 11:29:41
         * update_time : 2020-07-10 11:29:41
         * typeid : 1
         * status : 1
         * phone : 18888888880
         * relation_id : 0
         * level : 1
         * now_worker : 0
         * feed_no : F2020071053545056
         * status_txt : 待处理
         * typename : 订单
         */

        private int id;
        private int user_id;
        private String content;
        private String create_time;
        private String update_time;
        private int typeid;
        private int status;
        private String phone;
        private String relation_id;
        private int level;
        private int now_worker;
        private String feed_no;
        private String status_txt;
        private String typename;
        private List<ImgsBean> imgs;

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

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRelation_id() {
            return relation_id;
        }

        public void setRelation_id(String relation_id) {
            this.relation_id = relation_id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getNow_worker() {
            return now_worker;
        }

        public void setNow_worker(int now_worker) {
            this.now_worker = now_worker;
        }

        public String getFeed_no() {
            return feed_no;
        }

        public void setFeed_no(String feed_no) {
            this.feed_no = feed_no;
        }

        public String getStatus_txt() {
            return status_txt;
        }

        public void setStatus_txt(String status_txt) {
            this.status_txt = status_txt;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * name : 会员中心.png
             * url : http://static.laojia99.com/Uploads/image/20200710/387f9cf019ec6da3cf985a7f0387bc02.png
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
