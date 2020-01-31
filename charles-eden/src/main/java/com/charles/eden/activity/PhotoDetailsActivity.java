package com.charles.eden.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.model.bo.PhotoStoryBo;
import com.charles.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2020-01-22 22:00
 */
public class PhotoDetailsActivity extends BaseActivity {


    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        ButterKnife.bind(this);
        String imageUrls = getIntent().getStringExtra("image_urls");
        List<PhotoStoryBo> photoStoryBos = JSON.parseArray(imageUrls, PhotoStoryBo.class);
        ArrayList<View> imageViews = new ArrayList<>();
        for (PhotoStoryBo o : photoStoryBos) {
            View view = View.inflate(this, R.layout.item_photo_details, null);
            ImageView imageView = view.findViewById(R.id.image_view);
            Picasso.with(this).load(o.getPhotoUrl()).into(imageView);
            TextView textImpression = view.findViewById(R.id.text_impression);
            textImpression.setText(o.getImpression());
            imageViews.add(view);
        }
        viewPager.setAdapter(new DetailsAdapter(imageViews));
        viewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
    }

    class DetailsAdapter extends PagerAdapter {

        private ArrayList<View> mGirls;

        public DetailsAdapter(ArrayList<View> girls) {
            mGirls = girls;
        }

        @Override
        public int getCount() {
            return mGirls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mGirls.get(position));
            return mGirls.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
