package com.laojiashop.laojia.http;

import com.google.gson.JsonElement;
import com.laojiashop.laojia.activity.MainActivity;
import com.laojiashop.laojia.entity.AddressmanagementBean;
import com.laojiashop.laojia.entity.AfterSaleapplyBean;
import com.laojiashop.laojia.entity.ClassificationDetailsBean;
import com.laojiashop.laojia.entity.ClassificationPageBean;
import com.laojiashop.laojia.entity.ConfigBean;
import com.laojiashop.laojia.entity.CreateOrderBean;
import com.laojiashop.laojia.entity.FeedbackBean;
import com.laojiashop.laojia.entity.FeedbackDetailBean;
import com.laojiashop.laojia.entity.FeedbackconditionsBean;
import com.laojiashop.laojia.entity.FlieUploadBean;
import com.laojiashop.laojia.entity.GoldcoinsBean;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.entity.HomePageBean;
import com.laojiashop.laojia.entity.HomePageTestBean;
import com.laojiashop.laojia.entity.MallGoodsCommentBean;
import com.laojiashop.laojia.entity.MeHappyListBean;
import com.laojiashop.laojia.entity.MeHappybean;
import com.laojiashop.laojia.entity.MyCoinInfoBean;
import com.laojiashop.laojia.entity.MySpellingBean;
import com.laojiashop.laojia.entity.MycollectionBean;
import com.laojiashop.laojia.entity.MyteamBean;
import com.laojiashop.laojia.entity.MyteamListBean;
import com.laojiashop.laojia.entity.OrderBean;
import com.laojiashop.laojia.entity.OrderGoodsdetailBean;
import com.laojiashop.laojia.entity.PayOrderInfoBean;
import com.laojiashop.laojia.entity.PayWayBean;
import com.laojiashop.laojia.entity.ReceivedividendsBean;
import com.laojiashop.laojia.entity.ScoreInfoBean;
import com.laojiashop.laojia.entity.SearchKeywordsBean;
import com.laojiashop.laojia.entity.ServiceFeeBean;
import com.laojiashop.laojia.entity.SpellgrouprecordsBean;
import com.laojiashop.laojia.entity.TuanGouDetailsBean;
import com.laojiashop.laojia.entity.UserInfoBean;
import com.laojiashop.laojia.entity.WithdrawalRecordBean;
import com.laojiashop.laojia.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    /**
     * 手机号登录
     */
    @FormUrlEncoded
    @POST("User/login")
    Observable<BaseResult<User>> login(@Field("phone") String phonenum,
                                       @Field("sms_verification_code") String sms_verification_code,
                                       @Field("source") String source, @Field("login_type") String login_type);

    /**
     * 微信登录
     *
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("User/login")
    Observable<BaseResult<User>> weilogin(@Field("code") String code, @Field("login_type") String login_type, @Field("source") String source);

    /**
     * 获取验证码
     *
     * @param phonenum
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("Publics/getVerCode")
    Observable<BaseResult<Object>> getcode(@Field("phone") String phonenum, @Field("type") String type);

    /**
     * 绑定手机号
     *
     * @param phonenum
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("User/bindPhoneSave")
    Observable<BaseResult<Object>> bindPhoneSave(@Field("phone") String phonenum, @Field("code") String code);

    /**
     * 获取用户信息
     *
     * @return
     */
    @GET("User/getUserInfo")
    Observable<BaseResult<UserInfoBean>> getuserinfo();

    /**
     * 开心豆明细
     *
     * @return
     */
    @GET("Core/getMyScoreInfo")
    Observable<BaseResult<MeHappybean>> gethappyinfo();

    /**
     * 开心豆明细
     *
     * @return
     */
    @GET("Base/Score/getList")
    Observable<BaseResult<MeHappyListBean>> gethappylistinfo(@Query("scence") String scence, @Query("page") int page, @Query("filter") String filter);

    /**
     * 首页数据
     *
     * @param table
     * @return
     */
    @GET("Base/mallIndex")
    Observable<BaseResult<HomePageBean>> gethomepageinfo(@Query("table") String table);

    /**
     * 测试的首页数据
     *
     * @param table
     * @return
     */
    @GET("Base/mallIndex")
    Observable<BaseResult<HomePageTestBean>> gethomepageinfos(@Query("table") String table);

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @GET("Base/Goods/detail")
    Observable<BaseResult<GoodsDetailBean>> getgooddetailinfo(@Query("id") String id);

    /**
     * 商品评价
     *
     * @param scence
     * @param page
     * @param filter
     * @return
     */
    @GET("Base/Comment/getList")
    Observable<BaseResult<MallGoodsCommentBean>> getgoodsevaluationlist(@Query("scence") String scence, @Query("page") int page, @Query("filter") String filter);

    /**
     * 商品详情
     *
     * @return
     */
    @GET("GoodsType/index")
    Observable<BaseResult<List<ClassificationPageBean>>> getclassificationinfo();

    /**
     * 商品列表
     *
     * @param scence
     * @param page
     * @param filter
     * @return
     */
    @GET("Base/Goods/getList")
    Observable<BaseResult<ClassificationDetailsBean>> getclassificainfo(@Query("scence") String scence, @Query("page") int page, @Query("filter") String filter);

    /**
     * 收藏/取消收藏
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Base/addCollect")
    Observable<BaseResult<Object>> getaddcollect(@Field("table") String table, @Field("item_id") String item_id);

    /**
     * 收藏列表
     *
     * @param scence
     * @param page
     * @return
     */
    @GET("Base/Collect/getList")
    Observable<BaseResult<MycollectionBean>> getcollectgetList(@Query("scence") String scence, @Query("page") int page);

    /**
     * 获取地址列表
     *
     * @param scence
     * @param page
     * @return
     */
    @GET("Base/Address/getList")
    Observable<BaseResult<AddressmanagementBean>> getaddressgetList(@Query("scence") String scence, @Query("page") int page);

    /**
     * 新增地址
     *
     * @param name
     * @param phone
     * @param province
     * @param city
     * @param area
     * @param p_code
     * @param c_code
     * @param a_code
     * @param code
     * @param address
     * @param is_def
     * @param ssq
     * @param user_id
     * @return
     */
    @FormUrlEncoded
    @POST("Base/Address/addRecord")
    Observable<BaseResult<Object>> addressaddrecord(@Field("id") String id, @Field("name") String name,
                                                    @Field("phone") String phone,
                                                    @Field("province") String province,
                                                    @Field("city") String city,
                                                    @Field("area") String area,
                                                    @Field("p_code") String p_code,
                                                    @Field("c_code") String c_code,
                                                    @Field("a_code") String a_code,
                                                    @Field("code") String code,
                                                    @Field("address") String address,
                                                    @Field("is_def") int is_def,
                                                    @Field("ssq") String ssq,
                                                    @Field("user_id") String user_id);

    /**
     * 商品列表
     *
     * @param scence
     * @param page
     * @param filter
     * @return
     */
    @GET("Base/MallOrder/getList")
    Observable<BaseResult<OrderBean>> getmallOrdergetList(@Query("scence") String scence, @Query("page") int page, @Query("filter") String filter);

    /**
     * 登出
     *
     * @return
     */
    @GET("User/logOut")
    Observable<BaseResult<Object>> logOut();

    /**
     * 登出
     *
     * @return
     */
    @GET("Base/MallOrder/detail")
    Observable<BaseResult<OrderGoodsdetailBean>> mallOrderdetail(@Query("id") int id);

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @POST("MallOrder/cancelOrder")
    Observable<BaseResult<Object>> mallcancelorder(@Field("orderId") int orderId);

    /**
     * 确认收货
     *
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @POST("MallOrder/confirmReceipt")
    Observable<BaseResult<Object>> mallconfirmreceipt(@Field("orderId") int orderId);

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @POST("MallOrder/deleteOrder")
    Observable<BaseResult<Object>> malldeleteOrder(@Field("orderId") int orderId);

    /**
     * 搜索
     *
     * @return
     */
    @GET("Goods/keyword")
    Observable<BaseResult<SearchKeywordsBean>> goodskeyword();

    /**
     * 创建订单
     *
     * @param type
     * @param goodsId
     * @param configIdx
     * @param coin
     * @param score
     * @param pay
     * @param number
     * @param skuCombination
     * @param addressId
     * @param name
     * @param identificationNumber
     * @param remark
     * @param liveUid
     * @return
     */
    @FormUrlEncoded
    @POST("MallOrder/createOrder")
    Observable<BaseResult<CreateOrderBean>> createorder(@Field("type") int type,
                                                        @Field("goodsId") String goodsId,
                                                        @Field("configIdx") int configIdx,
                                                        @Field("coin") double coin,
                                                        @Field("score") int score,
                                                        @Field("pay") double pay,
                                                        @Field("number") int number,
                                                        @Field("skuCombination") String skuCombination,
                                                        @Field("addressId") int addressId,
                                                        @Field("name") String name,
                                                        @Field("identificationNumber") String identificationNumber,
                                                        @Field("remark") String remark,
                                                        @Field("liveUid") String liveUid);

    /**
     * 搜索
     *
     * @return
     */
    @GET("Pay/getPayWayList")
    Observable<BaseResult<PayWayBean>> getPayWayList();

    /**
     * 获取可金币和积分
     *
     * @return
     */
    @GET("Core/getScoreInfo")
    Observable<BaseResult<ScoreInfoBean>> getScoreInfo();

    /**
     * 图片上传(单张图片上传)
     */
    @Multipart
    @POST("Upload/toSave")
    Observable<BaseResult<FlieUploadBean>> uploadtosave(@Part MultipartBody.Part file_data);

    /**
     * 获取支付信息
     *
     * @param orderNumber
     * @param payway
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/toPay")
    Observable<BaseResult<PayOrderInfoBean>> toPay(@Field("orderNumber") String orderNumber, @Field("payWay") int payway);

    /**
     * 创建团购
     *
     * @param goods_id
     * @param config_idx
     * @return
     */
    @FormUrlEncoded
    @POST("Core/makeTuan")
    Observable<BaseResult<Object>> makeTuan(@Field("goods_id") String goods_id, @Field("config_idx") int config_idx);
    /**
     * 获取团购列表
     */
    /**
     * 商品列表
     *
     * @param scence
     * @param page
     * @param filter
     * @return
     */
    @GET("Base/Tuangou/getList")
    Observable<BaseResult<MySpellingBean>> getTuangouList(@Query("scence") String scence, @Query("page") int page, @Query("filter") String filter);

    /**
     * 关闭拼团
     *
     * @param tid
     * @return
     */
    @FormUrlEncoded
    @POST("Core/cancelPintuan")
    Observable<BaseResult<Object>> cancelPintuan(@Field("tid") int tid);

    /**
     * 参团记录
     *
     * @param tid
     * @return
     */
    @GET("Core/getTuanAllData")
    Observable<BaseResult<SpellgrouprecordsBean>> getTuanAllData(@Query("tid") int tid);

    /**
     * 我的金币
     *
     * @return
     */
    @GET("Core/getMyCoinInfo")
    Observable<BaseResult<MyCoinInfoBean>> getMyCoinInfo();

    /**
     * 金币明细列表
     *
     * @param scence
     * @param page
     * @param filter
     * @return
     */
    @GET("Base/Coin/getList")
    Observable<BaseResult<GoldcoinsBean>> getCoinList(@Query("scence") String scence, @Query("page") int page, @Query("filter") String filter);

    /**
     * 我的拼团业绩列表
     *
     * @return
     */
    @GET("MyTeam/index")
    Observable<BaseResult<MyteamBean>> MyTeamIndex();

    /**
     * 团队列表
     *
     * @param page
     * @return
     */
    @GET("MyTeam/getMyTeamList")
    Observable<BaseResult<MyteamListBean>> getMyTeamList(@Query("page") int page);

    /**
     * 邀请码
     *
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("User/makeRelationByCode")
    Observable<BaseResult<Object>> makeRelationByCode(@Field("code") String code);

    /**
     * 提交评价
     *
     * @param table
     * @param order_id
     * @param goods_id
     * @param star
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("Core/applyComment")
    Observable<BaseResult<Object>> applyComment(@Field("table") String table,
                                                @Field("order_id") String order_id,
                                                @Field("goods_id") int goods_id,
                                                @Field("star") int star,
                                                @Field("content") String content,
                                                @Field("imgs") String stringList);


    /**
     * 退换货详情页
     *
     * @param goods_id
     * @param order_id
     * @return
     */
    @FormUrlEncoded
    @POST("AfterSale/applyPage")
    Observable<BaseResult<AfterSaleapplyBean>> AfterSaleapplyPage(@Field("goods_id") int goods_id, @Field("order_id") int order_id);

    /**
     * 分红权明细列表
     *
     * @param scence
     * @param page
     * @return
     */
    @GET("Base/Bonus/getList")
    Observable<BaseResult<ReceivedividendsBean>> getBonusList(@Query("scence") String scence, @Query("page") int page);

    /**
     * 获取配置文件
     *
     * @return
     */
    @GET("Config/getConfig")
    Observable<BaseResult<ConfigBean>> getConfig();

    /**
     * 兑换分红权
     *
     * @param num
     * @return
     */
    @FormUrlEncoded
    @POST("Core/buyBonus")
    Observable<BaseResult<Object>> corebuyBonus(@Field("num") int num);

    /**
     * 退出分红权
     *
     * @param bonusId
     * @return
     */
    @FormUrlEncoded
    @POST("Bonus/exitBonus")
    Observable<BaseResult<Object>> exitBonus(@Field("bonusId") int bonusId);

    /**
     * 获取配置文件
     *
     * @return
     */
    @GET("Base/TuanGou/detail")
    Observable<BaseResult<TuanGouDetailsBean>> getTuanGoudetail(@Query("id") String id, @Query("nstr") String nstr);

    /**
     * 修改用户信息
     *
     * @param headImgUrl
     * @param nickName
     * @return
     */
    @FormUrlEncoded
    @POST("User/saveUserInfo")
    Observable<BaseResult<Object>> saveUserInfo(@Field("headImgUrl") String headImgUrl, @Field("nickName") String nickName);

    /**
     * 获取服务费升级
     *
     * @return
     */
    @GET("ServiceFee/index")
    Observable<BaseResult<ServiceFeeBean>> getServiceFeeindex();

    /**
     * 新增服务费
     *
     * @param level
     * @return
     */
    @FormUrlEncoded
    @POST("ServiceFee/add")
    Observable<BaseResult<Object>> serviceFeeadd(@Field("level") String level);

    @FormUrlEncoded
    @POST("ServiceFee/update")
    Observable<BaseResult<Object>> serviceFeeupdate(@Field("level") int level, @Field("record_id") String record_id, @Field("images") String stringList);

    /**
     * 参与拼团
     *
     * @param saveData
     * @return
     */
    @FormUrlEncoded
    @POST("Core/joinTuanGou")
    Observable<BaseResult<CreateOrderBean>> corejoinTuanGou(@Field("saveData") String saveData);

    /**
     * 申请提现
     *
     * @param pay
     * @return
     */
    @FormUrlEncoded
    @POST("Core/withdraw")
    Observable<BaseResult<Object>> corewithdraw(@Field("pay") String pay);

    /**
     * 获取提现记录
     *
     * @param scence
     * @param page
     * @return
     */
    @GET("Base/withdrawLog/getList")
    Observable<BaseResult<WithdrawalRecordBean>> getwithdrawLogList(@Query("scence") String scence, @Query("page") int page);

    /**
     * 删除地址
     *
     * @param ids
     * @return
     */
    @GET("Base/Address/delete")
    Observable<BaseResult<Object>> addressdelete(@Query("ids") int ids);

    /**
     * 获取意见反馈列表
     *
     * @param scence
     * @param page
     * @return
     */
    @GET("Base/FeedBack/getList")
    Observable<BaseResult<FeedbackBean>> getFeedBackList(@Query("scence") String scence, @Query("page") int page, @Query("filter") String filter);

    /**
     * 获取反馈类型列表
     *
     * @param scence
     * @param page
     * @return
     */
    @GET("Base/FeedbackType/getList")
    Observable<BaseResult<FeedbackconditionsBean>> getBaFeedbackTypeList(@Query("scence") String scence, @Query("page") int page);

    /**
     * 添加意见反馈
     * @param table
     * @param typeid
     * @param typename
     * @param phone
     * @param content
     * @param relation_id
     * @param stringList
     * @return
     */
    @FormUrlEncoded
    @POST("Fankui/apply")
    Observable<BaseResult<Object>> Fankuiapply(@Field("table") String table,
                                               @Field("typeid") int typeid,
                                               @Field("typename") String typename,
                                               @Field("phone") String phone,
                                               @Field("content") String content,
                                               @Field("relation_id") String relation_id,
                                               @Field("imgs") String stringList);

    /**
     * 反馈详情
     * @param id
     * @param user_type
     * @return
     */
    @GET("Base/FeedBack/detail")
    Observable<BaseResult<FeedbackDetailBean>> getfeedBackdetail(@Query("id") int id, @Query("user_type") String user_type);

    /**
     * 追加意见反馈
     * @param f_id
     * @param table
     * @param user_type
     * @param content
     * @param stringList
     * @return
     */
    @FormUrlEncoded
    @POST("Fankui/reBack")
    Observable<BaseResult<Object>> FankuireBack(@Field("f_id") int f_id,
                                               @Field("table") String table,
                                               @Field("user_type") String user_type,
                                               @Field("content") String content,
                                               @Field("imgs") String stringList);

    /**
     * 结束工单
     * @param f_id
     * @param table
     * @param user_type
     * @return
     */
    @FormUrlEncoded
    @POST("Fankui/done")
    Observable<BaseResult<Object>> Fankuidone(@Field("f_id") int f_id,
                                                @Field("table") String table,
                                                @Field("user_type") String user_type);



}
