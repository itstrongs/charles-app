package com.charles.credit.assist.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.ConstantPool;
import com.charles.credit.assist.http.HttpService;
import com.charles.credit.assist.http.RetrofitHelper;
import com.charles.credit.assist.model.entity.OrderEntity;
import com.charles.credit.assist.model.enums.PayTypeEnum;
import com.charles.utils.AppUtils;
import com.charles.utils.Logger;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;
import com.charles.utils.http.HttpResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

import static com.charles.credit.assist.model.ConstantPool.WECHAT_APP_ID;
import static com.charles.credit.assist.model.ConstantPool.WECHAT_PAY_SUCCESS;

/**
 * 支付页面
 *
 * @Author: liufengqiang
 * @Date: 2019/6/6
 */
public class PayActivity extends BaseActivity {

    @BindView(R.id.text_money)
    TextView textMoney;
    @BindView(R.id.text_order_number)
    TextView textOrderNumber;
    @BindView(R.id.text_create_time)
    TextView textCreateTime;
    @BindView(R.id.img_state_wechat)
    ImageView imgStateWechat;
    @BindView(R.id.img_state_alipay)
    ImageView imgStateAlipay;

    private static final int PERMISSIONS_REQUEST_CODE = 1002;
    private int mPayType;
    private OrderEntity mOrderEntity;
    private IWXAPI api;
    private boolean mIsCallWechat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        initStatusBar();
        Logger.d("PayActivity");
        String orderInfo = getIntent().getStringExtra("order_info");
        if (StringUtils.isEmpty(orderInfo))
            return;
        mOrderEntity = JSONObject.parseObject(orderInfo, OrderEntity.class);
        textMoney.setText("￥ " + mOrderEntity.getMoney());
        textOrderNumber.setText("订单编号：" + mOrderEntity.getOrderNo());
        textCreateTime.setText("创建时间：" + mOrderEntity.getCreatedAt());
        api = WXAPIFactory.createWXAPI(this, WECHAT_APP_ID);
        mPayType = PayTypeEnum.WECHAT.getCode();
        requestPermission();
        mIsCallWechat = false;
        WECHAT_PAY_SUCCESS = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsCallWechat && WECHAT_PAY_SUCCESS) {
            setResult(ConstantPool.RESULT_CODE_PAY_SUCCESS);
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.img_back, R.id.btn_pay, R.id.layout_pay_wechat, R.id.layout_pay_alipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.layout_pay_wechat:
                setDrawableState(true);
                break;
            case R.id.layout_pay_alipay:
                setDrawableState(false);
                break;
            case R.id.btn_pay:
                doPay();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setDrawableState(boolean isWechat) {
        mPayType = isWechat ? PayTypeEnum.WECHAT.getCode() : PayTypeEnum.ALIPAY.getCode();
        imgStateWechat.setImageResource(isWechat ? R.mipmap.ic_select : R.mipmap.ic_not_select);
        imgStateAlipay.setImageResource(isWechat ? R.mipmap.ic_not_select : R.mipmap.ic_select);
    }

    private void doPay() {
        mIsCallWechat = false;
        WECHAT_PAY_SUCCESS = false;
        RetrofitHelper.INSTANCE.post(this, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.placeOrder(mOrderEntity.getOrderNo(), mPayType,
                        mOrderEntity.getQueryType(), AppUtils.getLocalIpAddress(mContext));
            }

            @Override
            public void onResult(HttpResult result) {
                if (PayTypeEnum.ALIPAY.getCode().equals(mPayType)) {
                    payV2(String.valueOf(result.getData()));
                } else {
                    doWechatPay(result.getJSONData());
                }
            }
        });
    }

    private void doWechatPay(JSONObject data) {
        mIsCallWechat = true;
        PayReq req = new PayReq();
        //req.appId = "wxf8b4f85f3a794e77";  // ²âÊÔÓÃappId
        req.appId = data.getString("appid");
        req.partnerId = data.getString("mch_id");
        req.prepayId = data.getString("prepay_id");
        req.nonceStr = data.getString("nonce_str");
        req.timeStamp = data.getString("timestamp");
        req.packageValue = "Sign=WXPay";
        req.sign = data.getString("sign");
        req.extData = "app data"; // optional
        boolean b = api.sendReq(req);
        Logger.d("bbb" + b);
    }

    private void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                final Map<String, String> result = alipay.payV2(orderInfo, true);
                Logger.d("支付结果" + result.toString());
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ("9000".equals(result.get("resultStatus"))) {
                            ToastUtils.show(mContext, "支付成功");
                            setResult(ConstantPool.RESULT_CODE_PAY_SUCCESS);
                            finish();
                        } else {
                            ToastUtils.show(mContext, "支付失败");
                        }
                    }
                });
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d("PayActivity onActivityResult: requestCode = " + requestCode + ", requestCode = " + requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                // 用户取消了权限弹窗
                if (grantResults.length == 0) {
                    ToastUtils.show(this, "无法获取支付宝 SDK 所需的权限, 请到系统设置开启");
                    return;
                }

                // 用户拒绝了某些权限
                for (int x : grantResults) {
                    if (x == PackageManager.PERMISSION_DENIED) {
                        ToastUtils.show(this, "无法获取支付宝 SDK 所需的权限, 请到系统设置开启");
                        return;
                    }
                }
            }
        }
    }
}
