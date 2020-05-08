package com.charles.eden.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelperBak;
import com.charles.eden.model.bo.NotePlanBo;
import com.charles.eden.model.bo.NoteTypeBo;
import com.charles.utils.Logger;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;
import com.charles.utils.http.HttpResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月02日 15:04
 */
public class NoteListActivity extends BaseActivity {

    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_desc)
    TextView textDesc;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private NoteTypeBo noteTypeBo;
    private List<NotePlanBo> recordPlanBos;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        ButterKnife.bind(this);
        noteTypeBo = (NoteTypeBo) getIntent().getSerializableExtra("record_plan_type");
        textName.setText(noteTypeBo.getName());
        textDesc.setText(noteTypeBo.getDescription());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        getRecordPlanData();
    }

    private void getRecordPlanData() {
        RetrofitHelperBak.INSTANCE.post(this, new RetrofitHelperBak.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.listByTypeId(noteTypeBo.getId());
            }

            @Override
            public void onResult(HttpResult result) {
                recordPlanBos = JSONArray.parseArray(result.getStringData(), NotePlanBo.class);
                if (recordPlanBos != null && recordPlanBos.size() > 0) {
                    mMyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @OnClick(R.id.img_add)
    public void onViewClicked() {
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_type, null, false);
        AlertDialog.Builder editDialog = new AlertDialog.Builder(this);
        editDialog.setTitle("添加" + noteTypeBo.getName());
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
            NotePlanBo recordPlanBo = new NotePlanBo();
            recordPlanBo.setTypeId(noteTypeBo.getId());
            recordPlanBo.setName(typeName);
            recordPlanBo.setContent(typeDesc);
            saveRecordPlan(recordPlanBo);
            dialog.dismiss();
        });
        editDialog.create().show();
    }

    private void saveRecordPlan(final NotePlanBo recordPlanBo) {
        RetrofitHelperBak.INSTANCE.post(mActivity, new RetrofitHelperBak.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.addRecord(recordPlanBo);
            }

            @Override
            public void onResult(HttpResult result) {
                ToastUtils.show(mContext, result.getMsg());
                getRecordPlanData();
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_note_list, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            final NotePlanBo notePlanBo = recordPlanBos.get(position);
            Logger.d("笔记：" + notePlanBo);
            holder.textName.setText(position + 1 + "." + " " + notePlanBo.getName());
            if (notePlanBo.getSetTop()) {
                holder.textName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                holder.imgSetTop.setVisibility(View.VISIBLE);
            }
            switch (noteTypeBo.getType()) {
                case 0:
                    holder.textContent.setVisibility(View.VISIBLE);
                    holder.textContent.setOnClickListener(v -> {
                        Intent intent = new Intent(mContext, NoteDetailsActivity.class);
                        intent.putExtra("data", recordPlanBos.get(position));
                        startActivity(intent);
                    });
                    break;
                case 1:
//                    holder.checkBox.setVisibility(View.VISIBLE);
//                    holder.checkBox.setChecked(notePlanBo.getState() != null && notePlanBo.getState() == 1);
                    holder.viewLine.setVisibility(notePlanBo.getState() == null || notePlanBo.getState() == 0 ? View.GONE : View.VISIBLE);
//                    holder.checkBox.setOnClickListener(v -> {
//                        holder.viewLine.setVisibility(holder.checkBox.isChecked() ? View.VISIBLE : View.GONE);
//                        notePlanBo.setState(holder.checkBox.isChecked() ? 1 : 0);
//                        RetrofitHelperBak.INSTANCE.post(mActivity, new RetrofitHelperBak.RetrofitCallback() {
//                            @Override
//                            public Observable<HttpResult> getObservable(HttpService httpService) {
//                                return httpService.addRecord(notePlanBo);
//                            }
//
//                            @Override
//                            public void onResult(HttpResult result) {
//                            }
//                        });
//                    });
                    break;
                case 2:
//                    holder.btnCard.setVisibility(View.VISIBLE);
                    break;
            }
            holder.textContent.setText(notePlanBo.getContent());
            holder.textDate.setText(notePlanBo.getUpdateAt());
        }

        @Override
        public int getItemCount() {
            return recordPlanBos == null ? 0 : recordPlanBos.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textName;
            View viewLine;
//            CheckBox checkBox;
//            TextView btnCard;
            TextView textContent;
            TextView textDate;
            ImageView imgSetTop;

            MyViewHolder(View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_name);
                viewLine = itemView.findViewById(R.id.view_line);
//                checkBox = itemView.findViewById(R.id.check_box);
//                btnCard = itemView.findViewById(R.id.btn_card);
                textContent = itemView.findViewById(R.id.text_content);
                textDate = itemView.findViewById(R.id.text_date);
                imgSetTop = itemView.findViewById(R.id.img_set_top);
            }
        }
    }
}
