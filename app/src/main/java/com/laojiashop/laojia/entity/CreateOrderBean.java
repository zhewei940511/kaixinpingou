package com.laojiashop.laojia.entity;

import java.util.List;

public class CreateOrderBean {

    /**
     * id : 146744
     * type : 2
     * order_no : 2007159550074972022
     * user_id : 59469
     * item_id : 19
     * pay_way : 1
     * payment_no : null
     * pay : 1005.00
     * status : 0
     * create_time : 2020-07-23 18:39:09
     * pay_time : --
     * content : []
     * coin : 5000.00
     * freeze_coin : 16.00
     * total : 6021.00
     * score : 0.00
     * pay_status : 90
     */

    private int id;
    private int type;
    private String order_no;
    private int user_id;
    private int item_id;
    private int pay_way;
    private Object payment_no;
    private String pay;
    private int status;
    private String create_time;
    private String pay_time;
    private String coin;
    private String freeze_coin;
    private String total;
    private String score;
    private int pay_status;
    private List<?> content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getPay_way() {
        return pay_way;
    }

    public void setPay_way(int pay_way) {
        this.pay_way = pay_way;
    }

    public Object getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(Object payment_no) {
        this.payment_no = payment_no;
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getFreeze_coin() {
        return freeze_coin;
    }

    public void setFreeze_coin(String freeze_coin) {
        this.freeze_coin = freeze_coin;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}
