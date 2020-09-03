package com.laojiashop.laojia.entity;

import java.util.List;

public class ServiceFeeBean {
    /**
     * recordInfo : {"id":8,"user_id":59469,"level":5,"levelStr":"代理商","service_fee":"100000.00","need_achieve_performance":2000000,"assessment_cycle":180,"status":3,"statusStr":"审核通过","images":["http://static.laojia99.com/Uploads/image/20200311/9a2693fd9303928defe7f43fdb598bd9.jpg",""],"assessment_status":2,"remark":null,"create_time":"2020-06-19 11:51:42","review_time":1597732174,"review_time_str":"2020-08-18 14:29:34","performance":0,"front_display_status":"查看任务"}
     * level : [{"level":3,"levelStr":"导购","margin":1000,"performance":20000,"cycle":30,"rule":""},{"level":4,"levelStr":"经销商","margin":10000,"performance":200000,"cycle":90,"rule":""},{"level":5,"levelStr":"代理商","margin":100000,"performance":2000000,"cycle":180,"rule":""}]
     * user_level : 5
     * user_level_str : 代理商
     * event_description : <p>亲爱的开心拼购朋友：</p><p>&nbsp; &nbsp; &nbsp; &nbsp;如您还在为升级身份而感到困惑，或是在升级过程中因为团队人数遇到瓶颈，停滞不前，不妨可以尝试加入《预交服务费提升身份》活动，随时开通，立即体验高级身份带来的全部收益。</p><p>&nbsp; &nbsp; &nbsp; &nbsp;通过《预交服务费提升身份》活动而获得的永久身份，不受晋级条件约束。如果您还在为升级条件而感到烦恼，就快来体验吧！</p>
     * collect_money_info : <p>收款账户信息</p><p>我单位收款银行账户信息如下:</p><p>账户名称：陈守渝</p><p>帐号：6226 1911 0197 3958</p><p>开户行：[民生银行重庆市南岸区南岸支行]</p>
     */

    private RecordInfoBean recordInfo;
    private int user_level;
    private String user_level_str;
    private String event_description;
    private String collect_money_info;
    private List<LevelBean> level;

    public RecordInfoBean getRecordInfo() {
        return recordInfo;
    }

    public void setRecordInfo(RecordInfoBean recordInfo) {
        this.recordInfo = recordInfo;
    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }

    public String getUser_level_str() {
        return user_level_str;
    }

