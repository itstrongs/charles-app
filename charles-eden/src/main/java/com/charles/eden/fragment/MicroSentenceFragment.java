package com.charles.eden.fragment;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.model.dto.MicroSentenceDto;
import com.charles.utils.base.BaseFragment;
import com.charles.utils.http.HttpResult;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import io.reactivex.Observable;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-22 14:23
 */
public class MicroSentenceFragment extends BaseFragment {

    @BindView(R.id.view_pager)
    VerticalViewPager viewPager;

    private List<MicroSentenceDto> microSentenceDtos;
    private List<PageFragment> mFragmentList;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_micro_sentence;
    }

    @Override
    protected void initView() {
        loadData();
//        loadLocalData();
    }

//    private void loadLocalData() {
//        WewordList wewordList = WewordHelper.getInstance().getWewordList(mContext);
//        mFragmentList = new ArrayList<>();
//        for (WewordList.WewordBean dataBean : wewordList.getWeword()) {
//            mFragmentList.add(new PageFragment(dataBean));
//        }
//        mViewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
//            @Override
//            public int getCount() {
//                return mFragmentList.size();
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//                return mFragmentList.get(position);
//            }
//        });
//    }

    private void loadData() {
        RetrofitHelper.INSTANCE.post(getActivity(), new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.microSentence();
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResult(HttpResult result) {
                microSentenceDtos = JSONArray.parseArray(result.getStringData(), MicroSentenceDto.class);
                mFragmentList = microSentenceDtos.stream().map(PageFragment::new).collect(Collectors.toList());
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
//        OkHttpHelper.getInstance().get(getActivity(), ConstantHolder.URL_GET_ALL_EMOTION, new OkHttpHelper.HttpCallback() {
//            @Override
//            public void onSuccess(String result) {
//                Weword emotion = new Gson().fromJson(result, Weword.class);
//                List<Weword.EmotionBean> emotion1 = emotion.getEmotion();
//                mFragmentList = new ArrayList<>();
//                for (Weword.EmotionBean dataBean : emotion1) {
//                    mFragmentList.add(new PageFragment(dataBean));
//                }
//                mViewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
//                    @Override
//                    public int getCount() {
//                        return mFragmentList.size();
//                    }
//
//                    @Override
//                    public Fragment getItem(int position) {
//                        return mFragmentList.get(position);
//                    }
//                });
//            }
//        });
    }
}
