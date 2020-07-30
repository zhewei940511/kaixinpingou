package com.laojiashop.laojia.entity;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;
import java.util.List;

public class ClassificationPageBean implements Serializable {


    /**
     * id : 55
     * pid : 0
     * typename : 汽车品牌
     * path :
     * banner : [{"name":"6b7169bffea920094e79938f76d90ced.jpg","url":"http://static.laojia99.com/Uploads/image/20200608/c75b2ed32aed69f90325d0021edf60c4.jpg","uid":1591582406058,"status":"success","path":"www.baidu.com"},{"name":"6b7169bffea920094e79938f76d90ced.jpg","url":"http://static.laojia99.com/Uploads/image/20200608/dc17d2f3037a4a705cf50a5d948cd9b7.jpg","uid":1591582417565,"status":"success","path":"www.hao123.com"}]
     * son : [{"id":56,"pid":55,"typename":"宝马","path":"","banner":[]}]
     */

    private int id;
    private int pid;
    private String typename;
    private String path;
    private List<BannerBean> banner;
    private List<SonBean> son;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<SonBean> getSon() {
        return son;
    }

    public void setSon(List<SonBean> son) {
        this.son = son;
    }

    public static class BannerBean extends SimpleBannerInfo implements Serializable {
        /**
         * name : 6b7169bffea920094e79938f76d90ced.jpg
         * url : http://static.laojia99.com/Uploads/image/20200608/c75b2ed32aed69f90325d0021edf60c4.jpg
         * uid : 1591582406058
         * status : success
         * path : www.baidu.com
         */

        private String name;
        private String url;
        private long uid;
        private String status;
        private String path;

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

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        @Override
        public Object getXBannerUrl() {
            return null;
        }
    }

    public static class SonBean implements Serializable {
        //哪一個是標題typename
        /**
         * id : 56
         * pid : 55
         * typename : 宝马
         * path :
         * banner : []
         */

        private int id;
        private int pid;
        private String typename;
        private String path;
        private List<?> banner;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<?> getBanner() {
            return banner;
        }

        public void setBanner(List<?> banner) {
            this.banner = banner;
        }
    }
}
