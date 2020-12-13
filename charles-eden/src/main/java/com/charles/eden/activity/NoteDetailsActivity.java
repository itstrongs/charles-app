package com.charles.eden.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.charles.eden.R;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.model.bean.NotePlanBo;
import com.zzhoujay.markdown.MarkDown;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月02日 17:49
 */
public class NoteDetailsActivity extends BaseActivity {

    @BindView(R.id.text_title)
    TextView textTitle;
//    @BindView(R.id.text_content)
//    TextView textContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_plan_details);
        ButterKnife.bind(this);
        NotePlanBo recordPlanBo = (NotePlanBo) getIntent().getSerializableExtra("data");
        textTitle.setText(recordPlanBo.getName());
//        textContent.setText(recordPlanBo.getContent());

        try {
            InputStream open = getAssets().open("分布式问题.md");
            textTitle.post(() -> {
                Spanned spanned = MarkDown.fromMarkdown(open, source -> {
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                    drawable.setBounds(0, 0, 400, 400);
                    return drawable;
                }, textTitle);
                textTitle.setText(spanned);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
