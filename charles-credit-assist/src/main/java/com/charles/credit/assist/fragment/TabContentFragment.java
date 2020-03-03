package com.charles.credit.assist.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.activity.InviteActivity;
import com.charles.credit.assist.http.HttpService;
import com.charles.credit.assist.http.RetrofitHelper;
import com.charles.credit.assist.model.bo.CouponListBo;

import butterknife.BindView;
import butterknife.OnClick;
import com.charles.library.base.BaseFragment;
import com.charles.library.http.HttpResult;
import io.reactivex.Observable;

/**
 * @Details:
 * @Author: liufengqiang
 * @Date: 2019/7/4
 */
public class TabContentFragment extends BaseFragment {

    @BindView(R.id.layout_no_coupon)
    LinearLayout layoutNoCoupon;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyAdapter mMyAdapter;
    private CouponListBo couponListBo;
    private Integer mCouponType;

    public static Fragment newInstance(String tab) {
        TabContentFragment fragment = new TabContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab", tab);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        Bundle arguments = getArguments();
        switch (arguments.getString("tab")) {
            case "未使用":
                mCouponType = 0;
                break;
            case "已使用":
                mCouponType = 1;
                break;
            case "已过期":
                mCouponType = 2;
                break;
        }
    }

    @Override
    protected void initData() {
        RetrofitHelper.INSTANCE.post(getActivity(), new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.couponList(mCouponType);
            }

            @Override
            public void onResult(HttpResult result) {
                couponListBo = JSONObject.parseObject(result.getStringData(), CouponListBo.class);
                if (couponListBo.getResultList() != null && couponListBo.getResultList().size() != 0) {
                    layoutNoCoupon.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    layoutNoCoupon.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                mMyAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.btn_share)
    public void onViewClicked() {
        startActivity(new Intent(mContext, InviteActivity.class));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_coupon, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            if (couponListBo != null) {
                CouponListBo.ResultListBean resultListBean = couponListBo.getResultList().get(position);
                holder.couponMoney.setText("￥" + resultListBean.getMoney());
                holder.couponName.setText(resultListBean.getName());
                holder.couponValidity.setText("有效期：" + resultListBean.getInvalidTime());
                holder.couponUse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().setResult(918);
                        getActivity().finish();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return couponListBo == null ? 0 : couponListBo.getResultList().size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView couponMoney;
            TextView couponName;
            TextView couponValidity;
            TextView couponUse;

            MyViewHolder(View itemView) {
                super(itemView);
                couponMoney = itemView.findViewById(R.id.text_money);
                couponName = itemView.findViewById(R.id.text_name);
                couponValidity = itemView.findViewById(R.id.text_validity);
                couponUse = itemView.findViewById(R.id.text_use);
            }
        }
    }
}
