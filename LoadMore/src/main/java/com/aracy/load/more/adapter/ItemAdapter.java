package com.aracy.load.more.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.aracy.load.more.R;
import com.aracy.load.more.bean.Item;

import java.util.List;

/**
 * @author Sun.bl
 * @version [1.0, 2016/6/24]
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ItemAdapter";

    private LayoutInflater mInflater;


    private List<Item> itemList;

    private int mLastPosition;

    public ItemAdapter(Context context, List<Item> data) {
        this.mInflater = LayoutInflater.from(context);
        this.itemList = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_position, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.tvPosition.setText(itemList.get(position).content());
//            ((ItemViewHolder) holder).setAnimation(position);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvPosition;

        private ItemViewHolder(View itemView) {
            super(itemView);
            tvPosition = (TextView) itemView.findViewById(R.id.tv_position);
        }

        void setAnimation(int position) {

            if (position > mLastPosition) {
                ObjectAnimator oa = ObjectAnimator.ofFloat(itemView, "translationY", itemView.getHeight() * 0.5f, 0);
                oa.setDuration(1500);
                oa.setInterpolator(new AccelerateDecelerateInterpolator());
                oa.start();
            }

            mLastPosition = position;
        }

    }


}
