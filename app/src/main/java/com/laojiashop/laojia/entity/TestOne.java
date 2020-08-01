package com.laojiashop.laojia.entity;

import java.util.List;

public class TestOne {


    /**
     * course : {"courseId":null,"courseCount":null,"courseStartTime":null,"courseEndTime":null,"courseCate":0,"totalNum":0,"favorableRate":0,"ascription":null,"online":null,"isAudition":null,"agreement":null,"scheduleGroup":0}
     * goods : {"goodId":1,"goodsName":"商品1_new","banner":"http://dlsuser-test.oss-cn-beijing.aliyuncs.com/file-91d019bbbd73492386e3cbe5d5982ec7.png","sellStartTime":1557036248000,"sellEndTime":1557036251000,"payCount":11,"limitCount":2,"limitBuyNum":2,"goodsSku":[{"id":1,"goodsId":1,"specs":"1-4,2-6","price":9.1,"stock":3},{"id":2,"goodsId":1,"specs":"1-4,2-7","price":9.2,"stock":3},{"id":3,"goodsId":1,"specs":"1-4,2-8","price":9.3,"stock":3},{"id":4,"goodsId":1,"specs":"1-5,2-6","price":8.1,"stock":2},{"id":5,"goodsId":1,"specs":"1-5,2-7","price":8.2,"stock":2},{"id":6,"goodsId":1,"specs":"1-5,2-8","price":8.3,"stock":2}],"specItems":[{"id":1,"name":"类型","items":[{"id":4,"name":"教师资格证","items":null}]},{"id":2,"name":"学段","items":[{"id":6,"name":"中学","items":null},{"id":7,"name":"小学","items":null},{"id":8,"name":"幼儿园","items":null}]}],"productStatus":2,"exist":false,"originalPrice":3,"currentPrice":3,"actualPayMoney":3,"promotionType":1,"timingStartTime":1557036267000,"timingEndTime":1557036269000,"timingPrice":3,"flashSaleRemainSecond":null,"isFrontMoney":true,"depositPaid":false,"frontMoneyMsg":null,"frontMoney":2,"finalMoney":1,"isBatchPay":true,"batchMoney":3,"batchMoneyList":null,"hasPromGift":true,"promGiftTypeNames":["模考","商品","礼品","商品"],"promGiftNum":4,"promGiftList":[{"promGiftType":"模考","promGiftName":"test","refId":null},{"promGiftType":"商品","promGiftName":"test","refId":7},{"promGiftType":"礼品","promGiftName":"rr","refId":null},{"promGiftType":"商品","promGiftName":"testet","refId":1}],"hasDeduction":false,"enablePointsDeduct":false,"deductionHoneyNum":0,"pointsDeductMoney":0,"coupon":null,"couponMoney":null,"isGroupPurchase":true,"groupPurchasePrice":500,"isAdvisory":true,"advisoryList":[{"type":null,"qqId":"33","androidKey":"44","iphoneKey":"23","pageKey":"43","wechatId":"433","wechatName":"433","wechatPhoto":"http://dlsuser-test.oss-cn-beijing.aliyuncs.com/file-6ea9bc5ae07a490b808e0d76158f16e8.png","wechatQrcode":"http://dlsuser-test.oss-cn-beijing.aliyuncs.com/file-a0cf55feda7945ab8e1b66a8a0f9813a.png","phone":34324,"thirdPartyId":4234234}],"needBuyerInfo":true,"isShip":true,"isVipPeculiar":false,"studentVipPrice":2,"iphoneId":"2222","redirectType":0}
     */

