package com.charles.credit.assist.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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
 * 信用卡页面
 *
 * @Author: liufengqiang
 * @Date: 2019/7/3
 */
public class CreditCardActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_tab_0)
    TextView textTab0;
    @BindView(R.id.text_tab_1)
    TextView textTab1;
    @BindView(R.id.text_tab_2)
    TextView textTab2;

    private MyAdapter mMyAdapter;
    private LoansProductBo mLoansProductBo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        ButterKnife.bind(this);
        initStatusBar();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        getLoansProduct(0);
    }

    private void getLoansProduct(final Integer tab) {
        RetrofitHelper.INSTANCE.post(mActivity, new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.loansProduct(1, tab);
            }

            @Override
            public void onResult(HttpResult result) {
                mLoansProductBo = JSONObject.parseObject(result.getStringData(), LoansProductBo.class);
                mMyAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.img_back, R.id.text_tab_0, R.id.text_tab_1, R.id.text_tab_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.text_tab_0:
                textTab0.setBackgroundResource(R.drawable.shape_bg_cyan_25);
                textTab1.setBackgroundResource(0);
                textTab2.setBackgroundResource(0);
                getLoansProduct(0);
                break;
            case R.id.text_tab_1:
                textTab0.setBackgroundResource(0);
                textTab1.setBackgroundResource(R.drawable.shape_bg_cyan_25);
                textTab2.setBackgroundResource(0);
                getLoansProduct(1);
                break;
            case R.id.text_tab_2:
                textTab0.setBackgroundResource(0);
                textTab1.setBackgroundResource(0);
                textTab2.setBackgroundResource(R.drawable.shape_bg_cyan_25);
                getLoansProduct(2);
                break;
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_credit_card, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            final LoansProductBo.ResultListBean resultList = mLoansProductBo.getResultList().get(position);
            if (StringUtils.isNotEmpty(resultList.getLogoUrl())) {
                Picasso.with(mContext).load(resultList.getLogoUrl()).into(holder.imgLogo);
            }
            holder.textName.setText(resultList.getName());
            holder.textRange.setText(resultList.getMinAmount() + "~" + resultList.getMaxAmount());
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
            View textApply;

            MyViewHolder(View itemView) {
                super(itemView);
                imgLogo = itemView.findViewById(R.id.img_logo);
                textName = itemView.findViewById(R.id.text_name);
                textRange = itemView.findViewById(R.id.text_range);
                textApply = itemView.findViewById(R.id.layout_apply);
            }
        }
    }
}
