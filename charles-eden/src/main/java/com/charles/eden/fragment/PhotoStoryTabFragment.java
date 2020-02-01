package com.charles.eden.fragment;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.charles.eden.R;
import com.charles.utils.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

import static com.charles.eden.model.ConstantPool.LIST_TAB_TEXT;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2020-01-31 19:49
 */
public class PhotoStoryTabFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_photo_story_tab;
    }

    @Override
    protected void initView() {
        com.charles.eden.model.SectionsPagerAdapter sectionsPagerAdapter = new com.charles.eden.model.SectionsPagerAdapter(mContext,
                getFragmentManager(), LIST_TAB_TEXT, position -> PhotoStoryFragment.newInstance(position + 1));
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
