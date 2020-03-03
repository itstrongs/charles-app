package com.charles.credit.assist.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.bo.BlacklistCommonResultBo;
import com.charles.credit.assist.model.bo.NetLoanReportBo;
import com.charles.library.utils.ToastUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 三合一结果页：网黑 + 拒贷综合报告
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月03日 13:19
 */
public class SetMealResultActivity extends BaseActivity implements OnChartValueSelectedListener {

    @BindView(R.id.text_credit_no)
    TextView textCreditNo;
    @BindView(R.id.text_phone)
    TextView textPhone;
    @BindView(R.id.text_apply_age)
    TextView textApplyAge;
    @BindView(R.id.text_id_card)
    TextView textIdCard;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.pic_chart)
    PieChart picChart;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_state)
    TextView textState;
    @BindView(R.id.radio_btn_tab_0)
    RadioButton radioBtnTab0;
    @BindView(R.id.radio_btn_tab_1)
    RadioButton radioBtnTab1;
    @BindView(R.id.radio_btn_tab_2)
    RadioButton radioBtnTab2;
    @BindView(R.id.text_tip)
    TextView textTip;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_count)
    TextView textCount;
    @BindView(R.id.text_risk_0)
    TextView textRisk0;
    @BindView(R.id.text_risk_1)
    TextView textRisk1;
    @BindView(R.id.text_risk_2)
    TextView textRisk2;
    @BindView(R.id.text_risk_3)
    TextView textRisk3;
    @BindView(R.id.text_risk_4)
    TextView textRisk4;
    @BindView(R.id.text_risk_content_0)
    TextView textRiskContent0;
    @BindView(R.id.text_risk_content_1)
    TextView textRiskContent1;
    @BindView(R.id.text_risk_content_2)
    TextView textRiskContent2;
    @BindView(R.id.text_risk_content_3)
    TextView textRiskContent3;
    @BindView(R.id.text_risk_content_4)
    TextView textRiskContent4;

    private BlacklistCommonResultBo blacklistResultBo;
    private NetLoanReportBo netLoanReportBo;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_meal_result);
        ButterKnife.bind(this);
