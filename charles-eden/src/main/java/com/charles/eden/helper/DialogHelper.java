package com.charles.eden.helper;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.charles.eden.R;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-24 19:37
 */
public enum DialogHelper {

    INSTANCE;

    public void showDialog(Activity activity, String name, String desc, OnDialogAddListener listener) {
        final View view = LayoutInflater.from(activity).inflate(R.layout.dialog_add_type, null, false);
        EditText editName = view.findViewById(R.id.edit_name);
        EditText editDesc = view.findViewById(R.id.edit_desc);
        editName.setText(name);
        editDesc.setText(desc);
        AlertDialog.Builder editDialog = new AlertDialog.Builder(activity);
        editDialog.setTitle("添加分类");
        editDialog.setIcon(R.mipmap.ic_launcher);
        editDialog.setView(view);
        editDialog.setPositiveButton("添加", (dialog, which) -> {
//            EditText editName = view.findViewById(R.id.edit_name);
//            EditText editDesc = view.findViewById(R.id.edit_desc);
            final String typeName = editName.getText().toString().trim();
            final String typeDesc = editDesc.getText().toString().trim();
            if (StringUtils.isEmpty(typeName)) {
                ToastUtils.show(activity, "类型名不能为空");
                return;
            }
            listener.add(typeName);
            dialog.dismiss();
        });
        editDialog.create().show();
    }

    public void showDialog(Activity activity, OnDialogAddListener listener) {
        showDialog(activity, "", "", listener);
    }

    public interface OnDialogAddListener {

        void add(String content);
    }
}
