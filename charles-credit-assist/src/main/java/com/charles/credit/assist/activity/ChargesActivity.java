package com.charles.credit.assist.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.http.HttpService;
import com.charles.credit.assist.http.RetrofitHelper;
import com.charles.credit.assist.model.bo.LoansProductBo;
import com.charles.library.http.HttpResult;
import com.charles.library.utils.IntentUtils;
import com.charles.library.utils.StringUtils;
import com.charles.library.utils.ToastUtils;
import com.squareup.picasso.Picasso;
import io.reactivex.Observable;

/**
 * 大额分期
 *
 * @Author: liufengqiang
 * @Date: 2019/7/2
 */
public class ChargesActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyAdapter mMyAdapter;
    private LoansProductBo mLoansProductBo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charges);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        RetrofitHelper.INSTANCE.post(mActivity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.loansProduct(2, null);
            }

            @Override
            public void onResult(HttpResult result) {
                mLoansProductBo = JSONObject.parseObject(result.getStringData(), LoansProductBo.class);
                mMyAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        onBackPressed();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_charges, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
            final LoansProductBo.ResultListBean resultList = mLoansProductBo.getResultList().get(position);
            if (StringUtils.isNotEmpty(resultList.getLogoUrl())) {
                Picasso.with(mContext).load(resultList.getLogoUrl()).into(holder.imgLogo);
            }
            holder.textName.setText(resultList.getName());
            holder.textRange.setText(resultList.getMinAmount() + "~" + resultList.getMaxAmount());
            holder.textRate.setText(resultList.getRate());
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

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imgLogo;
            TextView textName;
            TextView textRange;
            TextView textRate;
            View textApply;

            MyViewHolder(View itemView) {
                super(itemView);
                imgLogo = itemView.findViewById(R.id.img_logo);
                textName = itemView.findViewById(R.id.text_name);
                textRange = itemView.findViewById(R.id.text_range);
                textRate = itemView.findViewById(R.id.text_rate);
                textApply = itemView.findViewById(R.id.layout_apply);
            }
        }
    }
}
