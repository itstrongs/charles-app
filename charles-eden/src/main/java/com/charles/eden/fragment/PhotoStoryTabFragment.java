package com.charles.eden.fragment;

import androidx.viewpager.widget.ViewPager;

import com.charles.eden.R;
import com.charles.eden.model.SectionsPagerAdapter;
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

    @BindView(R.id.tab_layout2)
    TabLayout tabLayout;
    @BindView(R.id.view_pager2)
    ViewPager viewPager;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_photo_story_tab;
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(new SectionsPagerAdapter(mContext, getFragmentManager(), LIST_TAB_TEXT, position -> PhotoStoryFragment.newInstance(position + 1)));
        tabLayout.setupWithViewPager(viewPager);
    }
}