    private CourseBean course;
    private GoodsBean goods;

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public static class CourseBean {
        /**
         * courseId : null
         * courseCount : null
         * courseStartTime : null
         * courseEndTime : null
         * courseCate : 0
         * totalNum : 0
         * favorableRate : 0
         * ascription : null
         * online : null
         * isAudition : null
         * agreement : null
         * scheduleGroup : 0
         */

        private Object courseId;
        private Object courseCount;
        private Object courseStartTime;
        private Object courseEndTime;
        private int courseCate;
        private int totalNum;
        private int favorableRate;
        private Object ascription;
        private Object online;
        private Object isAudition;
        private Object agreement;
        private int scheduleGroup;

        public Object getCourseId() {
            return courseId;
        }

        public void setCourseId(Object courseId) {
            this.courseId = courseId;
        }

        public Object getCourseCount() {
            return courseCount;
        }

        public void setCourseCount(Object courseCount) {
            this.courseCount = courseCount;
        }

        public Object getCourseStartTime() {
            return courseStartTime;
        }

        public void setCourseStartTime(Object courseStartTime) {
            this.courseStartTime = courseStartTime;
        }

        public Object getCourseEndTime() {
            return courseEndTime;
        }

        public void setCourseEndTime(Object courseEndTime) {
            this.courseEndTime = courseEndTime;
        }

        public int getCourseCate() {
            return courseCate;
        }

        public void setCourseCate(int courseCate) {
            this.courseCate = courseCate;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getFavorableRate() {
            return favorableRate;
        }

        public void setFavorableRate(int favorableRate) {
            this.favorableRate = favorableRate;
        }

        public Object getAscription() {
            return ascription;
        }

        public void setAscription(Object ascription) {
            this.ascription = ascription;
        }

        public Object getOnline() {
            return online;
        }

        public void setOnline(Object online) {
            this.online = online;
        }

        public Object getIsAudition() {
            return isAudition;
        }

        public void setIsAudition(Object isAudition) {
            this.isAudition = isAudition;
        }

        public Object getAgreement() {
            return agreement;
        }

        public void setAgreement(Object agreement) {
            this.agreement = agreement;
        }

        public int getScheduleGroup() {
            return scheduleGroup;
        }

        public void setScheduleGroup(int scheduleGroup) {
            this.scheduleGroup = scheduleGroup;
        }
    }

    public static class GoodsBean {
        /**
         * goodId : 1
         * goodsName : 商品1_new
         * banner : http://dlsuser-test.oss-cn-beijing.aliyuncs.com/file-91d019bbbd73492386e3cbe5d5982ec7.png
         * sellStartTime : 1557036248000
         * sellEndTime : 1557036251000
         * payCount : 11
         * limitCount : 2
         * limitBuyNum : 2
         * goodsSku : [{"id":1,"goodsId":1,"specs":"1-4,2-6","price":9.1,"stock":3},{"id":2,"goodsId":1,"specs":"1-4,2-7","price":9.2,"stock":3},{"id":3,"goodsId":1,"specs":"1-4,2-8","price":9.3,"stock":3},{"id":4,"goodsId":1,"specs":"1-5,2-6","price":8.1,"stock":2},{"id":5,"goodsId":1,"specs":"1-5,2-7","price":8.2,"stock":2},{"id":6,"goodsId":1,"specs":"1-5,2-8","price":8.3,"stock":2}]
         * specItems : [{"id":1,"name":"类型","items":[{"id":4,"name":"教师资格证","items":null}]},{"id":2,"name":"学段","items":[{"id":6,"name":"中学","items":null},{"id":7,"name":"小学","items":null},{"id":8,"name":"幼儿园","items":null}]}]
         * productStatus : 2
         * exist : false
         * originalPrice : 3
         * currentPrice : 3
         * actualPayMoney : 3
         * promotionType : 1
         * timingStartTime : 1557036267000
         * timingEndTime : 1557036269000
         * timingPrice : 3
         * flashSaleRemainSecond : null
         * isFrontMoney : true
         * depositPaid : false
         * frontMoneyMsg : null
         * frontMoney : 2
         * finalMoney : 1
         * isBatchPay : true
         * batchMoney : 3
         * batchMoneyList : null
         * hasPromGift : true
         * promGiftTypeNames : ["模考","商品","礼品","商品"]
         * promGiftNum : 4
         * promGiftList : [{"promGiftType":"模考","promGiftName":"test","refId":null},{"promGiftType":"商品","promGiftName":"test","refId":7},{"promGiftType":"礼品","promGiftName":"rr","refId":null},{"promGiftType":"商品","promGiftName":"testet","refId":1}]
         * hasDeduction : false
         * enablePointsDeduct : false
         * deductionHoneyNum : 0
         * pointsDeductMoney : 0
         * coupon : null
         * couponMoney : null
         * isGroupPurchase : true
         * groupPurchasePrice : 500
         * isAdvisory : true
         * advisoryList : [{"type":null,"qqId":"33","androidKey":"44","iphoneKey":"23","pageKey":"43","wechatId":"433","wechatName":"433","wechatPhoto":"http://dlsuser-test.oss-cn-beijing.aliyuncs.com/file-6ea9bc5ae07a490b808e0d76158f16e8.png","wechatQrcode":"http://dlsuser-test.oss-cn-beijing.aliyuncs.com/file-a0cf55feda7945ab8e1b66a8a0f9813a.png","phone":34324,"thirdPartyId":4234234}]
         * needBuyerInfo : true
         * isShip : true
         * isVipPeculiar : false
         * studentVipPrice : 2
         * iphoneId : 2222
         * redirectType : 0
         */

        private int goodId;
        private String goodsName;
        private String banner;
        private long sellStartTime;
        private long sellEndTime;
        private int payCount;
        private int limitCount;
        private int limitBuyNum;
        private int productStatus;
        private boolean exist;
        private int originalPrice;
        private int currentPrice;
        private int actualPayMoney;
        private int promotionType;
        private long timingStartTime;
        private long timingEndTime;
        private int timingPrice;
        private Object flashSaleRemainSecond;
        private boolean isFrontMoney;
        private boolean depositPaid;
        private Object frontMoneyMsg;
        private int frontMoney;
        private int finalMoney;
        private boolean isBatchPay;
        private int batchMoney;
        private Object batchMoneyList;
        private boolean hasPromGift;
        private int promGiftNum;
        private boolean hasDeduction;
        private boolean enablePointsDeduct;
        private int deductionHoneyNum;
        private int pointsDeductMoney;
        private Object coupon;
        private Object couponMoney;
        private boolean isGroupPurchase;
        private int groupPurchasePrice;
        private boolean isAdvisory;
        private boolean needBuyerInfo;
        private boolean isShip;
        private boolean isVipPeculiar;
        private int studentVipPrice;
        private String iphoneId;
        private int redirectType;
        private List<GoodsSkuBean> goodsSku;
        private List<SpecItemsBean> specItems;
        private List<String> promGiftTypeNames;
        private List<PromGiftListBean> promGiftList;
        private List<AdvisoryListBean> advisoryList;

        public int getGoodId() {
            return goodId;
        }

        public void setGoodId(int goodId) {
            this.goodId = goodId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public long getSellStartTime() {
            return sellStartTime;
        }

        public void setSellStartTime(long sellStartTime) {
            this.sellStartTime = sellStartTime;
        }

        public long getSellEndTime() {
            return sellEndTime;
        }

        public void setSellEndTime(long sellEndTime) {
            this.sellEndTime = sellEndTime;
        }

        public int getPayCount() {
            return payCount;
        }

        public void setPayCount(int payCount) {
            this.payCount = payCount;
        }

        public int getLimitCount() {
            return limitCount;
        }

        public void setLimitCount(int limitCount) {
            this.limitCount = limitCount;
        }

        public int getLimitBuyNum() {
            return limitBuyNum;
        }

        public void setLimitBuyNum(int limitBuyNum) {
            this.limitBuyNum = limitBuyNum;
        }

        public int getProductStatus() {
            return productStatus;
        }

        public void setProductStatus(int productStatus) {
            this.productStatus = productStatus;
        }

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }

        public int getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(int originalPrice) {
            this.originalPrice = originalPrice;
        }

        public int getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(int currentPrice) {
            this.currentPrice = currentPrice;
        }

        public int getActualPayMoney() {
            return actualPayMoney;
        }

        public void setActualPayMoney(int actualPayMoney) {
            this.actualPayMoney = actualPayMoney;
        }

        public int getPromotionType() {
            return promotionType;
        }

        public void setPromotionType(int promotionType) {
            this.promotionType = promotionType;
        }

        public long getTimingStartTime() {
            return timingStartTime;
        }

        public void setTimingStartTime(long timingStartTime) {
            this.timingStartTime = timingStartTime;
        }

        public long getTimingEndTime() {
            return timingEndTime;
        }

        public void setTimingEndTime(long timingEndTime) {
            this.timingEndTime = timingEndTime;
        }

        public int getTimingPrice() {
            return timingPrice;
        }

        public void setTimingPrice(int timingPrice) {
            this.timingPrice = timingPrice;
        }

        public Object getFlashSaleRemainSecond() {
            return flashSaleRemainSecond;
        }

        public void setFlashSaleRemainSecond(Object flashSaleRemainSecond) {
            this.flashSaleRemainSecond = flashSaleRemainSecond;
        }

        public boolean isIsFrontMoney() {
            return isFrontMoney;
        }

        public void setIsFrontMoney(boolean isFrontMoney) {
            this.isFrontMoney = isFrontMoney;
        }

        public boolean isDepositPaid() {
            return depositPaid;
        }

        public void setDepositPaid(boolean depositPaid) {
            this.depositPaid = depositPaid;
        }

        public Object getFrontMoneyMsg() {
            return frontMoneyMsg;
        }

        public void setFrontMoneyMsg(Object frontMoneyMsg) {
            this.frontMoneyMsg = frontMoneyMsg;
        }

        public int getFrontMoney() {
            return frontMoney;
        }

        public void setFrontMoney(int frontMoney) {
            this.frontMoney = frontMoney;
        }

        public int getFinalMoney() {
            return finalMoney;
        }

        public void setFinalMoney(int finalMoney) {
            this.finalMoney = finalMoney;
        }

        public boolean isIsBatchPay() {
            return isBatchPay;
        }

        public void setIsBatchPay(boolean isBatchPay) {
            this.isBatchPay = isBatchPay;
        }

        public int getBatchMoney() {
            return batchMoney;
        }

        public void setBatchMoney(int batchMoney) {
            this.batchMoney = batchMoney;
        }

        public Object getBatchMoneyList() {
            return batchMoneyList;
        }

        public void setBatchMoneyList(Object batchMoneyList) {
            this.batchMoneyList = batchMoneyList;
        }

        public boolean isHasPromGift() {
            return hasPromGift;
        }

        public void setHasPromGift(boolean hasPromGift) {
            this.hasPromGift = hasPromGift;
        }

        public int getPromGiftNum() {
            return promGiftNum;
        }

        public void setPromGiftNum(int promGiftNum) {
            this.promGiftNum = promGiftNum;
        }

        public boolean isHasDeduction() {
            return hasDeduction;
        }

        public void setHasDeduction(boolean hasDeduction) {
            this.hasDeduction = hasDeduction;
        }

        public boolean isEnablePointsDeduct() {
            return enablePointsDeduct;
        }

        public void setEnablePointsDeduct(boolean enablePointsDeduct) {
            this.enablePointsDeduct = enablePointsDeduct;
        }

        public int getDeductionHoneyNum() {
            return deductionHoneyNum;
        }

        public void setDeductionHoneyNum(int deductionHoneyNum) {
            this.deductionHoneyNum = deductionHoneyNum;
        }

        public int getPointsDeductMoney() {
            return pointsDeductMoney;
        }

        public void setPointsDeductMoney(int pointsDeductMoney) {
            this.pointsDeductMoney = pointsDeductMoney;
        }

        public Object getCoupon() {
            return coupon;
        }

        public void setCoupon(Object coupon) {
            this.coupon = coupon;
        }

        public Object getCouponMoney() {
            return couponMoney;
        }

        public void setCouponMoney(Object couponMoney) {
            this.couponMoney = couponMoney;
        }

        public boolean isIsGroupPurchase() {
            return isGroupPurchase;
        }

        public void setIsGroupPurchase(boolean isGroupPurchase) {
            this.isGroupPurchase = isGroupPurchase;
        }

        public int getGroupPurchasePrice() {
            return groupPurchasePrice;
        }

        public void setGroupPurchasePrice(int groupPurchasePrice) {
            this.groupPurchasePrice = groupPurchasePrice;
        }

        public boolean isIsAdvisory() {
            return isAdvisory;
        }

        public void setIsAdvisory(boolean isAdvisory) {
            this.isAdvisory = isAdvisory;
        }

        public boolean isNeedBuyerInfo() {
            return needBuyerInfo;
        }

        public void setNeedBuyerInfo(boolean needBuyerInfo) {
            this.needBuyerInfo = needBuyerInfo;
        }

        public boolean isIsShip() {
            return isShip;
        }

        public void setIsShip(boolean isShip) {
            this.isShip = isShip;
        }

        public boolean isIsVipPeculiar() {
            return isVipPeculiar;
        }

        public void setIsVipPeculiar(boolean isVipPeculiar) {
            this.isVipPeculiar = isVipPeculiar;
        }

        public int getStudentVipPrice() {
            return studentVipPrice;
        }

        public void setStudentVipPrice(int studentVipPrice) {
            this.studentVipPrice = studentVipPrice;
        }

        public String getIphoneId() {
            return iphoneId;
        }

        public void setIphoneId(String iphoneId) {
            this.iphoneId = iphoneId;
        }

        public int getRedirectType() {
            return redirectType;
        }

        public void setRedirectType(int redirectType) {
            this.redirectType = redirectType;
        }

        public List<GoodsSkuBean> getGoodsSku() {
            return goodsSku;
        }

        public void setGoodsSku(List<GoodsSkuBean> goodsSku) {
            this.goodsSku = goodsSku;
        }

        public List<SpecItemsBean> getSpecItems() {
            return specItems;
        }

        public void setSpecItems(List<SpecItemsBean> specItems) {
            this.specItems = specItems;
        }

        public List<String> getPromGiftTypeNames() {
            return promGiftTypeNames;
        }

        public void setPromGiftTypeNames(List<String> promGiftTypeNames) {
            this.promGiftTypeNames = promGiftTypeNames;
        }

        public List<PromGiftListBean> getPromGiftList() {
            return promGiftList;
        }

        public void setPromGiftList(List<PromGiftListBean> promGiftList) {
            this.promGiftList = promGiftList;
        }

        public List<AdvisoryListBean> getAdvisoryList() {
            return advisoryList;
        }

        public void setAdvisoryList(List<AdvisoryListBean> advisoryList) {
            this.advisoryList = advisoryList;
        }

        public static class GoodsSkuBean {
            /**
             * id : 1
             * goodsId : 1
             * specs : 1-4,2-6
             * price : 9.1
             * stock : 3
             */

            private int id;
            private int goodsId;
            private String specs;
            private double price;
            private int stock;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }
        }

        public static class SpecItemsBean {
            /**
             * id : 1
             * name : 类型
             * items : [{"id":4,"name":"教师资格证","items":null}]
             */

            private int id;
            private String name;
            private List<ItemsBean> items;

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

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * id : 4
                 * name : 教师资格证
                 * items : null
                 */

                private int id;
                private String name;
                private Object items;

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

                public Object getItems() {
                    return items;
                }

                public void setItems(Object items) {
                    this.items = items;
                }
            }
        }

