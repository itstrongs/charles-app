package com.charles.credit.assist.activity;

import android.content.Intent;
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
import com.charles.credit.assist.helper.DataCallback;
import com.charles.credit.assist.model.bo.QueryRecordBo;
import com.charles.credit.assist.model.enums.CreditQueryEnum;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 历史报告页面
 *
 * @Author: liufengqiang
 * @Date: 2019/6/12
 */
public class HistoryActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_no)
    TextView textNo;

    private MyAdapter mMyAdapter;
    private QueryRecordBo queryRecordBo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        initStatusBar();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        mPresenter.creditHistory(mActivity, null, new DataCallback<String>() {
            @Override
            public void result(String result) {
                queryRecordBo = JSONObject.parseObject(result, QueryRecordBo.class);
                if (queryRecordBo.getResultList().size() > 0) {
                    textNo.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    mMyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        onBackPressed();
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_history, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            final QueryRecordBo.ResultListBean resultListBean = queryRecordBo.getResultList().get(position);
            holder.textName.setText(resultListBean.getRealName());
            holder.textCreditName.setText(resultListBean.getQueryTypeName());
            holder.textLook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (CreditQueryEnum.getEnumByCode(resultListBean.getQueryType())) {
                        case ANTI_FRAUD:
                            queryReport(GradeResultActivity.class);
                            break;
                        case BLACK_LIST:
                            queryReport(BlackResultActivity.class);
                            break;
                        case LOANS_RECORD:
                            queryReport(ApplyResultActivity.class);
                            break;
                        case SET_MEAL:
                            queryReport(SetMealResultActivity.class);
                            break;
                        case EMERGENCY_CONTACT:
                            queryReport(ContactsResultActivity.class);
                            break;
                    }
                }

                private void queryReport(Class clazz) {
                    Intent intent = new Intent(mContext, clazz);
                    intent.putExtra("result", resultListBean.getQueryResult());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return queryRecordBo == null ? 0 : queryRecordBo.getResultList().size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textName;
            TextView textCreditName;
            TextView textLook;

            MyViewHolder(View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_name);
                textCreditName = itemView.findViewById(R.id.text_credit_name);
                textLook = itemView.findViewById(R.id.text_look);
            }
        }
    }
}

