package com.charles.eden.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.helper.DialogHelper;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelperBak;
import com.charles.eden.model.bo.TodoPlanBo;
import com.charles.utils.http.HttpResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-24 19:07
 */
public class TodoListActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<TodoPlanBo> mTodoPlanBo;
    private long mNoteTypeId;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        mNoteTypeId = getIntent().getLongExtra("note_type_id", 0L);
        textTitle.setText(getIntent().getStringExtra("note_type_name"));
        swipeRefreshLayout.setOnRefreshListener(this::initData);
        if (mNoteTypeId != 0L) {
            initData();
        }
    }

    private void initData() {
        RetrofitHelperBak.INSTANCE.post(mActivity, new RetrofitHelperBak.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.todoList(mNoteTypeId);
            }

            @Override
            public void onResult(HttpResult result) {
                swipeRefreshLayout.setRefreshing(false);
                mTodoPlanBo = JSONArray.parseArray(result.getStringData(), TodoPlanBo.class);
                if (mTodoPlanBo != null && mTodoPlanBo.size() > 0) {
                    mMyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @OnClick({R.id.text_back, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_back:
                finish();
                break;
            case R.id.img_add:
                DialogHelper.showDialog(mActivity, (content) -> RetrofitHelperBak.INSTANCE.post(mActivity, new RetrofitHelperBak.RetrofitCallback() {
                    @Override
                    public Observable<HttpResult> getObservable(HttpService httpService) {
                        TodoPlanBo todoPlanBo = new TodoPlanBo();
                        todoPlanBo.setTypeId(mNoteTypeId);
                        todoPlanBo.setContent(content);
                        return httpService.todoPlan(todoPlanBo);
                    }

                    @Override
                    public void onResult(HttpResult result) {
                        initData();
                    }
                }));
                break;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_note_list, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            TodoPlanBo todoPlanBo = mTodoPlanBo.get(position);
            holder.textName.setText(position + 1 + "." + " " + todoPlanBo.getContent());
            if (todoPlanBo.getSetTop()) {
                holder.textName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                holder.imgSetTop.setVisibility(View.VISIBLE);
            }
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(todoPlanBo.getFinish() != null && todoPlanBo.getFinish());
            holder.viewLine.setVisibility(todoPlanBo.getFinish() == null || todoPlanBo.getFinish() ? View.VISIBLE : View.GONE);
            holder.checkBox.setOnClickListener(v -> {
                holder.viewLine.setVisibility(holder.checkBox.isChecked() ? View.VISIBLE : View.GONE);
                todoPlanBo.setFinish(holder.checkBox.isChecked());
                RetrofitHelperBak.INSTANCE.post(mActivity, new RetrofitHelperBak.RetrofitCallback() {
                    @Override
                    public Observable<HttpResult> getObservable(HttpService httpService) {
                        return httpService.todoFinish(todoPlanBo.getId(), holder.checkBox.isChecked());
                    }

                    @Override
                    public void onResult(HttpResult result) {
                    }
                });
            });
            holder.textContent.setText(todoPlanBo.getContent());
            holder.textDate.setText(todoPlanBo.getUpdatedAt());
            holder.imgEdit.setOnClickListener(v -> DialogHelper.showDialog(mActivity, todoPlanBo.getContent(), "", (content) -> RetrofitHelperBak.INSTANCE.post(mActivity, new RetrofitHelperBak.RetrofitCallback() {
                @Override
                public Observable<HttpResult> getObservable(HttpService httpService) {
                    todoPlanBo.setTypeId(mNoteTypeId);
                    todoPlanBo.setContent(content);
                    return httpService.todoPlan(todoPlanBo);
                }

                @Override
                public void onResult(HttpResult result) {
                    initData();
                }
            })));
        }

        @Override
        public int getItemCount() {
            return mTodoPlanBo == null ? 0 : mTodoPlanBo.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textName;
            View viewLine;
            CheckBox checkBox;
            TextView textContent;
            TextView textDate;
            ImageView imgSetTop;
            ImageView imgEdit;

            MyViewHolder(View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_name);
                viewLine = itemView.findViewById(R.id.view_line);
                checkBox = itemView.findViewById(R.id.check_box);
                textContent = itemView.findViewById(R.id.text_content);
                textDate = itemView.findViewById(R.id.text_date);
                imgSetTop = itemView.findViewById(R.id.img_set_top);
                imgEdit = itemView.findViewById(R.id.img_edit);
            }
        }
    }
}
