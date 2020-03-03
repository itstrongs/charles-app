package com.charles.credit.assist.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.bo.NetLoanReportBo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 网贷申请记录结果页
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年07月28日 13:18
 */
public class ApplyResultActivity extends BaseActivity {

    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_credit_no)
    TextView textCreditNo;
    @BindView(R.id.text_phone)
    TextView textPhone;
    @BindView(R.id.text_apply_age)
    TextView textAge;
    @BindView(R.id.text_id_card)
    TextView textIdCard;
    @BindView(R.id.text_tip)
    TextView textTip;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_count)
    TextView textCount;

    private NetLoanReportBo netLoanReportBo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_result);
        ButterKnife.bind(this);
//        String result = "{\"reportId\":\"156393842668518306918831\",\"loanInfoList\":[{\"platformName\":\"上海你我贷互联网金融信息**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"拍拍信数据**（上海）有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"上海仟才金融信息**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"凡普金科**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"新疆玖富万卡信息**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"霍尔果斯智融未来信息**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"上海科赋网络**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"}],\"phone\":\"183****8831\",\"idCard\":\"152***********7218\",\"sex\":1,\"name\":\"滕立波\",\"loanPlatCount\":7,\"age\":30}";
        String result = getIntent().getStringExtra("result");
        netLoanReportBo = JSONObject.parseObject(result, NetLoanReportBo.class);
        textName.setText(netLoanReportBo.getName());
        textCreditNo.setText(netLoanReportBo.getReportId());
        textPhone.setText(netLoanReportBo.getPhone());
        textAge.setText(netLoanReportBo.getAge() + "岁");
        textIdCard.setText(netLoanReportBo.getIdCard());
        textCount.setText(String.valueOf(netLoanReportBo.getLoanPlatCount()));
        if (netLoanReportBo.getLoanInfoList() == null || netLoanReportBo.getLoanInfoList().size() == 0) {
            textTip.setText("经检测，你没有记录的平台");
        } else {
            textTip.setText("经检测，你有记录的平台");
        }
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
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_apply_result, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            NetLoanReportBo.LoanInfoListBean loanInfoListBean = netLoanReportBo.getLoanInfoList().get(position);
            Picasso.with(mContext).load(loanInfoListBean.getPlatformIcon()).into(holder.imgIcon);
            holder.textName.setText(loanInfoListBean.getPlatformName());
            holder.textDesc.setText(loanInfoListBean.getStatus());
        }

        @Override
        public int getItemCount() {
            return netLoanReportBo.getLoanInfoList() == null ? 0 : netLoanReportBo.getLoanInfoList().size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imgIcon;
            TextView textName;
            TextView textDesc;

            MyViewHolder(View itemView) {
                super(itemView);
                imgIcon = itemView.findViewById(R.id.img_icon);
                textName = itemView.findViewById(R.id.text_name);
                textDesc = itemView.findViewById(R.id.text_desc);
            }
        }
    }
}
