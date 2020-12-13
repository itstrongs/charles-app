package com.charles.todo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.charles.todo.domain.Todo;
import com.charles.todo.http.HttpService;
import com.charles.todo.http.RetrofitHelper;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.spinner_type)
    AppCompatSpinner spinnerType;
    @BindView(R.id.recycler_type)
    RecyclerView recyclerType;
    @BindView(R.id.recycler_todo)
    RecyclerView recyclerTodo;
    @BindView(R.id.edit_todo)
    AppCompatEditText editTodo;

    private Context mContext;
    private List<Todo> mTodos;
    private TodoAdapter mTodoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        initData();
    }

    private void initData() {
        mTodos = new ArrayList<>();
        Todo todo = new Todo();
        RetrofitHelper.INSTANCE.post(this, Todo.class, new RetrofitHelper.RetrofitCallback<Todo>() {
            @Override
            public Observable<JSONObject> getObservable(HttpService httpService) {
                return httpService.todo(todo);
            }

            @Override
            public void onResult(String msg, Todo result) {
                ToastUtils.show(mContext, msg);
                mTodos.add(todo);
                mTodoAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerType.setLayoutManager(linearLayoutManager);
        recyclerType.setAdapter(new TypeAdapter());

        recyclerTodo.setLayoutManager(new LinearLayoutManager(this));
        recyclerTodo.setAdapter(mTodoAdapter = new TodoAdapter());
    }

    @OnClick(R.id.img_add)
    public void onViewClicked() {
        String todoContent = editTodo.getText().toString().trim();
        if (StringUtils.isEmpty(todoContent)) {
            ToastUtils.show(mContext, "内容为空！");
            return;
        }

        Todo todo = new Todo(todoContent);
        RetrofitHelper.INSTANCE.post(this, Todo.class, new RetrofitHelper.RetrofitCallback<Todo>() {
            @Override
            public Observable<JSONObject> getObservable(HttpService httpService) {
                return httpService.todo(todo);
            }

            @Override
            public void onResult(String msg, Todo result) {
                ToastUtils.show(mContext, msg);
                mTodos.add(todo);
                mTodoAdapter.notifyDataSetChanged();
            }
        });
    }

    private class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_todo, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            Todo todo = mTodos.get(position);
            holder.radioButton.setText(todo.getContent());
        }

        @Override
        public int getItemCount() {
            return mTodos == null ? 0 : mTodos.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            RadioButton radioButton;

            MyViewHolder(View itemView) {
                super(itemView);
                radioButton = itemView.findViewById(R.id.radio_todo);
            }
        }
    }

    private class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_type, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textType;

            MyViewHolder(View itemView) {
                super(itemView);
                textType = itemView.findViewById(R.id.text_type);
            }
        }
    }
}