        public static class PromGiftListBean {
            /**
             * promGiftType : 模考
             * promGiftName : test
             * refId : null
             */

            private String promGiftType;
            private String promGiftName;
            private Object refId;

            public String getPromGiftType() {
                return promGiftType;
            }

            public void setPromGiftType(String promGiftType) {
                this.promGiftType = promGiftType;
            }

            public String getPromGiftName() {
                return promGiftName;
            }

            public void setPromGiftName(String promGiftName) {
                this.promGiftName = promGiftName;
            }

            public Object getRefId() {
                return refId;
            }

            public void setRefId(Object refId) {
                this.refId = refId;
            }
        }

        public static class AdvisoryListBean {
            /**
             * type : null
             * qqId : 33
             * androidKey : 44
             * iphoneKey : 23
             * pageKey : 43
             * wechatId : 433
             * wechatName : 433
             * wechatPhoto : http://dlsuser-test.oss-cn-beijing.aliyuncs.com/file-6ea9bc5ae07a490b808e0d76158f16e8.png
             * wechatQrcode : http://dlsuser-test.oss-cn-beijing.aliyuncs.com/file-a0cf55feda7945ab8e1b66a8a0f9813a.png
             * phone : 34324
             * thirdPartyId : 4234234
             */

            private Object type;
            private String qqId;
            private String androidKey;
            private String iphoneKey;
            private String pageKey;
            private String wechatId;
            private String wechatName;
            private String wechatPhoto;
            private String wechatQrcode;
            private int phone;
            private int thirdPartyId;

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public String getQqId() {
                return qqId;
            }

