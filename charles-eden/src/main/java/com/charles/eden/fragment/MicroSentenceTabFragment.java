package com.charles.eden.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelperBak;
import com.charles.eden.model.bo.NoteTypeBo;
import com.charles.utils.Logger;
import com.charles.utils.base.BaseFragment;
import com.charles.utils.http.HttpResult;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

import static com.charles.eden.model.ConstantPool.ARG_SECTION_NUMBER;

public class MicroSentenceTabFragment extends BaseFragment {

    @BindView(R.id.swipe_micro_sentence)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view_micro_sentence)
    RecyclerView recyclerView;

    private MyAdapter mMyAdapter;
    private List<NoteTypeBo> mNoteTypeBo;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_micro_sentence_tab;
    }

    static MicroSentenceTabFragment newInstance(int index) {
        MicroSentenceTabFragment fragment = new MicroSentenceTabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        swipeRefreshLayout.setOnRefreshListener(this::initData);
    }

    @Override
    protected void initData() {
        RetrofitHelperBak.INSTANCE.post(getActivity(), new RetrofitHelperBak.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.listModuleType(1, getArguments().getInt(ARG_SECTION_NUMBER));
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

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_micro_sentence, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            MyViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
