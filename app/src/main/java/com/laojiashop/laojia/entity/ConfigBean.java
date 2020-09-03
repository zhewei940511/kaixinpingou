package com.laojiashop.laojia.entity;

public class ConfigBean {
    /**
     * coin : {"tx_open":"1","tx_min":100,"tx_max":5000,"tx_count":5,"tx_day_max":5000,"tx_need_check":300,"tx_service":0.6,"tx_service_min":1,"tx_service_max":100,"tx_rules":"<p>金币提现规则<\/p><p>1、单笔提现额最低￥<strong><span style=\"color: rgb(255, 0, 0);\">100<\/span><\/strong>元，单日提现上限<strong><span style=\"color: rgb(255, 0, 0);\">5000<\/span><\/strong>元。<\/p><p>2、每个账户，每日申请提现次数最多为<span style=\"color: rgb(255, 0, 0);\"><strong>5<\/strong><\/span>次。<\/p><p>3、提现手续费比例 <span style=\"color: rgb(255, 0, 0);\"><strong>0.6%<\/strong><\/span>，最低<span style=\"color: rgb(255, 0, 0);\"><strong>1<\/strong><\/span>元。<\/p><p>4、单次提现超过<strong><span style=\"color: rgb(255, 0, 0);\">300<\/span><\/strong>元需审核，下个工作日到账。<\/p><p>5、提现金额必须是<span style=\"color: rgb(255, 0, 0);\"><strong>100<\/strong><\/span>的整数倍<\/p>","tx_multiple":100}
     * happy_bean : {"happy_bean_convert_third_party_points":"0","happy_bean_proportion_of_points_exchanged_on_third_party_platforms":1,"happy_bean_considerations_for_redeeming_third_party_points":"<p>开心豆兑换第三方平台积分注意事项<\/p>"}
     * bonus : {"bonus_exit_bonus":"1","bonus_exchange":"1"}
     * service_fee : <p>收款账户信息</p><p>我单位收款银行账户信息如下:</p><p>账户名称：陈守渝</p><p>帐号：6226 1911 0197 3958</p><p>开户行：[民生银行重庆市南岸区南岸支行]</p>
     */

    private CoinBean coin;
    private HappyBeanBean happy_bean;
    private BonusBean bonus;
    private String service_fee;

    public CoinBean getCoin() {
        return coin;
    }

    public void setCoin(CoinBean coin) {
        this.coin = coin;
    }

    public HappyBeanBean getHappy_bean() {
        return happy_bean;
    }

    public void setHappy_bean(HappyBeanBean happy_bean) {
        this.happy_bean = happy_bean;
    }

    public BonusBean getBonus() {
        return bonus;
    }

    public void setBonus(BonusBean bonus) {
        this.bonus = bonus;
    }

    public String getService_fee() {
        return service_fee;
    }

    public void setService_fee(String service_fee) {
        this.service_fee = service_fee;
    }

    public static class CoinBean {
        /**
         * tx_open : 1
         * tx_min : 100
         * tx_max : 5000
         * tx_count : 5
         * tx_day_max : 5000
         * tx_need_check : 300
         * tx_service : 0.6
         * tx_service_min : 1
         * tx_service_max : 100
         * tx_rules : <p>金币提现规则</p><p>1、单笔提现额最低￥<strong><span style="color: rgb(255, 0, 0);">100</span></strong>元，单日提现上限<strong><span style="color: rgb(255, 0, 0);">5000</span></strong>元。</p><p>2、每个账户，每日申请提现次数最多为<span style="color: rgb(255, 0, 0);"><strong>5</strong></span>次。</p><p>3、提现手续费比例 <span style="color: rgb(255, 0, 0);"><strong>0.6%</strong></span>，最低<span style="color: rgb(255, 0, 0);"><strong>1</strong></span>元。</p><p>4、单次提现超过<strong><span style="color: rgb(255, 0, 0);">300</span></strong>元需审核，下个工作日到账。</p><p>5、提现金额必须是<span style="color: rgb(255, 0, 0);"><strong>100</strong></span>的整数倍</p>
         * tx_multiple : 100
         */

        private String tx_open;
        private int tx_min;
        private int tx_max;
        private int tx_count;
        private int tx_day_max;
        private int tx_need_check;
        private double tx_service;
        private int tx_service_min;
        private int tx_service_max;
        private String tx_rules;
        private int tx_multiple;

