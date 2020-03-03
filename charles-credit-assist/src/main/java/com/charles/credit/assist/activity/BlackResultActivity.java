package com.charles.credit.assist.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.bo.BlacklistCommonResultBo;
import com.charles.library.utils.DateUtils;
import com.charles.library.utils.ToastUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.charles.library.utils.DateUtils.DATETIME_FORMAT_DAY;

/**
 * 网黑报告结果页
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年07月28日 10:31
 */
public class BlackResultActivity extends BaseActivity implements OnChartValueSelectedListener {

    @BindView(R.id.pic_chart)
    PieChart picChart;
    @BindView(R.id.text_score_desc)
    TextView textScoreDesc;
    @BindView(R.id.text_score)
    TextView textScore;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_telephone)
    TextView textTelephone;
    @BindView(R.id.text_user_age)
    TextView textAge;
    @BindView(R.id.text_id_card)
    TextView textIdCard;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.text_update_time)
    TextView textUpdateTime;
    @BindView(R.id.text_report_no)
    TextView textReportNo;
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
    @BindView(R.id.radio_btn_tab_0)
    RadioButton radioBtnTab0;
    @BindView(R.id.radio_btn_tab_1)
    RadioButton radioBtnTab1;
    @BindView(R.id.radio_btn_tab_2)
    RadioButton radioBtnTab2;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_result);
        ButterKnife.bind(this);
        String result = getIntent().getStringExtra("result");
//        String result = "{\"idCardBrokenPromise\":\"未命中\",\"sixMonthP2pCount\":7,\"hitCount\":1,\"idCard\":\"1521**********7218\",\"refuseRate\":55,\"oneMonthBankCount\":0,\"riskControl\":[{\"desc\":\"3个月内不要申请超过20次贷款，控制申请频率\"}],\"creditManagerId\":2,\"creditLevel\":\"优秀\",\"applyNotSelf\":\"未命中\",\"limit\":8000,\"threeMonthOtherCount\":4,\"oneMonthSmallCount\":1,\"limitDesc\":\"你的信用状况一般，建议选择高通过率贷款\",\"overdueDesc\":\"未命中\",\"threeMonthTotalCount\":8,\"threeMonthSmallCount\":1,\"address\":\"云南 - 西双版纳傣族自治州\",\"reportId\":\"156516869811918306918831\",\"sixMonthTotalCount\":13,\"sixMonthOtherCount\":4,\"sex\":1,\"oneMonthTotalCount\":5,\"threeMonthP2pCount\":3,\"oneMonthOtherCount\":3,\"sixMonthSmallCount\":2,\"refuseLevel\":\"较高\",\"oneMonthP2pCount\":1,\"phoneRisk\":\"未命中\",\"phone\":\"183****8831\",\"name\":\"滕立波\",\"sixMonthBankCount\":0,\"applyDebtRisk\":\"命中\",\"threeMonthBankCount\":0,\"age\":30}";
        blacklistResultBo = JSONObject.parseObject(result, BlacklistCommonResultBo.class);
        if (blacklistResultBo != null) {
            initData();
            initPicChart(0);
        } else {
            ToastUtils.show(mContext, "查询异常");
        }
    }

    private void initData() {
        textScoreDesc.setText("经第三方风控平台的综合评价，\r\n您的网贷被拒率为" + blacklistResultBo.getRefuseRate() + "%");
        textScore.setText(blacklistResultBo.getRefuseRate() + "%");
        textName.setText(blacklistResultBo.getName());
        textTelephone.setText(blacklistResultBo.getPhone());
        textAge.setText(blacklistResultBo.getAge() + "岁");
        textIdCard.setText(blacklistResultBo.getIdCard());
        textAddress.setText(blacklistResultBo.getAddress());
        textUpdateTime.setText(DateUtils.format(new Date(), DATETIME_FORMAT_DAY));
        textReportNo.setText(blacklistResultBo.getReportId());
        if (!"未命中".equals(blacklistResultBo.getIdCardBrokenPromise())) {
            changeText(textRisk0);
            textRiskContent0.setText("您命中本项风险");
        }
        if (!"未命中".equals(blacklistResultBo.getPhoneRisk())) {
            changeText(textRisk1);
            textRiskContent1.setText("您命中本项风险");
        }
        if (!"未命中".equals(blacklistResultBo.getApplyNotSelf())) {
            changeText(textRisk2);
            textRiskContent2.setText("您命中本项风险");
        }
        if (!"未命中".equals(blacklistResultBo.getApplyDebtRisk())) {
            changeText(textRisk3);
            textRiskContent3.setText("您命中本项风险");
        }
        if (!"未命中".equals(blacklistResultBo.getOverdueDesc())) {
            changeText(textRisk4);
            textRiskContent4.setText("您命中本项风险");
        }
    }

    private void changeText(TextView textView) {
        textView.setText("命中");
        textView.setTextColor(Color.parseColor("#FE0000"));
        textView.setBackgroundResource(R.drawable.shape_bg_red_25);
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
}
