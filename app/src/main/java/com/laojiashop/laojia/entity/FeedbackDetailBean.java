package com.laojiashop.laojia.entity;

import java.util.List;

public class FeedbackDetailBean {

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
     * log : [{"id":32,"f_id":209,"user_type":1,"user_id":59461,"content":"提交反馈","imgs":"","create_time":"2020-07-10 11:29:41","user_info":{"id":59461,"name":"测试会员","phone":"18888888880"}}]
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
    private List<LogBean> log;

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

    public List<LogBean> getLog() {
        return log;
    }

    public void setLog(List<LogBean> log) {
        this.log = log;
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

    public static class LogBean {
        /**
         * id : 32
         * f_id : 209
         * user_type : 1
         * user_id : 59461
         * content : 提交反馈
         * imgs :
         * create_time : 2020-07-10 11:29:41
         * user_info : {"id":59461,"name":"测试会员","phone":"18888888880"}
         */

        private int id;
        private int f_id;
        private int user_type;
        private int user_id;
        private String content;
        private String imgs;
        private String create_time;
        private UserInfoBean user_info;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getF_id() {
            return f_id;
        }

        public void setF_id(int f_id) {
            this.f_id = f_id;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
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

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class UserInfoBean {
            /**
             * id : 59461
             * name : 测试会员
             * phone : 18888888880
             */

            private int id;
            private String name;
            private String phone;

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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