        public String getTx_open() {
            return tx_open;
        }

        public void setTx_open(String tx_open) {
            this.tx_open = tx_open;
        }

        public int getTx_min() {
            return tx_min;
        }

        public void setTx_min(int tx_min) {
            this.tx_min = tx_min;
        }

        public int getTx_max() {
            return tx_max;
        }

        public void setTx_max(int tx_max) {
            this.tx_max = tx_max;
        }

        public int getTx_count() {
            return tx_count;
        }

        public void setTx_count(int tx_count) {
            this.tx_count = tx_count;
        }

        public int getTx_day_max() {
            return tx_day_max;
        }

        public void setTx_day_max(int tx_day_max) {
            this.tx_day_max = tx_day_max;
        }

        public int getTx_need_check() {
            return tx_need_check;
        }

        public void setTx_need_check(int tx_need_check) {
            this.tx_need_check = tx_need_check;
        }

        public double getTx_service() {
            return tx_service;
        }

        public void setTx_service(double tx_service) {
            this.tx_service = tx_service;
        }

        public int getTx_service_min() {
            return tx_service_min;
        }

        public void setTx_service_min(int tx_service_min) {
            this.tx_service_min = tx_service_min;
        }

        public int getTx_service_max() {
            return tx_service_max;
        }

        public void setTx_service_max(int tx_service_max) {
            this.tx_service_max = tx_service_max;
        }

        public String getTx_rules() {
            return tx_rules;
        }

        public void setTx_rules(String tx_rules) {
            this.tx_rules = tx_rules;
        }

        public int getTx_multiple() {
            return tx_multiple;
        }

        public void setTx_multiple(int tx_multiple) {
            this.tx_multiple = tx_multiple;
        }
    }

    public static class HappyBeanBean {
        /**
         * happy_bean_convert_third_party_points : 0
         * happy_bean_proportion_of_points_exchanged_on_third_party_platforms : 1
         * happy_bean_considerations_for_redeeming_third_party_points : <p>开心豆兑换第三方平台积分注意事项</p>
         */

        private String happy_bean_convert_third_party_points;
        private int happy_bean_proportion_of_points_exchanged_on_third_party_platforms;
        private String happy_bean_considerations_for_redeeming_third_party_points;

        public String getHappy_bean_convert_third_party_points() {
            return happy_bean_convert_third_party_points;
        }

        public void setHappy_bean_convert_third_party_points(String happy_bean_convert_third_party_points) {
            this.happy_bean_convert_third_party_points = happy_bean_convert_third_party_points;
        }

        public int getHappy_bean_proportion_of_points_exchanged_on_third_party_platforms() {
            return happy_bean_proportion_of_points_exchanged_on_third_party_platforms;
        }

        public void setHappy_bean_proportion_of_points_exchanged_on_third_party_platforms(int happy_bean_proportion_of_points_exchanged_on_third_party_platforms) {
            this.happy_bean_proportion_of_points_exchanged_on_third_party_platforms = happy_bean_proportion_of_points_exchanged_on_third_party_platforms;
        }

        public String getHappy_bean_considerations_for_redeeming_third_party_points() {
            return happy_bean_considerations_for_redeeming_third_party_points;
        }

        public void setHappy_bean_considerations_for_redeeming_third_party_points(String happy_bean_considerations_for_redeeming_third_party_points) {
            this.happy_bean_considerations_for_redeeming_third_party_points = happy_bean_considerations_for_redeeming_third_party_points;
        }
    }

    public static class BonusBean {
        /**
         * bonus_exit_bonus : 1
         * bonus_exchange : 1
         */

        private String bonus_exit_bonus;
        private String bonus_exchange;

        public String getBonus_exit_bonus() {
            return bonus_exit_bonus;
        }

        public void setBonus_exit_bonus(String bonus_exit_bonus) {
            this.bonus_exit_bonus = bonus_exit_bonus;
        }

        public String getBonus_exchange() {
            return bonus_exchange;
        }

