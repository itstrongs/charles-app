package com.charles.myapp.fragment.photo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.charles.library.base.BaseFragment;
import com.charles.myapp.R;
import com.charles.myapp.activity.DetailsActivity;
import com.charles.myapp.activity.MainActivity;
import com.charles.myapp.data.JsoupHelper;
import com.charles.myapp.data.bean.Gallery;
import com.charles.myapp.fragment.adapter.OnItemClickListener;
import com.charles.myapp.fragment.adapter.PhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by itstrongs on 2018/1/5.
 */
@SuppressLint("ValidFragment")
public class PictureFragment extends BaseFragment<MainActivity> {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private Gallery mGallery;
    private PhotoAdapter mGirlAdapter;
    private ArrayList<String> mURLList;

    @SuppressLint("ValidFragment")
    public PictureFragment (Gallery gallery) {
        mGallery = gallery;
    }

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_qi;
    }

    @Override
    protected void initView() {
        mURLList = new ArrayList<>();
        final StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(mGirlAdapter = new PhotoAdapter(mContext));
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        mGirlAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("position", position);
                intent.putStringArrayListExtra("image_urls", mURLList);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        JsoupHelper.getInstance().getDoubanImage(mGallery.getChildUrl(), new Consumer<List<Gallery.Picture>>() {
            @Override
            public void accept(List<Gallery.Picture> pictures) throws Exception {
                mGirlAdapter.setDataList(pictures);
                mGirlAdapter.notifyDataSetChanged();
                for (Gallery.Picture pic : pictures) {
                    mURLList.add(pic.getUrl());
                }
            }
        });
    }
}
