package com.aracy.refresh.load.more.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.aracy.refresh.load.more.recyclerview.R;

import java.util.List;

/**
 * @author Sun.bl
 * @version [1.0, 2016/6/24]
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;

    private List<String> itemList;

    public ItemAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.itemList = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View itemView = mInflater.inflate(R.layout.item_position, parent, false);
        viewHolder = new ItemViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.tvPosition.setText(itemList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPosition;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvPosition = (TextView) itemView.findViewById(R.id.tv_position);
        }
    }


}