        public void setBonus_exchange(String bonus_exchange) {
            this.bonus_exchange = bonus_exchange;
        }
    }


//    /**
//     * coin : {"tx_open":"1"}
//     * happy_bean : {"happy_bean_convert_third_party_points":"1","happy_bean_proportion_of_points_exchanged_on_third_party_platforms":1,"happy_bean_considerations_for_redeeming_third_party_points":"<p>开心豆兑换第三方平台积分需要后台审核<\/p>"}
//     * bonus : {"bonus_exit_bonus":"1","bonus_exchange":"0"}
//     * service_fee : <ul class="result-item list-paddingleft-2" style="list-style-type: none;"><li><p> 收款账户信息</p></li><li><p><br/></p></li><li><p> 我单位收款银行账户信息如下:</p></li><li><p><br/></p><p> 开户名称:开心拼购</p></li><li><p><br/></p></li></ul><p>       开户银行:[中国工商银行重庆江北支行]</p><ul class="result-item list-paddingleft-2" style="list-style-type: none;"><li><p> </p><p> 银行帐号:<span style="color: rgb(51, 51, 51); font-f....
//     */
//
//    private CoinBean coin;
//    private HappyBeanBean happy_bean;
//    private BonusBean bonus;
//    private String service_fee;
//
//    public CoinBean getCoin() {
//        return coin;
//    }
//
//    public void setCoin(CoinBean coin) {
//        this.coin = coin;
//    }
//
//    public HappyBeanBean getHappy_bean() {
//        return happy_bean;
//    }
//
//    public void setHappy_bean(HappyBeanBean happy_bean) {
//        this.happy_bean = happy_bean;
//    }
//
//    public BonusBean getBonus() {
//        return bonus;
//    }
//
//    public void setBonus(BonusBean bonus) {
//        this.bonus = bonus;
//    }
//
//    public String getService_fee() {
//        return service_fee;
//    }
//
//    public void setService_fee(String service_fee) {
//        this.service_fee = service_fee;
//    }
//
//    public static class CoinBean {
//        /**
//         * tx_open : 1
//         */
//
//        private String tx_open;
//
//        public String getTx_open() {
//            return tx_open;
//        }
//
//        public void setTx_open(String tx_open) {
//            this.tx_open = tx_open;
//        }
//    }
//
//    public static class HappyBeanBean {
//        /**
//         * happy_bean_convert_third_party_points : 1
//         * happy_bean_proportion_of_points_exchanged_on_third_party_platforms : 1
//         * happy_bean_considerations_for_redeeming_third_party_points : <p>开心豆兑换第三方平台积分需要后台审核</p>
//         */
//
//        private String happy_bean_convert_third_party_points;
//        private int happy_bean_proportion_of_points_exchanged_on_third_party_platforms;
//        private String happy_bean_considerations_for_redeeming_third_party_points;
//
//        public String getHappy_bean_convert_third_party_points() {
//            return happy_bean_convert_third_party_points;
//        }
//
//        public void setHappy_bean_convert_third_party_points(String happy_bean_convert_third_party_points) {
//            this.happy_bean_convert_third_party_points = happy_bean_convert_third_party_points;
//        }
//
//        public int getHappy_bean_proportion_of_points_exchanged_on_third_party_platforms() {
//            return happy_bean_proportion_of_points_exchanged_on_third_party_platforms;
//        }
//
//        public void setHappy_bean_proportion_of_points_exchanged_on_third_party_platforms(int happy_bean_proportion_of_points_exchanged_on_third_party_platforms) {
//            this.happy_bean_proportion_of_points_exchanged_on_third_party_platforms = happy_bean_proportion_of_points_exchanged_on_third_party_platforms;
//        }
//
//        public String getHappy_bean_considerations_for_redeeming_third_party_points() {
//            return happy_bean_considerations_for_redeeming_third_party_points;
//        }
//
//        public void setHappy_bean_considerations_for_redeeming_third_party_points(String happy_bean_considerations_for_redeeming_third_party_points) {
//            this.happy_bean_considerations_for_redeeming_third_party_points = happy_bean_considerations_for_redeeming_third_party_points;
//        }
//    }
//
//    public static class BonusBean {
//        /**
//         * bonus_exit_bonus : 1
//         * bonus_exchange : 0
//         */
//
//        private String bonus_exit_bonus;
//        private String bonus_exchange;
//
//        public String getBonus_exit_bonus() {
//            return bonus_exit_bonus;
//        }
//
//        public void setBonus_exit_bonus(String bonus_exit_bonus) {
//            this.bonus_exit_bonus = bonus_exit_bonus;
//        }
//
//        public String getBonus_exchange() {
//            return bonus_exchange;
//        }
//
//        public void setBonus_exchange(String bonus_exchange) {
//            this.bonus_exchange = bonus_exchange;
//        }
//    }
}
