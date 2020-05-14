package com.charles.eden.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONObject;
import com.charles.eden.R;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.model.SectionsPagerAdapter;
import com.charles.eden.model.bean.NoteTypeBo;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;
import com.charles.utils.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

import static com.charles.eden.model.ConstantPool.LIST_TAB_TEXT;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-22 14:16
 */
public class TodoTabFragment extends BaseFragment {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_todo_tab;
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(new SectionsPagerAdapter(mContext, getFragmentManager(), LIST_TAB_TEXT, TodoFragment::newInstance));
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick({R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_add:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_add_type, null, false);
        AlertDialog.Builder editDialog = new AlertDialog.Builder(mContext);
        editDialog.setTitle("添加分类");
        editDialog.setIcon(R.mipmap.ic_launcher);
        editDialog.setView(view);
        editDialog.setPositiveButton("添加", (dialog, which) -> {
            EditText editName = view.findViewById(R.id.edit_name);
            EditText editDesc = view.findViewById(R.id.edit_desc);
            final String typeName = editName.getText().toString().trim();
            final String typeDesc = editDesc.getText().toString().trim();
            if (StringUtils.isEmpty(typeName)) {
                ToastUtils.show(mContext, "类型名不能为空");
                return;
            }
            RetrofitHelper.INSTANCE.post(mActivity, NoteTypeBo.class, new RetrofitHelper.RetrofitCallback<NoteTypeBo>() {
                @Override
                public Observable<JSONObject> getObservable(HttpService httpService) {
                    NoteTypeBo noteTypeBo = new NoteTypeBo();
                    noteTypeBo.setName(typeName);
                    noteTypeBo.setDescription(typeDesc);
                    noteTypeBo.setModuleType(1);
                    return httpService.addModuleType(noteTypeBo);
                }

                @Override
                public void onResult(String msg, NoteTypeBo noteTypeBo) {
                    ToastUtils.show(mContext, msg);
                    initData();
                }
            });
            dialog.dismiss();
        });
        editDialog.create().show();
    }
}
