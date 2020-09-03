package com.laojiashop.laojia.entity;

public class FlieUploadBean {

    /**
     * path : http://static.laojia99.com/Uploads/image/20200805/e2919d479c75ec5523ec511a38db207b.png
     * thumb : http://static.laojia99.com/Uploads/image/20200805/e2919d479c75ec5523ec511a38db207b.png
     * savename : Screenshot_2020-06-16-14-05-57.png
     * ext : png
     * file : {"name":"Screenshot_2020-06-16-14-05-57.png","type":"image/jpg","tmp_name":"/tmp/phpWXo7m1","error":0,"size":18099}
     * md5 : 819ceb7389adc0e0bcc5df552236041f
     * create_time :
     * id : 11997
     */

    private String path;
    private String thumb;
    private String savename;
    private String ext;
    private FileBean file;
    private String md5;
    private String create_time;
    private String id;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSavename() {
        return savename;
    }

    public void setSavename(String savename) {
        this.savename = savename;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class FileBean {
        /**
         * name : Screenshot_2020-06-16-14-05-57.png
         * type : image/jpg
         * tmp_name : /tmp/phpWXo7m1
         * error : 0
         * size : 18099
         */

        private String name;
        private String type;
        private String tmp_name;
        private int error;
        private int size;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTmp_name() {
            return tmp_name;
        }

        public void setTmp_name(String tmp_name) {
            this.tmp_name = tmp_name;
        }

        public int getError() {
            return error;
        }

        public void setError(int error) {
            this.error = error;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
