package com.charles.eden.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.model.bo.NotePlanBo;
import com.charles.eden.model.bo.PhotoStoryBo;
import com.charles.utils.ToastUtils;
import com.charles.utils.http.HttpResult;
import com.charles.utils.view.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2020-01-22 21:49
 */
public class PhotoActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyAdapter mMyAdapter;
    private List<PhotoStoryBo> photoStoryBos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                layoutManager.invalidateSpanAssignments();
//                if (!recycleView.canScrollVertically(1) && !mIsLoading) {
//                    Logger.d("底部上拉加载更多");
//                    mIsLoading = true;
//                    mPresenter.doLoadMoreData();
//                }
//            }
//        });
        mMyAdapter.setOnItemClickListener(this::showGirlDetails);
        RetrofitHelper.INSTANCE.post(mActivity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.photoStoryList();
            }

            @Override
            public void onResult(HttpResult result) {
                photoStoryBos = JSONArray.parseArray(result.getStringData(), PhotoStoryBo.class);
                if (photoStoryBos != null && photoStoryBos.size() > 0) {
                    mMyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void showGirlDetails(int position) {
//        ArrayList<String> imageUrls = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577010287509&di=aad4cbc73a62d2a2a249e22eb17254ad&imgtype=0&src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-1c3bd9fe6c6a28c5ca3a678549dfde28_hd.jpg");
//        }
        Intent intent = new Intent(mContext, PhotoDetailsActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("image_urls", JSON.toJSONString(photoStoryBos));
        startActivity(intent);
    }

    @OnClick({R.id.img_back, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.img_add:
                break;
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private RecyclerItemClickListener mOnItemClickListener;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(parent.getContext(), R.layout.item_list_girl, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            MyViewHolder girlHolder = holder;
            PhotoStoryBo photoStoryBo = photoStoryBos.get(position);
            Picasso.with(mContext).load(photoStoryBo.getPhotoUrl()).into(girlHolder.imageView);
            girlHolder.textImpression.setText(photoStoryBo.getImpression());
            if (mOnItemClickListener != null) {
                girlHolder.imageView.setOnClickListener(view -> mOnItemClickListener.onItemClickListener(position));
            }
        }

        @Override
        public int getItemCount() {
            return photoStoryBos == null ? 0 : photoStoryBos.size();
        }

        public void setOnItemClickListener(RecyclerItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textImpression;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_view);
                textImpression = itemView.findViewById(R.id.text_impression);
            }
        }
    }
}