//        String result = "{\"NB\":{\"idCardBrokenPromise\":\"未命中\",\"sixMonthP2pCount\":7,\"hitCount\":1,\"idCard\":\"1521**********7218\",\"refuseRate\":55,\"oneMonthBankCount\":0,\"riskControl\":[{\"desc\":\"3个月内不要申请超过20次贷款，控制申请频率\"}],\"creditManagerId\":2,\"creditLevel\":\"优秀\",\"applyNotSelf\":\"未命中\",\"limit\":8000,\"threeMonthOtherCount\":4,\"oneMonthSmallCount\":1,\"limitDesc\":\"你的信用状况一般，建议选择高通过率贷款\",\"overdueDesc\":\"未命中\",\"threeMonthTotalCount\":8,\"threeMonthSmallCount\":1,\"address\":\"云南 - 西双版纳傣族自治州\",\"reportId\":\"156516869811918306918831\",\"sixMonthTotalCount\":13,\"sixMonthOtherCount\":4,\"sex\":1,\"oneMonthTotalCount\":5,\"threeMonthP2pCount\":3,\"oneMonthOtherCount\":3,\"sixMonthSmallCount\":2,\"refuseLevel\":\"较高\",\"oneMonthP2pCount\":1,\"phoneRisk\":\"未命中\",\"phone\":\"183****8831\",\"name\":\"滕立波\",\"sixMonthBankCount\":0,\"applyDebtRisk\":\"命中\",\"threeMonthBankCount\":0,\"age\":30},\"LS\":{\"reportId\":\"156393842668518306918831\",\"loanInfoList\":[{\"platformName\":\"上海你我贷互联网金融信息**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"拍拍信数据**（上海）有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"上海仟才金融信息**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"凡普金科**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"新疆玖富万卡信息**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"霍尔果斯智融未来信息**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"},{\"platformName\":\"上海科赋网络**有限公司\",\"platformIcon\":\"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png\",\"status\":\"REGISTER\"}],\"phone\":\"183****8831\",\"idCard\":\"152***********7218\",\"sex\":1,\"name\":\"滕立波\",\"loanPlatCount\":7,\"age\":30}}";
        String result = getIntent().getStringExtra("result");
        JSONObject jsonObject = JSONObject.parseObject(result);
        blacklistResultBo = JSONObject.parseObject(jsonObject.getJSONObject("NB").toJSONString(), BlacklistCommonResultBo.class);
        netLoanReportBo = JSONObject.parseObject(jsonObject.getJSONObject("LS").toJSONString(), NetLoanReportBo.class);
        if (blacklistResultBo != null && netLoanReportBo != null) {
            initData();
            initPicChart(0);
        } else {
            ToastUtils.show(mContext, "查询异常");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    private void initData() {
        textName.setText(blacklistResultBo.getName());
        textCreditNo.setText(blacklistResultBo.getReportId());
        textPhone.setText(blacklistResultBo.getPhone());
        textApplyAge.setText(blacklistResultBo.getAge() + "岁");
        textIdCard.setText(blacklistResultBo.getIdCard());
        textAddress.setText(blacklistResultBo.getAddress());
        textState.setText(blacklistResultBo.getCreditLevel());
        textCount.setText(String.valueOf(netLoanReportBo.getLoanPlatCount()));
        if (netLoanReportBo.getLoanInfoList() == null || netLoanReportBo.getLoanInfoList().size() == 0) {
            textTip.setText("经检测，你没有记录的平台");
        } else {
            textTip.setText("经检测，你有记录的平台");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
        if (!"未命中".equals(blacklistResultBo.getIdCardBrokenPromise())) {
            changeText(textRisk0, textRiskContent0);
        }
        if (!"未命中".equals(blacklistResultBo.getPhoneRisk())) {
            changeText(textRisk1, textRiskContent1);
        }
        if (!"未命中".equals(blacklistResultBo.getApplyNotSelf())) {
            changeText(textRisk2, textRiskContent2);
        }
        if (!"未命中".equals(blacklistResultBo.getApplyDebtRisk())) {
            changeText(textRisk3, textRiskContent3);
        }
        if (!"未命中".equals(blacklistResultBo.getOverdueDesc())) {
            changeText(textRisk4, textRiskContent4);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeText(TextView textView, TextView textRiskContent) {
        textView.setText("命中");
        textView.setTextColor(Color.parseColor("#FE0000"));
//        textView.setBackgroundResource(R.drawable.shape_bg_red_25);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_danger2);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textRiskContent.setCompoundDrawables(drawable, null, null, null);
    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.img_back, R.id.img_close, R.id.radio_btn_tab_0, R.id.radio_btn_tab_1, R.id.radio_btn_tab_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
            case R.id.img_close:
                onBackPressed();
                break;
            case R.id.radio_btn_tab_0:
                radioBtnTab0.setTextColor(getResources().getColor(R.color.color_white));
                radioBtnTab1.setTextColor(getResources().getColor(R.color.color_blue));
                radioBtnTab2.setTextColor(getResources().getColor(R.color.color_blue));
                initPicChart(0);
                break;
            case R.id.radio_btn_tab_1:
                radioBtnTab0.setTextColor(getResources().getColor(R.color.color_blue));
                radioBtnTab1.setTextColor(getResources().getColor(R.color.color_white));
                radioBtnTab2.setTextColor(getResources().getColor(R.color.color_blue));
                initPicChart(1);
                break;
            case R.id.radio_btn_tab_2:
                radioBtnTab0.setTextColor(getResources().getColor(R.color.color_blue));
                radioBtnTab1.setTextColor(getResources().getColor(R.color.color_blue));
                radioBtnTab2.setTextColor(getResources().getColor(R.color.color_white));
                initPicChart(2);
                break;
        }
    }

    private void initPicChart(int type) {
        List<PieEntry> strings = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        switch (type) {
            case 0:
                if (blacklistResultBo.getOneMonthTotalCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getOneMonthTotalCount(), "多头借贷"));
                    colors.add(getResources().getColor(R.color.color_FF7E4B));
                }
                if (blacklistResultBo.getOneMonthBankCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getOneMonthBankCount(), "消费金融"));
                    colors.add(getResources().getColor(R.color.color_4173F4));
                }
                if (blacklistResultBo.getOneMonthSmallCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getOneMonthSmallCount(), "小额贷款"));
                    colors.add(getResources().getColor(R.color.color_FFC32E));
                }
                if (blacklistResultBo.getOneMonthP2pCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getOneMonthP2pCount(), "P2P网贷"));
                    colors.add(getResources().getColor(R.color.color_01F6BC));
                }
                if (blacklistResultBo.getOneMonthOtherCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getOneMonthOtherCount(), "其他网贷"));
                    colors.add(getResources().getColor(R.color.color_gray_1));
                }
                break;
            case 1:
                if (blacklistResultBo.getThreeMonthTotalCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getThreeMonthTotalCount(), "多头借贷"));
                    colors.add(getResources().getColor(R.color.color_FF7E4B));
                }
                if (blacklistResultBo.getThreeMonthBankCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getThreeMonthBankCount(), "消费金融"));
                    colors.add(getResources().getColor(R.color.color_4173F4));
                }
                if (blacklistResultBo.getThreeMonthSmallCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getThreeMonthSmallCount(), "小额贷款"));
                    colors.add(getResources().getColor(R.color.color_FFC32E));
                }
                if (blacklistResultBo.getThreeMonthP2pCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getThreeMonthP2pCount(), "P2P网贷"));
                    colors.add(getResources().getColor(R.color.color_01F6BC));
                }
                if (blacklistResultBo.getThreeMonthOtherCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getThreeMonthOtherCount(), "其他网贷"));
                    colors.add(getResources().getColor(R.color.color_gray_1));
                }
                break;
            case 2:
                if (blacklistResultBo.getSixMonthTotalCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getSixMonthTotalCount(), "多头借贷"));
                    colors.add(getResources().getColor(R.color.color_FF7E4B));
                }
                if (blacklistResultBo.getSixMonthBankCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getSixMonthBankCount(), "消费金融"));
                    colors.add(getResources().getColor(R.color.color_4173F4));
                }
                if (blacklistResultBo.getSixMonthSmallCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getSixMonthSmallCount(), "小额贷款"));
                    colors.add(getResources().getColor(R.color.color_FFC32E));
                }
                if (blacklistResultBo.getSixMonthP2pCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getSixMonthP2pCount(), "P2P网贷"));
                    colors.add(getResources().getColor(R.color.color_01F6BC));
                }
                if (blacklistResultBo.getSixMonthOtherCount() != 0) {
                    strings.add(new PieEntry(blacklistResultBo.getSixMonthOtherCount(), "其他网贷"));
                    colors.add(getResources().getColor(R.color.color_gray_1));
                }
                break;
        }
        PieDataSet dataSet = new PieDataSet(strings, "");
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        picChart.setData(pieData);
        picChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
    }

    @Override
    public void onNothingSelected() {
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
