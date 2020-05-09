package com.charles.eden.fragment;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelperBak;
import com.charles.eden.model.SectionsPagerAdapter;
import com.charles.eden.model.dto.MicroSentenceDto;
import com.charles.utils.base.BaseFragment;
import com.charles.utils.http.HttpResult;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

import static com.charles.eden.model.ConstantPool.LIST_TAB_TEXT;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-22 14:23
 */
public class MicroSentenceFragment extends BaseFragment {

//    @BindView(R.id.view_pager)
//    VerticalViewPager viewPager;
    @BindView(R.id.view_pager_micro_sentence)
    ViewPager viewPager;
    @BindView(R.id.tab_layout_micro_sentence)
    TabLayout tabLayout;

    private List<MicroSentenceDto> microSentenceDtos;
    private List<PageFragment> mFragmentList;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_micro_sentence;
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(new SectionsPagerAdapter(mContext, getFragmentManager(), LIST_TAB_TEXT, MicroSentenceTabFragment::newInstance));
        tabLayout.setupWithViewPager(viewPager);
//        mFragmentList = new ArrayList<>();
//        loadData();
    }

    private void loadData() {
        RetrofitHelperBak.INSTANCE.post(getActivity(), new RetrofitHelperBak.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.microSentence();
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResult(HttpResult result) {
                microSentenceDtos = JSONArray.parseArray(result.getStringData(), MicroSentenceDto.class);
                for (MicroSentenceDto dto : microSentenceDtos) {
                    mFragmentList.add(new PageFragment(dto));
                }
                if (microSentenceDtos != null && microSentenceDtos.size() > 0) {
                    viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
                    @Override
                    public int getCount() {
                        return mFragmentList.size();
                    }

                    @Override
                    public Fragment getItem(int position) {
                        return mFragmentList.get(position);
                    }
                });
                }
            }
        });
    }
}
