package com.laojiashop.laojia.entity;

import java.util.List;

public class SpellgrouprecordsBean {

    /**
     * tuanStatus : 2
     * joinList : [{"id":168161936,"user_id":59462,"tid":168021615,"create_time":"11分钟前","is_winner":1,"address_id":308,"total":"4399.00","goods_pay":"4399.00","freight":"0.00","coin":"4399.00","pay":"0.00","status":1,"pay_time":1596766625,"robot":0,"has_back":0,"mark":"","name":"测试初级导购","phone":"18888888881","headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","ctime":1596766625},{"id":168161935,"user_id":59463,"tid":168021615,"create_time":"11分钟前","is_winner":0,"address_id":307,"total":"4399.00","goods_pay":"4399.00","freight":"0.00","coin":"4399.00","pay":"0.00","status":1,"pay_time":1596766622,"robot":0,"has_back":0,"mark":"","name":"测试高级导购","phone":"18888888882","headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","ctime":1596766622},{"id":168161934,"user_id":59461,"tid":168021615,"create_time":"11分钟前","is_winner":0,"address_id":319,"total":"4399.00","goods_pay":"4399.00","freight":"0.00","coin":"4399.00","pay":"0.00","status":1,"pay_time":1596766603,"robot":0,"has_back":0,"mark":"","name":"测试会员","phone":"18888888880","headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","ctime":1596766603}]
     * coinList : []
     * scoreList : []
     * myPrize : {"score":0,"coin":0}
     */

    private int tuanStatus;
    private MyPrizeBean myPrize;
    private List<JoinListBean> joinList;

    public int getTuanStatus() {
        return tuanStatus;
    }

    public void setTuanStatus(int tuanStatus) {
        this.tuanStatus = tuanStatus;
    }

    public MyPrizeBean getMyPrize() {
        return myPrize;
    }

    public void setMyPrize(MyPrizeBean myPrize) {
        this.myPrize = myPrize;
    }

    public List<JoinListBean> getJoinList() {
        return joinList;
    }

    public void setJoinList(List<JoinListBean> joinList) {
        this.joinList = joinList;
    }

    public static class MyPrizeBean {
    }

    public static class JoinListBean {
        /**
         * id : 168161936
         * user_id : 59462
         * tid : 168021615
         * create_time : 11分钟前
         * is_winner : 1
         * address_id : 308
         * total : 4399.00
         * goods_pay : 4399.00
         * freight : 0.00
         * coin : 4399.00
         * pay : 0.00
         * status : 1
         * pay_time : 1596766625
         * robot : 0
         * has_back : 0
         * mark :
         * name : 测试初级导购
         * phone : 18888888881
         * headimgurl : http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg
         * ctime : 1596766625
         */

        private int id;
        private int user_id;
        private int tid;
        private String create_time;
        private int is_winner;
        private int address_id;
        private String total;
        private String goods_pay;
        private String freight;
        private String coin;
        private String pay;
        private int status;
        private int pay_time;
        private int robot;
        private int has_back;
        private String mark;
        private String name;
        private String phone;
        private String headimgurl;
        private int ctime;

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

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getIs_winner() {
            return is_winner;
        }

        public void setIs_winner(int is_winner) {
            this.is_winner = is_winner;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getGoods_pay() {
            return goods_pay;
        }

        public void setGoods_pay(String goods_pay) {
            this.goods_pay = goods_pay;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPay_time() {
            return pay_time;
        }

        public void setPay_time(int pay_time) {
            this.pay_time = pay_time;
        }

        public int getRobot() {
            return robot;
        }

        public void setRobot(int robot) {
            this.robot = robot;
        }

        public int getHas_back() {
            return has_back;
        }

        public void setHas_back(int has_back) {
            this.has_back = has_back;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
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

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }
    }
}
