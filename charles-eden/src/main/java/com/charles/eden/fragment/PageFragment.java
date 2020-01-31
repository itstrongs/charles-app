package com.charles.eden.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.charles.eden.R;
import com.charles.eden.model.dto.MicroSentenceDto;
import com.charles.utils.ResUtils;
import com.charles.utils.base.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private ImageView mImgIllustration;
//    private ImageView mImgBg;

    private MicroSentenceDto mMicroSentenceDto;

    private List<String> mImgUrl = new ArrayList<>();

    public PageFragment() {
    }

    public PageFragment(MicroSentenceDto microSentenceDto) {
        this.mMicroSentenceDto = microSentenceDto;
        this.mImgUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577010287509&di=aad4cbc73a62d2a2a249e22eb17254ad&imgtype=0&src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-1c3bd9fe6c6a28c5ca3a678549dfde28_hd.jpg");
        this.mImgUrl.add("https://pic2.zhimg.com/80/v2-86cf45e7f34a38f5f4e20559f7d6fb35_hd.jpg");
        this.mImgUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577012344545&di=79095a81296c54a950cbe4c8db05a10d&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201506%2F04%2F20150604214559_BKmFJ.jpeg");
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
        mImgIllustration = mContentView.findViewById(R.id.img_illustration);
//        mImgBg = mContentView.findViewById(R.id.img_bg);
        Picasso.with(mContext).load(mImgUrl.get(new Random().nextInt(3))).into(mImgIllustration);
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
