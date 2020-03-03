package com.charles.credit.assist.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.bo.ContactResultBo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月01日 11:06
 */
public class ContactsResultActivity extends BaseActivity {

    @BindView(R.id.text_people)
    TextView textPeople;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ContactResultBo contactResultBo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_result);
        ButterKnife.bind(this);
        String result = getIntent().getStringExtra("result");
//        String result = "{\"matchCnt\":2,\"freeUpdates\":false,\"guidePhoneRisk\":true,\"notMatchCnt\":0,\"contacts\":[{\"phone\":\"151****7796\",\"name\":\"张三\",\"match\":true,\"hitRisk\":false},{\"phone\":\"151****7793\",\"name\":\"李四\",\"match\":true,\"hitRisk\":false}]}";
        contactResultBo = JSONObject.parseObject(result, ContactResultBo.class);
        textPeople.setText(contactResultBo.getMatchCnt() + "人适合  " + contactResultBo.getNotMatchCnt() + "人不适合");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
    }

    @OnClick({R.id.img_back, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
            case R.id.img_close:
                onBackPressed();
                break;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_contact_result, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
            ContactResultBo.ContactsBean contactsBean = contactResultBo.getContacts().get(position);
            holder.textName.setText(contactsBean.getName());
            holder.textPhone.setText(contactsBean.getPhone());
            if (contactsBean.isHitRisk()) {
                holder.textDesc.setText("命中一项风险");
                holder.textDesc.setTextColor(Color.parseColor("#FE0000"));
                holder.textDesc.setBackgroundResource(R.mipmap.ic_text_bg_red);
            }
            if (!contactsBean.isMatch()) {
                holder.textRight.setText("不合适");
                holder.textRight.setTextColor(Color.parseColor("#FE0000"));
                holder.textRight.setBackgroundResource(R.drawable.shape_bg_red_25);
            }
        }

        @Override
        public int getItemCount() {
            return contactResultBo.getContacts() == null ? 0 : contactResultBo.getContacts().size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textName;
            TextView textPhone;
            TextView textDesc;
            TextView textRight;

            MyViewHolder(View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_name);
                textPhone = itemView.findViewById(R.id.text_phone);
                textDesc = itemView.findViewById(R.id.text_desc);
                textRight = itemView.findViewById(R.id.text_right);
            }
        }
    }
}
