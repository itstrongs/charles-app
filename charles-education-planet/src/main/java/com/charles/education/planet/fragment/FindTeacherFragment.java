package com.charles.education.planet.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.charles.education.planet.R;
import com.charles.utils.base.BaseFragment;
import com.charles.utils.view.RecyclerItemClickListener;

import butterknife.BindView;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2020-03-03 12:43
 */
public class FindTeacherFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyAdapter mMyAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_find_teacher;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
    }

    @Override
    protected void initData() {
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private RecyclerItemClickListener mOnItemClickListener;

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_find_teacher, parent, false));
        }

        public void setOnItemClickListener(RecyclerItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            MyViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
