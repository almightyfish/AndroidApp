package com.example.myapplication;

import java.util.List;

import com.baidu.mapapi.search.sug.SuggestionResult;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PoiItemAdapter extends RecyclerView.Adapter {
    private List<SuggestionResult.SuggestionInfo> mSuggestInfos = null;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    public PoiItemAdapter() {
    }

    public PoiItemAdapter(List<SuggestionResult.SuggestionInfo>  suggestInfoList) {
        mSuggestInfos = suggestInfoList;
    }

    /**
     * 获取POS 位置 item的suggestInfo
     *
     * @param pos
     * @return
     */
    public SuggestionResult.SuggestionInfo getItemSuggestInfo(int pos) {
        if (pos < 0
                || null == mSuggestInfos
                || pos >= mSuggestInfos.size()) {
            return null;
        }

        return mSuggestInfos.get(pos);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void updateData(List<SuggestionResult.SuggestionInfo>  suggestInfos) {
        if (null == suggestInfos) {
            return;
        }
        mSuggestInfos = suggestInfos;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public View mItemView;
        public View mLocateImg;
        public TextView mPoiNamePreFix;
        public TextView mPoiName;
        public TextView mPoiAddress;
        public SuggestionResult.SuggestionInfo mSuggestInfo = null;

        public MyViewHolder(View view) {
            super(view);
            mItemView = view;
            mLocateImg = view.findViewById(R.id.imgLocate);
            mPoiNamePreFix = view.findViewById(R.id.namePrefix);
            mPoiName = view.findViewById(R.id.poiResultName);
            mPoiAddress = view.findViewById(R.id.poiAddress);
        }

        public void setSuggestInfo(SuggestionResult.SuggestionInfo  suggestInfo) {
            mSuggestInfo = suggestInfo;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_city_rgc_item, parent, false);

        return new MyViewHolder(view);
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (null == mSuggestInfos) {
            return;
        }

        if (position < 0 || position >= mSuggestInfos.size()) {
            return;
        }

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if (null == myViewHolder) {
            return;
        }

        if (position < 0 || position >= mSuggestInfos.size()) {
            return;
        }

        final SuggestionResult.SuggestionInfo  suggestInfo = mSuggestInfos.get(position);
        if (null == suggestInfo) {
            return;
        }

        ((MyViewHolder) holder).mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == mOnItemClickListener) {
                    return;
                }
                int pos = holder.getAdapterPosition();
                long id = holder.getItemId();
                mOnItemClickListener.onItemClick(null, ((MyViewHolder) holder).mItemView, pos, id);
            }
        });

        myViewHolder.setSuggestInfo(suggestInfo);
        myViewHolder.mPoiName.setText(suggestInfo.getKey());

        String address = suggestInfo.getAddress();
        if (TextUtils.isEmpty(address)) {
            myViewHolder.mPoiAddress.setVisibility(View.GONE);
        } else {
            myViewHolder.mPoiAddress.setText(suggestInfo.getAddress());
            myViewHolder.mPoiAddress.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return null == mSuggestInfos ? 0 : mSuggestInfos.size();
    }
}
