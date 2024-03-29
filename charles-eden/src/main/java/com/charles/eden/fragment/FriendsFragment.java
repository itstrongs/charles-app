package com.charles.eden.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONArray;
import com.charles.eden.R;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelperBak;
import com.charles.eden.model.ConstantPool;
import com.charles.eden.model.bean.Friends;
import com.charles.eden.model.bean.PersonFriendsBo;
import com.charles.utils.Logger;
import com.charles.utils.SPHelper;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;
import com.charles.utils.base.BaseFragment;
import com.charles.utils.http.HttpResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-22 15:05
 */
public class FriendsFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<Friends> friendsDtos;
    private MyAdapter mMyAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_friends;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        swipeRefreshLayout.setOnRefreshListener(this::initData);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initData() {
        RetrofitHelperBak.INSTANCE.post(getActivity(), new RetrofitHelperBak.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.friendsList();
            }

            @Override
            public void onResult(HttpResult result) {
                swipeRefreshLayout.setRefreshing(false);
                friendsDtos = JSONArray.parseArray(result.getStringData(), Friends.class);
                if (friendsDtos != null && friendsDtos.size() > 0) {
                    mMyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @OnClick(R.id.img_add)
    public void onViewClicked() {
        showDialog();
    }

    private void showDialog() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_add_type, null, false);
        AlertDialog.Builder editDialog = new AlertDialog.Builder(mContext);
        editDialog.setTitle("发朋友圈");
        editDialog.setIcon(R.mipmap.ic_launcher);
        editDialog.setView(view);
        editDialog.setPositiveButton("添加", (dialog, which) -> {
            EditText editName = view.findViewById(R.id.edit_name);
            final String typeName = editName.getText().toString().trim();
            if (StringUtils.isEmpty(typeName)) {
                ToastUtils.show(mContext, "类型名不能为空");
                return;
            }
            RetrofitHelperBak.INSTANCE.post(mActivity, new RetrofitHelperBak.RetrofitCallback() {
                @Override
                public Observable<HttpResult> getObservable(HttpService httpService) {
                    PersonFriendsBo personFriendsBo = new PersonFriendsBo();
                    personFriendsBo.setContent(typeName);
                    personFriendsBo.setPermission(0);
                    return httpService.personFriends(personFriendsBo);
                }

                @Override
                public void onResult(HttpResult result) {
                    ToastUtils.show(mContext, result.getMsg());
                    initData();
                }
            });
            dialog.dismiss();
        });
        editDialog.create().show();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_friends, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            Friends friends = friendsDtos.get(position);
            Picasso.with(mContext).load(friends.getPortrait()).into(holder.imgPortrait);
            holder.textNickname.setText(friends.getNickname());
            holder.textContent.setText(friends.getContent());
            holder.textDate.setText(friends.getCreatedAt());
            if (friends.getUserId().equals(SPHelper.getLong(mContext, ConstantPool.SP_USER_ID))) {
                holder.imgDelete.setVisibility(View.VISIBLE);
                holder.imgDelete.setOnClickListener(v -> new AlertDialog.Builder(mContext)
                        .setTitle("确定删除吗")
                        .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                        .setPositiveButton("确定", (dialog, which) -> {
                            RetrofitHelperBak.INSTANCE.post(mActivity, new RetrofitHelperBak.RetrofitCallback() {
                                @Override
                                public Observable<HttpResult> getObservable(HttpService httpService) {
                                    Logger.d("friendsDto.getId(): " + friends.getId());
                                    return httpService.personFriends(friends.getId());
                                }

                                @Override
                                public void onResult(HttpResult result) {
                                    initData();
                                }
                            });
                            dialog.dismiss();
                        })
                        .create()
                        .show());
            }
            if (StringUtils.isNotEmpty(friends.getImage())) {
                holder.imgBg.setVisibility(View.VISIBLE);
                Picasso.with(mContext).load(friends.getImage()).into(holder.imgBg);
            }
        }

        @Override
        public int getItemCount() {
            return friendsDtos == null ? 0 : friendsDtos.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imgPortrait;
            TextView textNickname;
            TextView textContent;
            TextView textDate;
            ImageView imgDelete;
            ImageView imgBg;

            MyViewHolder(View itemView) {
                super(itemView);
                imgPortrait = itemView.findViewById(R.id.img_portrait);
                textNickname = itemView.findViewById(R.id.text_nickname);
                textContent = itemView.findViewById(R.id.text_content);
                textDate = itemView.findViewById(R.id.text_date);
                imgDelete = itemView.findViewById(R.id.img_delete);
                imgBg = itemView.findViewById(R.id.img_bg);
            }
        }
    }
}
