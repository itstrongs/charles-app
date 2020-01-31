package com.charles.eden.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.activity.PhotoDetailsActivity;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.model.bo.PhotoStoryBo;
import com.charles.utils.base.BaseFragment;
import com.charles.utils.http.HttpResult;
import com.charles.utils.view.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2020-01-31 19:49
 */
public class PhotoStoryFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyAdapter mMyAdapter;
    private List<PhotoStoryBo> photoStoryBos;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_photo_story;
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
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
        Intent intent = new Intent(mContext, PhotoDetailsActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("image_urls", JSON.toJSONString(photoStoryBos));
        startActivity(intent);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private RecyclerItemClickListener mOnItemClickListener;

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyAdapter.MyViewHolder(View.inflate(parent.getContext(), R.layout.item_list_girl, null));
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
            MyAdapter.MyViewHolder girlHolder = holder;
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
