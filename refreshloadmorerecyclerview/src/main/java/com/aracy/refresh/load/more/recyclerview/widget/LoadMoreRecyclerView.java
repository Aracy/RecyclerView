package com.aracy.refresh.load.more.recyclerview.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.aracy.refresh.load.more.recyclerview.adapter.LoadMoreAdapter;

/**
 * @author Sun.bl
 * @version [1.0, 2016/7/5]
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private LoadMoreAdapter.LoadMoreApi moreApi;

    private boolean showAllLoadView;

    public LoadMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        LoadMoreAdapter moreAdapter = new LoadMoreAdapter(getContext(), adapter);
        moreAdapter.setShowAllLoadView(showAllLoadView);
        moreAdapter.setLoadMoreListener(moreApi);
        super.setAdapter(moreAdapter);
    }

    /***
     * 重置成初始化状态，主要是在下拉刷新中使用
     */
    public void initLoadCompleted() {
        Adapter adapter = getAdapter();

        if (adapter == null) {
            return;
        }

        LoadMoreAdapter moreAdapter = (LoadMoreAdapter) adapter;
        moreAdapter.resetLoadState();
    }

    /***
     * 加载完成
     */
    public void loadCompleted() {

        Adapter adapter = getAdapter();
        if (adapter == null) {
            return;
        }

        LoadMoreAdapter moreAdapter = (LoadMoreAdapter) adapter;
        moreAdapter.loadCompleted();

    }

    /***
     * 加载完成所有的数据
     */
    public void loadAllDataCompleted() {

        Adapter adapter = getAdapter();
        if (adapter == null) {
            return;
        }

        LoadMoreAdapter moreAdapter = (LoadMoreAdapter) adapter;
        moreAdapter.loadAllDataCompleted();


    }

    /**
     * 加载失败
     */
    public void loadFailed() {

        Adapter adapter = getAdapter();
        if (adapter == null) {
            return;
        }

        LoadMoreAdapter moreAdapter = (LoadMoreAdapter) adapter;
        moreAdapter.loadFailed();

    }

    /**
     * 设置加载更多的接口
     *
     * @param loadMoreApi
     */
    public void setLoadMoreApi(LoadMoreAdapter.LoadMoreApi loadMoreApi) {

        if (moreApi == loadMoreApi) {
            return;
        }
        this.moreApi = loadMoreApi;
        Adapter adapter = getAdapter();
        if (adapter == null || !(adapter instanceof LoadMoreAdapter)) {
            return;
        }
        LoadMoreAdapter moreAdapter = (LoadMoreAdapter) adapter;
        moreAdapter.setLoadMoreListener(loadMoreApi);

    }

    /***
     * 设置是否显示全部加载完成的view
     *
     * @param showAllLoadView
     */
    public void setShowAllLoadView(boolean showAllLoadView) {
        if (showAllLoadView == this.showAllLoadView) {
            return;
        }
        this.showAllLoadView = showAllLoadView;
        Adapter adapter = getAdapter();
        if (adapter == null || !(adapter instanceof LoadMoreAdapter)) {
            return;
        }
        LoadMoreAdapter moreAdapter = (LoadMoreAdapter) adapter;
        moreAdapter.setShowAllLoadView(showAllLoadView);
    }


}
