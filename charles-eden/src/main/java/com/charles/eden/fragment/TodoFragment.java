package com.charles.eden.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.activity.MainActivity;
import com.charles.eden.activity.TodoListActivity;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.model.bo.NoteTypeBo;
import com.charles.utils.Logger;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;
import com.charles.utils.base.BaseFragment;
import com.charles.utils.http.HttpResult;
import com.charles.utils.view.RecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-22 14:16
 */
public class TodoFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private MyAdapter mMyAdapter;
    private List<NoteTypeBo> mNoteTypeBo;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_todo;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        mMyAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(mContext, TodoListActivity.class);
            intent.putExtra("note_type_id", mNoteTypeBo.get(position).getId());
            intent.putExtra("note_type_name", mNoteTypeBo.get(position).getName());
            startActivity(intent);
        });
        swipeRefreshLayout.setOnRefreshListener(this::initData);
    }

    @Override
    protected void initData() {
        RetrofitHelper.INSTANCE.post(getActivity(), new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.noteType(1);
            }

            @Override
            public void onResult(HttpResult result) {
                swipeRefreshLayout.setRefreshing(false);
                mNoteTypeBo = JSONArray.parseArray(result.getStringData(), NoteTypeBo.class);
                Logger.d("请求成功，size = " + mNoteTypeBo.size());
                if (mNoteTypeBo != null && mNoteTypeBo.size() > 0) {
                    mMyAdapter.notifyDataSetChanged();
                }
            }
        });
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
            RetrofitHelper.INSTANCE.post(mActivity, new RetrofitHelper.RetrofitCallback() {
                @Override
                public Observable<HttpResult> getObservable(HttpService httpService) {
                    NoteTypeBo noteTypeBo = new NoteTypeBo();
                    noteTypeBo.setName(typeName);
                    noteTypeBo.setDescription(typeDesc);
                    noteTypeBo.setType(1);
                    return httpService.addNoteType(noteTypeBo);
                }

                @Override
                public void onResult(HttpResult result) {
                    ToastUtils.show(mContext, result.getMsg());
                    initData();
                }
            });
            dialog.dismiss();
        });
        editDialog.create().show();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private RecyclerItemClickListener mOnItemClickListener;

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_note, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            NoteTypeBo noteTypeBo = mNoteTypeBo.get(position);
            holder.textName.setText(noteTypeBo.getName());
            holder.textName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            holder.imgSetTop.setVisibility(View.VISIBLE);
            holder.layoutHead.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClickListener(position);
                }
            });
            holder.imgMore.setOnClickListener(v -> {
                View view = LayoutInflater.from(mContext).inflate(R.layout.popup_item, null, false);
                ImageView imgAlert = view.findViewById(R.id.img_alert);
                ImageView imgSetTop = view.findViewById(R.id.img_set_top);
                ImageView imgDelete = view.findViewById(R.id.img_delete);
                final PopupWindow popWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popWindow.setTouchable(true);
                popWindow.setTouchInterceptor((v1, event) -> false);
                popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                popWindow.showAsDropDown(v, 50, 0);
                imgAlert.setOnClickListener(v12 -> {
                    Toast.makeText(mContext, "你点击了嘻嘻~", Toast.LENGTH_SHORT).show();
                    popWindow.dismiss();
                });
                imgSetTop.setOnClickListener(v13 -> {
                    Toast.makeText(mContext, "你点击了呵呵~", Toast.LENGTH_SHORT).show();
                    popWindow.dismiss();
                });
                imgDelete.setOnClickListener(v13 -> {
//                    RetrofitHelper.INSTANCE.post(mActivity, new RetrofitHelper.RetrofitCallback() {
//                        @Override
//                        public Observable<HttpResult> getObservable(HttpService httpService) {
//                            NoteTypeBo noteTypeBo = new NoteTypeBo();
//                            noteTypeBo.setName(typeName);
//                            noteTypeBo.setDescription(typeDesc);
//                            noteTypeBo.setType(1);
//                            return httpService.addNoteType(noteTypeBo);
//                        }
//
//                        @Override
//                        public void onResult(HttpResult result) {
//                            ToastUtils.show(mContext, result.getMsg());
//                            initData();
//                        }
//                    });
                    Toast.makeText(mContext, "你点击了删除~", Toast.LENGTH_SHORT).show();
                    popWindow.dismiss();
                });
            });
        }

        void setOnItemClickListener(RecyclerItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        @Override
        public int getItemCount() {
            return mNoteTypeBo == null ? 0 : mNoteTypeBo.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            View layoutHead;
            TextView textName;
            TextView textDesc;
            ImageView imgSetTop;
            ImageView imgMore;

            MyViewHolder(View itemView) {
                super(itemView);
                layoutHead = itemView.findViewById(R.id.layout_head);
                textName = itemView.findViewById(R.id.text_name);
                textDesc = itemView.findViewById(R.id.text_desc);
                imgSetTop = itemView.findViewById(R.id.img_set_top);
                imgMore = itemView.findViewById(R.id.img_more);
            }
        }
    }
}