            public void setQqId(String qqId) {
                this.qqId = qqId;
            }

            public String getAndroidKey() {
                return androidKey;
            }

            public void setAndroidKey(String androidKey) {
                this.androidKey = androidKey;
            }

            public String getIphoneKey() {
                return iphoneKey;
            }

            public void setIphoneKey(String iphoneKey) {
                this.iphoneKey = iphoneKey;
            }

            public String getPageKey() {
                return pageKey;
            }

            public void setPageKey(String pageKey) {
                this.pageKey = pageKey;
            }

            public String getWechatId() {
                return wechatId;
            }

            public void setWechatId(String wechatId) {
                this.wechatId = wechatId;
            }

            public String getWechatName() {
                return wechatName;
            }

            public void setWechatName(String wechatName) {
                this.wechatName = wechatName;
            }

            public String getWechatPhoto() {
                return wechatPhoto;
            }

            public void setWechatPhoto(String wechatPhoto) {
                this.wechatPhoto = wechatPhoto;
            }

            public String getWechatQrcode() {
                return wechatQrcode;
            }

            public void setWechatQrcode(String wechatQrcode) {
                this.wechatQrcode = wechatQrcode;
            }

            public int getPhone() {
                return phone;
            }

            public void setPhone(int phone) {
                this.phone = phone;
            }

            public int getThirdPartyId() {
                return thirdPartyId;
            }

            public void setThirdPartyId(int thirdPartyId) {
                this.thirdPartyId = thirdPartyId;
            }
        }
    }
}
