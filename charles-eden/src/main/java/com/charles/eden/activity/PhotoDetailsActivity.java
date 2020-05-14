package com.charles.eden.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.charles.eden.R;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.model.bean.PhotoStoryBo;
import com.charles.utils.Logger;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        //拿到window
        Window window = getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏背景色
        window.setStatusBarColor(Color.BLACK);

        //设置状态栏字体颜色
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏字体颜色为白色
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        setContentView(R.layout.activity_photo_details);
        ButterKnife.bind(this);
        String imageUrls = getIntent().getStringExtra("image_urls");
        Logger.d("imageUrls=" + imageUrls);
        List<PhotoStoryBo> photoStoryBos = JSON.parseArray(imageUrls, PhotoStoryBo.class);
        ArrayList<View> imageViews = new ArrayList<>();
        for (PhotoStoryBo o : photoStoryBos) {
            View view = View.inflate(this, R.layout.item_photo_details, null);
            ImageView imageView = view.findViewById(R.id.img_details);
            Logger.d("o.getPhotoUrl()=" + o.getPhotoUrl());
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