    public void setUser_level_str(String user_level_str) {
        this.user_level_str = user_level_str;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getCollect_money_info() {
        return collect_money_info;
    }

    public void setCollect_money_info(String collect_money_info) {
        this.collect_money_info = collect_money_info;
    }

    public List<LevelBean> getLevel() {
        return level;
    }

    public void setLevel(List<LevelBean> level) {
        this.level = level;
    }

    public static class RecordInfoBean {
        /**
         * id : 8
         * user_id : 59469
         * level : 5
         * levelStr : 代理商
         * service_fee : 100000.00
         * need_achieve_performance : 2000000
         * assessment_cycle : 180
         * status : 3
         * statusStr : 审核通过
         * images : ["http://static.laojia99.com/Uploads/image/20200311/9a2693fd9303928defe7f43fdb598bd9.jpg",""]
         * assessment_status : 2
         * remark : null
         * create_time : 2020-06-19 11:51:42
         * review_time : 1597732174
         * review_time_str : 2020-08-18 14:29:34
         * performance : 0
         * front_display_status : 查看任务
         */

        private int id;
        private int user_id;
        private int level;
        private long remaining_time_for_assessment;
        private String levelStr;
        private String service_fee;
        private int need_achieve_performance;
        private int assessment_cycle;
        private int status;
        private String statusStr;
        private int assessment_status;
        private String remark;
        private String create_time;

        public long getRemaining_time_for_assessment() {
            return remaining_time_for_assessment;
        }

        public void setRemaining_time_for_assessment(long remaining_time_for_assessment) {
            this.remaining_time_for_assessment = remaining_time_for_assessment;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }


        public String getReview_time() {
            return review_time;
        }

        public void setReview_time(String review_time) {
            this.review_time = review_time;
        }

        private String review_time;
        private String review_time_str;
        private int performance;
        private String front_display_status;
        private List<String> images;

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

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getLevelStr() {
            return levelStr;
        }

        public void setLevelStr(String levelStr) {
            this.levelStr = levelStr;
        }

        public String getService_fee() {
            return service_fee;
        }

        public void setService_fee(String service_fee) {
            this.service_fee = service_fee;
        }

        public int getNeed_achieve_performance() {
            return need_achieve_performance;
        }

        public void setNeed_achieve_performance(int need_achieve_performance) {
            this.need_achieve_performance = need_achieve_performance;
        }

        public int getAssessment_cycle() {
            return assessment_cycle;
        }

        public void setAssessment_cycle(int assessment_cycle) {
            this.assessment_cycle = assessment_cycle;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public int getAssessment_status() {
            return assessment_status;
        }

        public void setAssessment_status(int assessment_status) {
            this.assessment_status = assessment_status;
        }


        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }


        public String getReview_time_str() {
            return review_time_str;
        }

        public void setReview_time_str(String review_time_str) {
            this.review_time_str = review_time_str;
        }

        public int getPerformance() {
            return performance;
        }

        public void setPerformance(int performance) {
            this.performance = performance;
        }

        public String getFront_display_status() {
            return front_display_status;
        }

        public void setFront_display_status(String front_display_status) {
            this.front_display_status = front_display_status;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class LevelBean {
        /**
         * level : 3
         * levelStr : 导购
         * margin : 1000
         * performance : 20000
         * cycle : 30
         * rule :
         */

        private int level;
        private String levelStr;
        private int margin;
        private int performance;
        private int cycle;
        private String rule;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getLevelStr() {
            return levelStr;
        }

        public void setLevelStr(String levelStr) {
            this.levelStr = levelStr;
        }

        public int getMargin() {
            return margin;
        }

        public void setMargin(int margin) {
            this.margin = margin;
        }

        public int getPerformance() {
            return performance;
        }

        public void setPerformance(int performance) {
            this.performance = performance;
        }

        public int getCycle() {
            return cycle;
        }

        public void setCycle(int cycle) {
            this.cycle = cycle;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }
    }
//
//    /**
//     * recordInfo : {"id":119,"user_id":55,"level":5,"levelStr":"代理商","service_fee":"100000.00","need_achieve_performance":"2000000.00","assessment_cycle":180,"status":1,"statusStr":"待上传凭证","images":null,"assessment_status":1,"remark":null,"create_time":"2020-08-15 17:24:32","review_time":null,"review_time_str":"1970-01-01 08:00:00","performance":0,"front_display_status":"待上传凭证"}
//     * level : [{"level":3,"levelStr":"导购","margin":1000,"performance":20000,"cycle":30,"rule":"<p style=\"text-align: center; line-height: 24px;\"><span style=\"font-family: 宋体; line-height: 24px;\">获取代理资格签约书<\/span><\/p><p style=\"line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">&nbsp;<\/span><\/p><p style=\"line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">尊敬的开心拼购顾客：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">您正参加本平台\u201c预交服务费提升身份\u201d的活动，本着公正公开、平等自愿、协商一致的原则，现与我司签订《获取代理资格签约书》，协议内容如下，请认真阅读：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">一，<\/span><span style=\"font-family: 宋体; font-size: 14px;\">您正申请的项目为：预交<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">1000<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">元服务费，身份升级为\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">导购<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d，即时享有\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">导购<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d所有权利与收益，同时也需履行相关义务。考核周期为<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">30天<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">，审核通过即时起算，到期日前需完成<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">20000<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">元（大写<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">贰万圆<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">）的发团销售业绩销售额考核。满足考核要求，立即退还服务费，同时永久享有\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">导购<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d身份，享受相关权益。未在期限内满足考核要求，服务费不予退还，但保留身份。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">二，<\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">导购<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d享受权益如下：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">1，平台会对您做好指导、协调、培训、服务等工作，以便您能顺利通过考核。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">2，如在平台体验过程中遇到问题，请及时与后台人工客服联系，以便给您以良好的购物环境与操作体验。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">三，<\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">导购<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d应尽以下义务：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">1，在销售任务期间内，您需接受平台的监督，遵守平台的规章制度，不做扰乱平台的事宜，活动期间不投机取巧，不做故意刷单等行为。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">2，您必须遵守国家法律法规，维护平台和您团队内部成员的合法权益，正确处理内部分配关系。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">3，作为\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">导购<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d，享受相关权益的同时，请您传播积极正面的消息，禁止对平台有诋毁、造谣、诽谤、中伤行为，如有类似情况发生，平台可视情节严重性单方面终止协议，并保留追究其法律责任的权利。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">四，协议的终止、变更、解除：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">1，协议文本及条款在点击\u201c我已阅读，同意无误\u201d后，即视为同意协议条款，之后协议将转至后台审核，审核通过后即时生效，任何单方提出的变更均属无效。在生效前，您如有任何疑问，可联系人工客服咨询，如需取消，仅可在协议生效前申诉取消，协议生效后不可取消。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">2，考核期结束后，本协议自动终止，平台不再接受任何关于该协议的要求和申诉。如您未满足考核要求，可重新签订该协议。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">&nbsp;<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">本协议在您点击\u201c我已阅读，同意无误\u201d后，本人实名签字，审核通过则即时生效。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">如您对协议中的内容有疑问，请咨询人工客服，如您需要增加补充项，也请联系人工客服协商添加。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">&nbsp;<\/span><\/p><p style=\"text-indent: 28px; text-align: right; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">\u201c开心拼购\u201d全国运营中心<\/span><\/p><p style=\"text-indent: 28px; text-align: right; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">中鼎啸龙科技发展有限公司<\/span><\/p>"},{"level":4,"levelStr":"经销商","margin":10000,"performance":200000,"cycle":90,"rule":"<p style=\"text-align: center; line-height: 24px;\"><span style=\"font-family: 宋体; line-height: 24px;\">获取代理资格签约书<\/span><\/p><p style=\"line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">&nbsp;<\/span><\/p><p style=\"line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">尊敬的开心拼购顾客：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">您正参加本平台\u201c预交服务费提升身份\u201d的活动，本着公正公开、平等自愿、协商一致的原则，现与我司签订《获取代理资格签约书》，协议内容如下，请认真阅读：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">一，<\/span><span style=\"font-family: 宋体; font-size: 14px;\">您正申请的项目为：预交<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">10000<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">元服务费，身份变更为\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">经销商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d，即时享有\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">经销商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d所有权利与收益，同时也需履行相关义务。考核周期为<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">60天<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">，审核通过即时起算，到期日前需完成<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">200000<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">元（大写<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">贰拾万圆<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">）的发团销售业绩销售额考核。满足考核要求，立即退还服务费，同时永久享有\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">经销商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d身份，享受相关权益。未在期限内满足考核要求，服务费不予退还，但保留身份。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">二，<\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">经销商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d享受权益如下：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">1，平台会对您做好指导、协调、培训、服务等工作，以便您能顺利通过考核。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">2，如在平台体验过程中遇到问题，请及时与后台人工客服联系，以便给您以良好的购物环境与操作体验。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">三，<\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">经销商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d应尽以下义务：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">1，在销售任务期间内，您需接受平台的监督，遵守平台的规章制度，不做扰乱平台的事宜，活动期间不投机取巧，不做故意刷单等行为。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">2，您必须遵守国家法律法规，维护平台和您团队内部成员的合法权益，正确处理内部分配关系。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">3，作为\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">经销商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d，享受相关权益的同时，请您传播积极正面的消息，禁止对平台有诋毁、造谣、诽谤、中伤行为，如有类似情况发生，平台可视情节严重性单方面终止协议，并保留追究其法律责任的权利。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">四，协议的终止、变更、解除：<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">1，协议文本及条款在点击\u201c我已阅读，同意无误\u201d后，即视为同意协议条款，之后协议将转至后台审核，审核通过后即时生效，任何单方提出的变更均属无效。在生效前，您如有任何疑问，可联系人工客服咨询，如需取消，仅可在协议生效前申诉取消，协议生效后不可取消。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">2，考核期结束后，本协议自动终止，平台不再接受任何关于该协议的要求和申诉。如您未满足考核要求，可重新签订该协议。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">&nbsp;<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">本协议在您点击\u201c我已阅读，同意无误\u201d后，本人实名签字，审核通过则即时生效。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">如您对协议中的内容有疑问，请咨询人工客服，如您需要增加补充项，也请联系人工客服协商添加。<\/span><\/p><p style=\"text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">&nbsp;<\/span><\/p><p style=\"text-indent: 28px; text-align: right; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">\u201c开心拼购\u201d全国运营中心<\/span><\/p><p style=\"text-indent: 28px; text-align: right; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">中鼎啸龙科技发展有限公司<\/span><\/p>"},{"level":5,"levelStr":"代理商","margin":100000,"performance":2000000,"cycle":180,"rule":"<p style=\"white-space: normal; text-align: center; line-height: 24px;\"><span style=\"font-family: 宋体; line-height: 24px;\">获取代理资格签约书<\/span><\/p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">&nbsp;<\/span><\/p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">尊敬的开心拼购顾客：<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">您正参加本平台\u201c预交服务费提升身份\u201d的活动，本着公正公开、平等自愿、协商一致的原则，现与我司签订《获取代理资格签约书》，协议内容如下，请认真阅读：<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">一，<\/span><span style=\"font-family: 宋体; font-size: 14px;\">您正申请的项目为：预交<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">100000<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">元服务费，身份变更为\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">代理商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d，即时享有\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">代理商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d所有权利与收益，同时也需履行相关义务。考核周期为<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">120天<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">，审核通过即时起算，到期日前需完成<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">2000000<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">元（大写<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">贰佰万圆<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">）的发团销售业绩销售额考核。满足考核要求，立即退还服务费，同时永久享有\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">代理商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d身份，享受相关权益。未在期限内满足考核要求，服务费不予退还，但保留身份。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">二，<\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">代理商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d享受权益如下<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">1，平台会对您做好指导、协调、培训、服务等工作，以便您能顺利通过考核。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">2，如在平台体验过程中遇到问题，请及时与后台人工客服联系，以便给您以良好的购物环境与操作体验。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">三，<\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">代理商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d应尽以下义务：<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">1，在销售任务期间内，您需接受平台的监督，遵守平台的规章制度，不做扰乱平台的事宜，活动期间不投机取巧，不做故意刷单等行为。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">2，您必须遵守国家法律法规，维护平台和您团队内部成员的合法权益，正确处理内部分配关系。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">3，作为\u201c<\/span><span style=\"text-decoration-line: underline;\"><span style=\"font-family: 宋体; font-size: 14px;\">代理商<\/span><\/span><span style=\"font-family: 宋体; font-size: 14px;\">\u201d，享受相关权益的同时，请您传播积极正面的消息，禁止对平台有诋毁、造谣、诽谤、中伤行为，如有类似情况发生，平台可视情节严重性单方面终止协议，并保留追究其法律责任的权利。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">四，协议的终止、变更、解除：<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">1，协议文本及条款在点击\u201c我已阅读，同意无误\u201d后，即视为同意协议条款，之后协议将转至后台审核，审核通过后即时生效，任何单方提出的变更均属无效。在生效前，您如有任何疑问，可联系人工客服咨询，如需取消，仅可在协议生效前申诉取消，协议生效后不可取消。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">2，考核期结束后，本协议自动终止，平台不再接受任何关于该协议的要求和申诉。如您未满足考核要求，可重新签订该协议。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">&nbsp;<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">本协议在您点击\u201c我已阅读，同意无误\u201d后，本人实名签字，审核通过则即时生效。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">如您对协议中的内容有疑问，请咨询人工客服，如您需要增加补充项，也请联系人工客服协商添加。<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">&nbsp;<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; text-align: right; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">\u201c开心拼购\u201d全国运营中心<\/span><\/p><p style=\"white-space: normal; text-indent: 28px; text-align: right; line-height: 24px;\"><span style=\"font-family: 宋体; font-size: 14px;\">中鼎啸龙科技发展有限公司<\/span><\/p>"}]
//     * user_level : 1
//     * user_level_str : 用户
//     * event_description : <p>亲爱的开心拼购朋友：</p><p>&nbsp; &nbsp; &nbsp; &nbsp;如您还在为升级身份而感到困惑，或是在升级过程中因为团队人数遇到瓶颈，停滞不前，不妨可以尝试加入《预交服务费提升身份》活动，随时开通，立即体验高级身份带来的全部收益。</p><p>&nbsp; &nbsp; &nbsp; &nbsp;通过《预交服务费提升身份》活动而获得的永久身份，不受晋级条件约束。如果您还在为升级条件而感到烦恼，就快来体验吧！</p>
//     * collect_money_info : <p>收款账户信息</p><p>我单位收款银行账户信息如下:</p><p>开户行：中国招商银行重庆南岸支行</p><p>账号：123909360710501</p><p>户名：中鼎啸龙科技发展有限公司</p>
//     */
//
//    private RecordInfoBean recordInfo;
//    private int user_level;
//    private String user_level_str;
//    private String event_description;
//    private String collect_money_info;
//    private List<LevelBean> level;
//
//    public RecordInfoBean getRecordInfo() {
//        return recordInfo;
//    }
//
//    public void setRecordInfo(RecordInfoBean recordInfo) {
//        this.recordInfo = recordInfo;
//    }
//
//    public int getUser_level() {
//        return user_level;
//    }
//
//    public void setUser_level(int user_level) {
//        this.user_level = user_level;
//    }
//
//    public String getUser_level_str() {
//        return user_level_str;
//    }
//
//    public void setUser_level_str(String user_level_str) {
//        this.user_level_str = user_level_str;
//    }
//
//    public String getEvent_description() {
//        return event_description;
//    }
//
//    public void setEvent_description(String event_description) {
//        this.event_description = event_description;
//    }
//
//    public String getCollect_money_info() {
//        return collect_money_info;
//    }
//
//    public void setCollect_money_info(String collect_money_info) {
//        this.collect_money_info = collect_money_info;
//    }
//
//    public List<LevelBean> getLevel() {
//        return level;
//    }
//
//    public void setLevel(List<LevelBean> level) {
//        this.level = level;
//    }
//
//    public static class RecordInfoBean {
//        /**
//         * id : 119
//         * user_id : 55
//         * level : 5
//         * levelStr : 代理商
//         * service_fee : 100000.00
//         * need_achieve_performance : 2000000.00
//         * assessment_cycle : 180
//         * status : 1
//         * statusStr : 待上传凭证
//         * images : null
//         * assessment_status : 1
//         * remark : null
//         * create_time : 2020-08-15 17:24:32
//         * review_time : null
//         * review_time_str : 1970-01-01 08:00:00
//         * performance : 0
//         * front_display_status : 待上传凭证
//         */
//
//        private int id;
//        private int user_id;
//        private int level;
//        private String levelStr;
//        private String service_fee;
//        private String need_achieve_performance;
//        private int assessment_cycle;
//        private int status;
//        private String statusStr;
//        private Object images;
//        private int assessment_status;
//
//        public String getRemark() {
//            return remark;
//        }
//
//        public void setRemark(String remark) {
//            this.remark = remark;
//        }
//
//        private String remark;
//        private String create_time;
//        private Object review_time;
//        private String review_time_str;
//        private int performance;
//        private String front_display_status;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(int user_id) {
//            this.user_id = user_id;
//        }
//
//        public int getLevel() {
//            return level;
//        }
//
//        public void setLevel(int level) {
//            this.level = level;
//        }
//
//        public String getLevelStr() {
//            return levelStr;
//        }
//
//        public void setLevelStr(String levelStr) {
//            this.levelStr = levelStr;
//        }
//
//        public String getService_fee() {
//            return service_fee;
//        }
//
//        public void setService_fee(String service_fee) {
//            this.service_fee = service_fee;
//        }
//
//        public String getNeed_achieve_performance() {
//            return need_achieve_performance;
//        }
//
//        public void setNeed_achieve_performance(String need_achieve_performance) {
//            this.need_achieve_performance = need_achieve_performance;
//        }
//
//        public int getAssessment_cycle() {
//            return assessment_cycle;
//        }
//
//        public void setAssessment_cycle(int assessment_cycle) {
//            this.assessment_cycle = assessment_cycle;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//
//        public void setStatus(int status) {
//            this.status = status;
//        }
//
//        public String getStatusStr() {
//            return statusStr;
//        }
//
//        public void setStatusStr(String statusStr) {
//            this.statusStr = statusStr;
//        }
//
//        public Object getImages() {
//            return images;
//        }
//
//        public void setImages(Object images) {
//            this.images = images;
//        }
//
//        public int getAssessment_status() {
//            return assessment_status;
//        }
//
//        public void setAssessment_status(int assessment_status) {
//            this.assessment_status = assessment_status;
//        }
//
//        public String getCreate_time() {
//            return create_time;
//        }
//
//        public void setCreate_time(String create_time) {
//            this.create_time = create_time;
//        }
//
//        public Object getReview_time() {
//            return review_time;
//        }
//
//        public void setReview_time(Object review_time) {
//            this.review_time = review_time;
//        }
//
//        public String getReview_time_str() {
//            return review_time_str;
//        }
//
//        public void setReview_time_str(String review_time_str) {
//            this.review_time_str = review_time_str;
//        }
//
//        public int getPerformance() {
//            return performance;
//        }
//
//        public void setPerformance(int performance) {
//            this.performance = performance;
//        }
//
//        public String getFront_display_status() {
//            return front_display_status;
//        }
//
//        public void setFront_display_status(String front_display_status) {
//            this.front_display_status = front_display_status;
//        }
//    }
//
//    public static class LevelBean {
//        /**
//         * level : 3
//         * levelStr : 导购
//         * margin : 1000
//         * performance : 20000
//         * cycle : 30
//         * rule : <p style="text-align: center; line-height: 24px;"><span style="font-family: 宋体; line-height: 24px;">获取代理资格签约书</span></p><p style="line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">&nbsp;</span></p><p style="line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">尊敬的开心拼购顾客：</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">您正参加本平台“预交服务费提升身份”的活动，本着公正公开、平等自愿、协商一致的原则，现与我司签订《获取代理资格签约书》，协议内容如下，请认真阅读：</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">一，</span><span style="font-family: 宋体; font-size: 14px;">您正申请的项目为：预交</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">1000</span></span><span style="font-family: 宋体; font-size: 14px;">元服务费，身份升级为“</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">导购</span></span><span style="font-family: 宋体; font-size: 14px;">”，即时享有“</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">导购</span></span><span style="font-family: 宋体; font-size: 14px;">”所有权利与收益，同时也需履行相关义务。考核周期为</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">30天</span></span><span style="font-family: 宋体; font-size: 14px;">，审核通过即时起算，到期日前需完成</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">20000</span></span><span style="font-family: 宋体; font-size: 14px;">元（大写</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">贰万圆</span></span><span style="font-family: 宋体; font-size: 14px;">）的发团销售业绩销售额考核。满足考核要求，立即退还服务费，同时永久享有“</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">导购</span></span><span style="font-family: 宋体; font-size: 14px;">”身份，享受相关权益。未在期限内满足考核要求，服务费不予退还，但保留身份。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">二，</span><span style="font-family: 宋体; font-size: 14px;">“</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">导购</span></span><span style="font-family: 宋体; font-size: 14px;">”享受权益如下：</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">1，平台会对您做好指导、协调、培训、服务等工作，以便您能顺利通过考核。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">2，如在平台体验过程中遇到问题，请及时与后台人工客服联系，以便给您以良好的购物环境与操作体验。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">三，</span><span style="font-family: 宋体; font-size: 14px;">“</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">导购</span></span><span style="font-family: 宋体; font-size: 14px;">”应尽以下义务：</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">1，在销售任务期间内，您需接受平台的监督，遵守平台的规章制度，不做扰乱平台的事宜，活动期间不投机取巧，不做故意刷单等行为。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">2，您必须遵守国家法律法规，维护平台和您团队内部成员的合法权益，正确处理内部分配关系。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">3，作为“</span><span style="text-decoration-line: underline;"><span style="font-family: 宋体; font-size: 14px;">导购</span></span><span style="font-family: 宋体; font-size: 14px;">”，享受相关权益的同时，请您传播积极正面的消息，禁止对平台有诋毁、造谣、诽谤、中伤行为，如有类似情况发生，平台可视情节严重性单方面终止协议，并保留追究其法律责任的权利。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">四，协议的终止、变更、解除：</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">1，协议文本及条款在点击“我已阅读，同意无误”后，即视为同意协议条款，之后协议将转至后台审核，审核通过后即时生效，任何单方提出的变更均属无效。在生效前，您如有任何疑问，可联系人工客服咨询，如需取消，仅可在协议生效前申诉取消，协议生效后不可取消。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">2，考核期结束后，本协议自动终止，平台不再接受任何关于该协议的要求和申诉。如您未满足考核要求，可重新签订该协议。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">&nbsp;</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">本协议在您点击“我已阅读，同意无误”后，本人实名签字，审核通过则即时生效。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">如您对协议中的内容有疑问，请咨询人工客服，如您需要增加补充项，也请联系人工客服协商添加。</span></p><p style="text-indent: 28px; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">&nbsp;</span></p><p style="text-indent: 28px; text-align: right; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">“开心拼购”全国运营中心</span></p><p style="text-indent: 28px; text-align: right; line-height: 24px;"><span style="font-family: 宋体; font-size: 14px;">中鼎啸龙科技发展有限公司</span></p>
//         */
//
//        private int level;
//        private String levelStr;
//        private int margin;
//        private int performance;
//        private int cycle;
//        private String rule;
//
//        public int getLevel() {
//            return level;
//        }
//
//        public void setLevel(int level) {
//            this.level = level;
//        }
//
//        public String getLevelStr() {
//            return levelStr;
//        }
//
//        public void setLevelStr(String levelStr) {
//            this.levelStr = levelStr;
//        }
//
//        public int getMargin() {
//            return margin;
//        }
//
//        public void setMargin(int margin) {
//            this.margin = margin;
//        }
//
//        public int getPerformance() {
//            return performance;
//        }
//
//        public void setPerformance(int performance) {
//            this.performance = performance;
//        }
//
//        public int getCycle() {
//            return cycle;
//        }
//
//        public void setCycle(int cycle) {
//            this.cycle = cycle;
//        }
//
//        public String getRule() {
//            return rule;
//        }
//
//        public void setRule(String rule) {
//            this.rule = rule;
//        }
//    }


}
