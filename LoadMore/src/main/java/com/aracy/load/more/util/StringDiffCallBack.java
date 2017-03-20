package com.aracy.load.more.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.aracy.load.more.bean.Item;

import java.util.List;

/**
 * @author Sun.bl
 * @version [1.0, 2016/8/29]
 */
public class StringDiffCallBack extends DiffUtil.Callback {

    private List<Item> mOldList;

    private List<Item> mNewList;

    public StringDiffCallBack(List<Item> oldList, List<Item> newList) {
        this.mOldList = oldList;
        this.mNewList = newList;
    }


    @Override
    public int getOldListSize() {
        return mOldList == null ? 0 : mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList == null ? 0 : mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).id == mNewList.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return TextUtils.equals(mOldList.get(oldItemPosition).content(), mNewList.get(newItemPosition).content());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Item oldItem = mOldList.get(oldItemPosition);
        Item newItem = mNewList.get(newItemPosition);

        Bundle diffBundle = new Bundle();

        if (!TextUtils.equals(oldItem.content(), newItem.content())) {
            diffBundle.putString("content", newItem.content());
        }

        return diffBundle;
    }
}
