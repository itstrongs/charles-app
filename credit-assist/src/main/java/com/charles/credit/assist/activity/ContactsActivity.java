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
import com.charles.credit.assist.model.request.UrgencyContactRequest;
import com.charles.credit.assist.model.enums.CreditQueryEnum;
import com.charles.library.utils.SPHelper;
import com.charles.library.utils.StringUtils;
import com.charles.library.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.charles.credit.assist.model.ConstantPool.REQUEST_CODE_PAY;
import static com.charles.credit.assist.model.ConstantPool.RESULT_CODE_PAY_SUCCESS;

/**
 * 紧急联系人
 *
 * @Author: liufengqiang
 * @Date: 2019/6/29
 */
public class ContactsActivity extends BaseActivity {

    @BindView(R.id.edit_name_1)
    EditText editName1;
    @BindView(R.id.edit_phone_1)
    EditText editPhone1;
    @BindView(R.id.edit_name_2)
    EditText editName2;
    @BindView(R.id.edit_phone_2)
    EditText editPhone2;
    @BindView(R.id.edit_name_3)
    EditText editName3;
    @BindView(R.id.edit_phone_3)
    EditText editPhone3;

    private String contactName1;
    private String contactPhone1;
    private String contactName2;
    private String contactPhone2;
    private String contactName3;
    private String contactPhone3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
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
                    mPresenter.createOrder(mActivity, CreditQueryEnum.EMERGENCY_CONTACT.getCode());
                }
                break;
        }
    }

    private boolean initParam() {
        contactName1 = editName1.getText().toString().trim();
        contactPhone1 = editPhone1.getText().toString().trim();
        contactName2 = editName2.getText().toString().trim();
        contactPhone2 = editPhone2.getText().toString().trim();
        contactName3 = editName3.getText().toString().trim();
        contactPhone3 = editPhone3.getText().toString().trim();
        if (StringUtils.isEmpty(contactName1) || StringUtils.isEmpty(contactPhone1)
                || StringUtils.isEmpty(contactName2) || StringUtils.isEmpty(contactPhone2)) {
            ToastUtils.show(mContext, "至少添加2个联系人");
            return false;
        }
        SPHelper.putString(mContext, ConstantPool.SP_CONTACT_NAME_0, contactName1);
        SPHelper.putString(mContext, ConstantPool.SP_CONTACT_NAME_1, contactName2);
        SPHelper.putString(mContext, ConstantPool.SP_CONTACT_NAME_2, contactName3);
        SPHelper.putString(mContext, ConstantPool.SP_CONTACT_PHONE_0, contactPhone1);
        SPHelper.putString(mContext, ConstantPool.SP_CONTACT_PHONE_1, contactPhone2);
        SPHelper.putString(mContext, ConstantPool.SP_CONTACT_PHONE_2, contactPhone3);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PAY && resultCode == RESULT_CODE_PAY_SUCCESS && initParam()) {
            CreditRequest creditRequest = new CreditRequest();
            creditRequest.setQueryType(CreditQueryEnum.EMERGENCY_CONTACT.getCode());
            ArrayList<UrgencyContactRequest> contactRequests = new ArrayList<>();
            contactRequests.add(new UrgencyContactRequest(contactName1, contactPhone1));
            contactRequests.add(new UrgencyContactRequest(contactName2, contactPhone2));
            if (!StringUtils.isEmpty(contactName3) && !StringUtils.isEmpty(contactPhone3)) {
                contactRequests.add(new UrgencyContactRequest(contactName3, contactPhone3));
            }
            creditRequest.setUrgencyContacts(contactRequests);
            mPresenter.queryCredit(mActivity, creditRequest, ContactsResultActivity.class);
        }
    }
}
