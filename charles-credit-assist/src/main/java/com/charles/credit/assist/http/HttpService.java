package com.charles.credit.assist.http;

import com.charles.credit.assist.model.request.CreditRequest;
import com.charles.library.http.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口列表
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2018年07月24日 09:31
 */
public interface HttpService {

    /**
     * 获取验证码
     * @param phone
     * @param verifyCodeType
     * @return
     */
    @GET("/user/verifyCode")
    Observable<HttpResult> verifyCode(@Query("phone") String phone,
                                      @Query("verifyCodeType") String verifyCodeType);

    /**
     * 用户登录
     * @param username
     * @param verifyCode
     * @return
     */
    @POST("/user/login")
    Observable<HttpResult> login(@Query("phone") String username,
                                 @Query("verifyCode") String verifyCode);

    /**
     * 用户信息
     * @return
     */
    @GET("/user/info")
    Observable<HttpResult> userInfo();

    /**
     * 信用查询
     * @param request
     * @return
     */
    @POST("/credit/query")
    Observable<HttpResult> queryCredit(@Body CreditRequest request);

    /**
     * 信用历史报告
     * @return
     */
    @GET("/credit/history")
    Observable<HttpResult> creditHistory(@Query("search") String search);

    /**
     * 创建订单
     * @return
     */
    @POST("/order/create")
    Observable<HttpResult> createOrder(@Query("queryType") Integer queryType);

    /**
     * 支付
     * @param orderNo
     * @param payType
     * @param ip
     * @return
     */
    @POST("/order/pay")
    Observable<HttpResult> placeOrder(@Query("orderNo") String orderNo,
                                      @Query("payType") Integer payType,
                                      @Query("queryType") Integer queryType,
                                      @Query("ip") String ip);

    /**
     * 优惠券列表
     * @param type
     * @return
     */
    @GET("/coupon/list")
    Observable<HttpResult> couponList(@Query("type") Integer type);

    /**
     * 贷款产品
     */
    @GET("/loans-product")
    Observable<HttpResult> loansProduct(@Query("type") Integer type, @Query("tag") Integer tag);
}
