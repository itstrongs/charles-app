package com.charles.credit.assist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.ConstantPool;
import com.charles.credit.assist.model.request.CreditRequest;
import com.charles.credit.assist.model.enums.CreditQueryEnum;
import com.charles.library.utils.SPHelper;
import com.charles.library.utils.StringUtils;
import com.charles.library.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.charles.credit.assist.model.ConstantPool.REQUEST_CODE_PAY;
import static com.charles.credit.assist.model.ConstantPool.RESULT_CODE_PAY_SUCCESS;

/**
 * @Details:
 * @Author: liufengqiang
 * @Date: 2019/6/29
 */
public class BlacklistActivity extends BaseActivity {

    @BindView(R.id.edit_real_name)
    EditText editRealName;
    @BindView(R.id.edit_id_card)
    EditText editIdCard;
    @BindView(R.id.edit_id_telephone)
    EditText editIdTelephone;

    private String realName;
    private String idCard;
    private String telephone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklist);
        ButterKnife.bind(this);
        editRealName.setText(SPHelper.getString(this, ConstantPool.SP_CREDIT_REAL_NAME));
        editIdCard.setText(SPHelper.getString(this, ConstantPool.SP_CREDIT_ID_CARD));
        editIdTelephone.setText(SPHelper.getString(this, ConstantPool.SP_CREDIT_TELEPHONE));
    }

    @OnClick({R.id.img_back, R.id.img_close, R.id.btn_checkup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
            case R.id.img_close:
                onBackPressed();
                break;
            case R.id.btn_checkup:
                if (initParam()) {
                    mPresenter.createOrder(mActivity, CreditQueryEnum.BLACK_LIST.getCode());
                }
                break;
        }
    }

    private boolean initParam() {
        realName = editRealName.getText().toString().trim();
        idCard = editIdCard.getText().toString().trim();
        telephone = editIdTelephone.getText().toString().trim();
        if (StringUtils.isEmpty(realName)) {
            ToastUtils.show(mContext, "姓名不能为空");
            return false;
        }
        if (StringUtils.isEmpty(idCard)) {
            ToastUtils.show(mContext, "身份证不能为空");
            return false;
        }
        if (StringUtils.isEmpty(telephone)) {
            ToastUtils.show(mContext, "手机号不能为空");
            return false;
        }
        SPHelper.putString(mContext, ConstantPool.SP_CREDIT_REAL_NAME, realName);
        SPHelper.putString(mContext, ConstantPool.SP_CREDIT_ID_CARD, idCard);
        SPHelper.putString(mContext, ConstantPool.SP_CREDIT_TELEPHONE, telephone);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PAY && resultCode == RESULT_CODE_PAY_SUCCESS && initParam()) {
            CreditRequest creditRequest = new CreditRequest();
            creditRequest.setQueryType(CreditQueryEnum.BLACK_LIST.getCode());
            creditRequest.setRealName(realName);
            creditRequest.setIdCard(idCard);
            creditRequest.setTelephone(telephone);
            mPresenter.queryCredit(mActivity, creditRequest, BlackResultActivity.class);
        }
    }
}
