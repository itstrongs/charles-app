package com.charles.credit.assist.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.activity.ChargesActivity;
import com.charles.credit.assist.activity.CreditCardActivity;
import com.charles.credit.assist.activity.EmergencyActivity;
import com.charles.credit.assist.http.HttpService;
import com.charles.credit.assist.http.RetrofitHelper;
import com.charles.credit.assist.model.bo.LoansProductBo;
import com.charles.library.base.BaseFragment;
import com.charles.library.http.HttpResult;
import com.charles.library.utils.IntentUtils;
import com.charles.library.utils.StringUtils;
import com.charles.library.utils.ToastUtils;
import com.squareup.picasso.Picasso;
import io.reactivex.Observable;

/**
 * Created by 刘奉强 on 2019/6/img_info_bg 19:00
 * <p>
 * Describe：
 */
public class WelfareFragment extends BaseFragment {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private PartyAdapter mPartyAdapter;
    private LoansProductBo mLoansProductBo;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_tab_welfare;
    }

    @Override
    protected void initView() {
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(mPartyAdapter = new PartyAdapter());
        RetrofitHelper.INSTANCE.post(mActivity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.loansProduct(0, null);
            }

            @Override
            public void onResult(HttpResult result) {
                mLoansProductBo = JSONObject.parseObject(result.getStringData(), LoansProductBo.class);
                mPartyAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.frame_credit, R.id.frame_charges, R.id.img_emergency})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frame_credit:
                startActivity(new Intent(mContext, CreditCardActivity.class));
                break;
            case R.id.frame_charges:
                startActivity(new Intent(mContext, ChargesActivity.class));
                break;
            case R.id.img_emergency:
                startActivity(new Intent(mContext, EmergencyActivity.class));
                break;
        }
    }

    private class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.PartyViewHolder> {

        @NonNull
        @Override
        public PartyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PartyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PartyViewHolder holder, final int position) {
            final LoansProductBo.ResultListBean resultList = mLoansProductBo.getResultList().get(position);
            if (StringUtils.isNotEmpty(resultList.getLogoUrl())) {
                Picasso.with(mContext).load(resultList.getLogoUrl()).into(holder.imgLogo);
            }
            holder.textName.setText(resultList.getName());
            holder.textRange.setText(resultList.getMinAmount() + "~" + resultList.getMaxAmount());
            holder.textRate.setText(resultList.getRate());
            holder.textTime.setText(resultList.getDuration());
            holder.textDeadline.setText(resultList.getDeadline());
            holder.textApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (StringUtils.isEmpty(resultList.getUrl())) {
                        ToastUtils.show(mContext, "未配置产品链接");
                    } else {
                        IntentUtils.openURLByBrowser(mContext, resultList.getUrl());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mLoansProductBo == null ? 0 : mLoansProductBo.getResultList().size();
        }

        class PartyViewHolder extends RecyclerView.ViewHolder {

            ImageView imgLogo;
            TextView textName;
            TextView textRange;
            TextView textRate;
            TextView textTime;
            TextView textDeadline;
            TextView textApply;

            PartyViewHolder(View itemView) {
                super(itemView);
                imgLogo = itemView.findViewById(R.id.img_logo);
                textName = itemView.findViewById(R.id.text_name);
                textRange = itemView.findViewById(R.id.text_range);
                textRate = itemView.findViewById(R.id.text_rate);
                textTime = itemView.findViewById(R.id.text_time);
                textDeadline = itemView.findViewById(R.id.text_deadline);
                textApply = itemView.findViewById(R.id.text_apply);
            }
        }
    }
}
