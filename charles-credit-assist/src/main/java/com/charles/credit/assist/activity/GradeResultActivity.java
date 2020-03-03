package com.charles.credit.assist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 详细信用报告
 *
 * @Author: liufengqiang
 * @Date: 2019/6/7
 */
public class GradeResultActivity extends BaseActivity {

    @BindView(R.id.text_score_name)
    TextView textScoreName;
    @BindView(R.id.text_score_desc)
    TextView textScoreDesc;
    @BindView(R.id.text_score)
    TextView textScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        String result = getIntent().getStringExtra("result");
        JSONObject jsonObject = JSONObject.parseObject(result);
        textScoreName.setText("您的反欺诈评级分数为" + jsonObject.getInteger("score") + "分");
        textScoreDesc.setText("综合拒贷率为" + jsonObject.getInteger("score") + "%");
        textScore.setText(jsonObject.getInteger("score") + "分");
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.img_back, R.id.img_close, R.id.btn_get})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
            case R.id.img_close:
                onBackPressed();
                break;
            case R.id.btn_get:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
