package com.charles.eden.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.charles.eden.R;
import com.charles.eden.model.dto.MicroSentenceDto;
import com.charles.utils.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Charles on 2018/5/21.
 */

public class PageFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.img_bg)
    ImageView imgBg;
    private TextView mTextCategory;
    private TextView mTextSource;
    private TextView mTextContent;
    private TextView mTextFeel;
//    private ImageView mImgBg;

    private MicroSentenceDto mMicroSentenceDto;

    private List<String> mImgUrl = new ArrayList<>();

    public PageFragment() {
    }

    public PageFragment(MicroSentenceDto microSentenceDto) {
        this.mMicroSentenceDto = microSentenceDto;
        this.mImgUrl.add("http://pwkij4wg0.bkt.clouddn.com/WechatIMG12.jpeg");
        this.mImgUrl.add("http://pwkij4wg0.bkt.clouddn.com/WechatIMG10.jpeg");
        this.mImgUrl.add("http://pwkij4wg0.bkt.clouddn.com/WechatIMG11.jpeg");
        this.mImgUrl.add("http://pva7sac9n.bkt.clouddn.com/FqX43SWwu3R8tT7xs_sz--G0fw1F");
    }

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_page;
    }

    @Override
    protected void initView() {
        mTextCategory = mContentView.findViewById(R.id.text_category);
        mTextSource = mContentView.findViewById(R.id.text_source);
        mTextContent = mContentView.findViewById(R.id.text_content);
        mTextFeel = mContentView.findViewById(R.id.text_feel);
//        mImgBg = mContentView.findViewById(R.id.img_bg);
//        Picasso.with(mContext).load(mImgUrl.get(new Random().nextInt(4))).into(imgBg);
        mTextCategory.setText("#" + mMicroSentenceDto.getCategory() + "#");
        mTextSource.setText("+" + mMicroSentenceDto.getSource());
        mTextContent.setText(mMicroSentenceDto.getContent());
        mTextFeel.setText(mMicroSentenceDto.getFeel());
//        if (!mDataBean.getImage().isEmpty()) {
//            mImgBg.setVisibility(View.VISIBLE);
////            mImgBg.setImageResource(ResUtils.getResById(mContext, mDataBean.getImage(), "drawable"));
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
