package com.charles.credit.assist.helper;

import android.app.Activity;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.activity.MainActivity;
import com.charles.credit.assist.activity.PayActivity;
import com.charles.credit.assist.model.ConstantPool;
import com.charles.credit.assist.model.request.CreditRequest;
import com.charles.library.http.HttpResult;
import com.charles.credit.assist.model.entity.UserEntity;
import com.charles.credit.assist.http.HttpService;
import com.charles.credit.assist.http.RetrofitHelper;
import com.charles.library.utils.SPHelper;
import com.charles.library.utils.ToastUtils;

import io.reactivex.Observable;

import static com.charles.credit.assist.model.ConstantPool.REQUEST_CODE_PAY;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年07月31日 20:23
 */
public class Presenter {

    public void createOrder(final Activity activity, final int queryType) {
        RetrofitHelper.INSTANCE.post(activity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.createOrder(queryType);
            }

            @Override
            public void onResult(HttpResult result) {
                Intent intent = new Intent(activity, PayActivity.class);
                intent.putExtra("order_info", result.getJSONData().toJSONString());
                activity.startActivityForResult(intent, REQUEST_CODE_PAY);
            }
        });
    }

    public void queryCredit(final Activity activity, final CreditRequest creditRequest, final Class resultClazz) {
        RetrofitHelper.INSTANCE.post(activity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.queryCredit(creditRequest);
            }

            @Override
            public void onResult(HttpResult result) {
                Intent intent = new Intent(activity, resultClazz);
                intent.putExtra("result", JSON.toJSONString(result.getData()));
                activity.startActivity(intent);
            }
        });
    }

    public void couponList(final Activity activity, final int type, final DataCallback callback) {
        RetrofitHelper.INSTANCE.post(activity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.couponList(type);
            }

            @Override
            public void onResult(HttpResult result) {
                callback.result(result.getJSONData());
            }
        });
    }

    public void creditHistory(Activity activity, final String search, final DataCallback callback) {
        RetrofitHelper.INSTANCE.post(activity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.creditHistory(search);
            }

            @Override
            public void onResult(HttpResult result) {
                callback.result(result.getStringData());
            }
        });
    }

    public void verifyCode(Activity activity, final String phone, final DataCallback callback) {
        RetrofitHelper.INSTANCE.post(activity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.verifyCode(phone, "login");
            }

            @Override
            public void onResult(HttpResult result) {
                callback.result(null);
            }
        });
    }

    public void login(final Activity activity, final String telephone, final String verifyCode) {
        RetrofitHelper.INSTANCE.post(activity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.login(telephone, verifyCode);
            }

            @Override
            public void onResult(HttpResult result) {
                ToastUtils.show(activity, result.getMsg());
                UserEntity userEntity = JSONObject.parseObject(result.getJSONData().toJSONString(), UserEntity.class);
                SPHelper.putBoolean(activity, ConstantPool.SP_IS_LOGIN, true);
                SPHelper.putString(activity, ConstantPool.SP_PHONE, telephone);
                SPHelper.putLong(activity, ConstantPool.SP_USER_ID, userEntity.getUserId());
                SPHelper.putString(activity, ConstantPool.SP_AUTHORIZATION, userEntity.getAuthorization());
                activity.startActivity(new Intent(activity, MainActivity.class));
            }
        });
    }
}
