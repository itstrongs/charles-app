package com.charles.eden.fragment;

import android.text.TextUtils;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.charles.eden.R;
import com.charles.eden.model.SectionsPagerAdapter;
import com.charles.eden.view.InputTextMsgDialog;
import com.charles.utils.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

import static com.charles.eden.model.ConstantPool.LIST_TAB_TEXT;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-22 14:23
 */
public class MicroSentenceFragment extends BaseFragment {

    @BindView(R.id.view_pager_micro_sentence)
    ViewPager viewPager;
    @BindView(R.id.tab_layout_micro_sentence)
    TabLayout tabLayout;

    private InputTextMsgDialog inputTextMsgDialog;
    private Timer timer;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_micro_sentence;
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(new SectionsPagerAdapter(mContext, getFragmentManager(), LIST_TAB_TEXT, MicroSentenceTabFragment::newInstance));
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.img_add)
    public void onViewClicked() {
        showSoft();
    }

    private void showSoft() {
        if (inputTextMsgDialog == null) {
            inputTextMsgDialog = new InputTextMsgDialog(mActivity, R.style.dialog_center);
            inputTextMsgDialog.setHint("请输入聊天内容");
        }
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(() -> inputTextMsgDialog.showKeyboard());
            }
        }, 100);

        inputTextMsgDialog.show();
        inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
            @Override
            public void onInputTextString(String msg) {
                //  LogUtils.d("msg===========" + msg);
            }

            @Override
            public void onClickSend(String message) {
                String comment = message.replace(" ", "");
                if (!TextUtils.isEmpty(comment)) {
                    Toast.makeText(mContext, comment, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "请输入聊天内容", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
