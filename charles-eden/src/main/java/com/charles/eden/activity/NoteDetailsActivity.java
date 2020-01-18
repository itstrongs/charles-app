package com.charles.eden.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.charles.eden.R;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.model.bo.NotePlanBo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月02日 17:49
 */
public class NoteDetailsActivity extends BaseActivity {

    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_content)
    TextView textContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_plan_details);
        ButterKnife.bind(this);
        NotePlanBo recordPlanBo = (NotePlanBo) getIntent().getSerializableExtra("data");
        textName.setText(recordPlanBo.getName());
        textContent.setText(recordPlanBo.getContent());
    }
}
