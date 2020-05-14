package com.charles.eden.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONObject;
import com.charles.eden.R;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.model.bean.MicroSentence;
import com.charles.utils.base.BaseFragment;

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
    private List<MicroSentence> microSentences;

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
        RetrofitHelper.INSTANCE.post(getActivity(), MicroSentence.class, new RetrofitHelper.RetrofitListCallback<MicroSentence>() {
            @Override
            public Observable<JSONObject> getObservable(HttpService httpService) {
                return httpService.microSentence(getArguments().getInt(ARG_SECTION_NUMBER));
            }

            @Override
            public void onResult(String msg, List<MicroSentence> result) {
                swipeRefreshLayout.setRefreshing(false);
                microSentences = result;
                mMyAdapter.notifyDataSetChanged();
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
            MicroSentence microSentence = microSentences.get(position);
            holder.textCategory.setText("#" + microSentence.getCategory() + "#");
            holder.textSource.setText("+ " + microSentence.getSource());
            holder.textContent.setText(microSentence.getContent());
            Drawable drawable = getResources().getDrawable(microSentence.getFavour() ? R.drawable.ic_favour_checked : R.drawable.ic_favour);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.textFavour.setCompoundDrawables(drawable, null, null, null);
            holder.textFavour.setText(String.valueOf(microSentence.getFavourNum()));
            holder.textFavour.setOnClickListener(v -> {
                RetrofitHelper.INSTANCE.post(getActivity(), MicroSentence.class, new RetrofitHelper.RetrofitListCallback<MicroSentence>() {
                    @Override
                    public Observable<JSONObject> getObservable(HttpService httpService) {
                        return httpService.microSentenceFavour(microSentence.getId());
                    }

                    @Override
                    public void onResult(String msg, List<MicroSentence> result) {
                        if (getArguments().getInt(ARG_SECTION_NUMBER) == 0) {
                            microSentences.remove(microSentence);
                        } else {
                            microSentences.get(position).setFavour(!microSentence.getFavour());
                            if (microSentence.getFavour()) {
                                microSentences.get(position).setFavourNum(microSentence.getFavourNum() + 1);
                            }
                        }
                        mMyAdapter.notifyDataSetChanged();
                    }
                });
            });
        }

        @Override
        public int getItemCount() {
            return microSentences == null ? 0 : microSentences.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textCategory;
            TextView textSource;
            TextView textContent;
            TextView textFavour;

            MyViewHolder(View itemView) {
                super(itemView);
                textCategory = itemView.findViewById(R.id.text_category);
                textSource = itemView.findViewById(R.id.text_source);
                textContent = itemView.findViewById(R.id.text_content);
                textFavour = itemView.findViewById(R.id.text_favour);
            }
        }
    }
}
