package com.charles.myapp.fragment.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.charles.library.utils.SPHelper;
import com.charles.library.utils.ToastUtils;
import com.charles.myapp.R;
import com.charles.myapp.data.ConstantPool;
import com.charles.myapp.service.YYSService;

/**
 * Created by itstrongs on 2017/11/25.
 */
public class YYSFragment extends Fragment {

    @BindView(R.id.edit_yys_x)
    EditText editYysX;
    @BindView(R.id.edit_yys_y)
    EditText editYysY;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yys, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editYysX.setText(String.valueOf(SPHelper.getInt(getContext(), ConstantPool.SP_YYS_X, 850)));
        editYysY.setText(String.valueOf(SPHelper.getInt(getContext(), ConstantPool.SP_YYS_Y, 450)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        getContext().stopService(new Intent(getContext(), YYSService.class));
    }

    @OnClick({R.id.btn_0, R.id.btn_1, R.id.btn_yys_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                getActivity().startService(new Intent(getContext(), YYSService.class));
                break;
            case R.id.btn_1:
                getActivity().stopService(new Intent(getContext(), YYSService.class));
                break;
            case R.id.btn_yys_ok:
                try {
                    SPHelper.putInt(getContext(), ConstantPool.SP_YYS_X, Integer.parseInt(editYysX.getText().toString()));
                    SPHelper.putInt(getContext(), ConstantPool.SP_YYS_Y, Integer.parseInt(editYysY.getText().toString()));
                } catch (Exception e) {
                    ToastUtils.show(getContext(), "请输入数字");
                }
                break;
        }
    }
}