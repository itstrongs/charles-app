package com.charles.eden.activity;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.charles.eden.R;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.helper.DialogHelper;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.helper.RetrofitHelperBak;
import com.charles.eden.model.bo.NoteTypeBo;
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
                DialogHelper.INSTANCE.showDialog(mActivity, (content) -> RetrofitHelper.INSTANCE.post(mActivity, NoteTypeBo.class, new RetrofitHelper.RetrofitCallback<NoteTypeBo>() {
                    @Override
                    public Observable<JSONObject> getObservable(HttpService httpService) {
                        TodoPlanBo todoPlanBo = new TodoPlanBo();
                        todoPlanBo.setTypeId(mNoteTypeId);
                        todoPlanBo.setContent(content);
                        return httpService.todoPlan(todoPlanBo);
                    }

                    @Override
                    public void onResult(String msg, NoteTypeBo userBo) {
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
            holder.viewLine.setVisibility(todoPlanBo.getFinish() == null || todoPlanBo.getFinish() ? View.VISIBLE : View.GONE);
            holder.textContent.setText(todoPlanBo.getContent());
            holder.textDate.setText(todoPlanBo.getUpdatedAt());
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
                    popWindow.dismiss();
                    DialogHelper.INSTANCE.showDialog(mActivity, todoPlanBo.getContent(), "", content -> {
                        RetrofitHelper.INSTANCE.post(mActivity, NoteTypeBo.class, new RetrofitHelper.RetrofitCallback<NoteTypeBo>() {
                            @Override
                            public Observable<JSONObject> getObservable(HttpService httpService) {
                                todoPlanBo.setContent(content);
                                return httpService.todoPlan(todoPlanBo);
                            }

                            @Override
                            public void onResult(String msg, NoteTypeBo userBo) {
                                mMyAdapter.notifyDataSetChanged();
                            }
                        });
                    });
                });
                imgSetTop.setOnClickListener(v13 -> {
                    todoPlanBo.setFinish(!todoPlanBo.getFinish());
                    holder.viewLine.setVisibility(todoPlanBo.getFinish() ? View.VISIBLE : View.GONE);
                    RetrofitHelperBak.INSTANCE.post(mActivity, new RetrofitHelperBak.RetrofitCallback() {
                        @Override
                        public Observable<HttpResult> getObservable(HttpService httpService) {
                            return httpService.todoFinish(todoPlanBo.getId(), todoPlanBo.getFinish());
                        }

                        @Override
                        public void onResult(HttpResult result) {
                        }
                    });
                    popWindow.dismiss();
                });
                imgDelete.setOnClickListener(v13 -> {
                    Toast.makeText(mContext, "你点击了删除~", Toast.LENGTH_SHORT).show();
                    popWindow.dismiss();
                });
            });
        }

        @Override
        public int getItemCount() {
            return mTodoPlanBo == null ? 0 : mTodoPlanBo.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textName;
            View viewLine;
            TextView textContent;
            TextView textDate;
            ImageView imgSetTop;
            ImageView imgMore;

            MyViewHolder(View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_name);
                viewLine = itemView.findViewById(R.id.view_line);
                textContent = itemView.findViewById(R.id.text_content);
                textDate = itemView.findViewById(R.id.text_date);
                imgSetTop = itemView.findViewById(R.id.img_set_top);
                imgMore = itemView.findViewById(R.id.img_more);
            }
        }
    }
}
