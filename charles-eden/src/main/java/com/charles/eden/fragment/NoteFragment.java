package com.charles.eden.fragment;

import android.content.Intent;
import android.graphics.Typeface;
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

import com.alibaba.fastjson.JSONObject;
import com.charles.eden.R;
import com.charles.eden.activity.NoteListActivity;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.model.bean.NoteTypeBo;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;
import com.charles.utils.base.BaseFragment;
import com.charles.utils.view.RecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by 刘奉强 on 2018/12/16 14:00
 * <p>
 * Describe：
 */
public class NoteFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerViewParty;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NoteAdapter mPartyAdapter;
    private List<NoteTypeBo> noteBos;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_note;
    }

    @Override
    protected void initView() {
        recyclerViewParty.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewParty.setAdapter(mPartyAdapter = new NoteAdapter());
        mPartyAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(getContext(), NoteListActivity.class);
            intent.putExtra("record_plan_type", noteBos.get(position));
            startActivity(intent);
        });
        swipeRefreshLayout.setOnRefreshListener(this::initData);
    }

    @Override
    protected void initData() {
        RetrofitHelper.INSTANCE.post(mActivity, NoteTypeBo.class, new RetrofitHelper.RetrofitListCallback<NoteTypeBo>() {
            @Override
            public Observable<JSONObject> getObservable(HttpService httpService) {
                return httpService.listModuleType(0);
            }

            @Override
            public void onResult(String msg, List<NoteTypeBo> result) {
                noteBos = result;
                swipeRefreshLayout.setRefreshing(false);
                mPartyAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.img_add)
    public void onViewClicked() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_add_type, null, false);
        AlertDialog.Builder editDialog = new AlertDialog.Builder(mContext);
        editDialog.setTitle("添加分类");
        editDialog.setIcon(R.mipmap.ic_launcher);
        editDialog.setView(view);
        editDialog.setPositiveButton("添加", (dialog, which) -> {
            EditText editName = view.findViewById(R.id.edit_name);
            EditText editDesc = view.findViewById(R.id.edit_desc);
            final String typeName = editName.getText().toString().trim();
            final String typeDesc = editDesc.getText().toString().trim();
            if (StringUtils.isEmpty(typeName)) {
                ToastUtils.show(mContext, "类型名不能为空");
                return;
            }
            RetrofitHelper.INSTANCE.post(mActivity, NoteTypeBo.class, new RetrofitHelper.RetrofitCallback<NoteTypeBo>() {
                @Override
                public Observable<JSONObject> getObservable(HttpService httpService) {
                    NoteTypeBo noteTypeBo = new NoteTypeBo();
                    noteTypeBo.setName(typeName);
                    noteTypeBo.setDescription(typeDesc);
                    noteTypeBo.setModuleType(0);
                    return httpService.addModuleType(noteTypeBo);
                }

                @Override
                public void onResult(String msg, NoteTypeBo noteTypeBo) {
                    ToastUtils.show(mContext, msg);
                    initData();
                }
            });
            dialog.dismiss();
        });
        editDialog.create().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ThemeViewHolder> {

        private RecyclerItemClickListener mOnItemClickListener;

        @NonNull
        @Override
        public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ThemeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_note, parent, false));
        }

        public void setOnItemClickListener(RecyclerItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        @Override
        public void onBindViewHolder(@NonNull ThemeViewHolder holder, final int position) {
//            NotePlanTypeBo notePlanTypeBo = noteBos.get(position);
//            holder.textName.setText(notePlanTypeBo.getName());
//            if (notePlanTypeBo.getIsSetTop()) {
//                holder.textName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                holder.imgSetTop.setVisibility(View.VISIBLE);
//            }
//            holder.textDesc.setText(notePlanTypeBo.getDescription());
//            holder.layoutHead.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mOnItemClickListener != null) {
//                        mOnItemClickListener.onItemClickListener(position);
//                    }
//                }
//            });

            NoteTypeBo noteBo = noteBos.get(position);
            holder.textName.setText(noteBo.getName());
//            if (noteBo.get()) {
            holder.textName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            holder.imgSetTop.setVisibility(View.VISIBLE);
//            }
            holder.textDesc.setText(noteBo.getNickname());
            holder.layoutHead.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClickListener(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return noteBos == null ? 0 : noteBos.size();
        }

        private void showPopWindow() {
//            if (popupBigPhoto == null) {
//                View view = getLayoutInflater().inflate(R.layout.pop_record_plan_type_operation, null);
//                popupBigPhoto = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
//                popupBigPhoto.setOutsideTouchable(true);
////                popupBigPhoto.setOnDismissListener(this);
//            }
//            if (popupBigPhoto.isShowing()) {
//                popupBigPhoto.dismiss();
//            } else {
//                popupBigPhoto.showAtLocation(headview, Gravity.TOP, 0, 0);
//            }
        }

        class ThemeViewHolder extends RecyclerView.ViewHolder {

            View layoutHead;
            TextView textName;
            TextView textDesc;
            ImageView imgSetTop;
            ImageView imgMore;

            ThemeViewHolder(View itemView) {
                super(itemView);
                layoutHead = itemView.findViewById(R.id.layout_head);
                textName = itemView.findViewById(R.id.text_name);
                textDesc = itemView.findViewById(R.id.text_date);
                imgSetTop = itemView.findViewById(R.id.img_set_top);
                imgMore = itemView.findViewById(R.id.img_more);
            }
        }
    }
}
