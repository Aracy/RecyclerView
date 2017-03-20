package com.aracy.load.more;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;

import com.aracy.load.more.adapter.ItemAdapter;
import com.aracy.load.more.adapter.LoadMoreAdapter;
import com.aracy.load.more.bean.Item;
import com.aracy.load.more.widget.ItemDivider;
import com.aracy.load.more.widget.LoadMoreRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity implements LoadMoreAdapter.LoadMoreApi {

    private static final String TAG = "MainActivity";

    private LoadMoreRecyclerView recyclerView;

    private PtrClassicFrameLayout mPtrFrame;

    private List<Item> itemList = new ArrayList<>();

    private UIHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        super.onDestroy();
    }

    private void initView() {

        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.setLoadMoreApi(this);
        recyclerView.setShowAllLoadView(false);//默认false

        ItemDivider itemDivider = new ItemDivider(this, R.drawable.recycler_divider);
        itemDivider.setDividerPadding(0, 0);
        recyclerView.addItemDecoration(itemDivider);

        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.ptr_frame_layout);
        mPtrFrame.setLastUpdateTimeKey(getClass().getSimpleName());
        mPtrFrame.setPtrHandler(ptrHandler);

    }

    private void initData() {

        mHandler = new UIHandler(this);

        ItemAdapter mItemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(mItemAdapter);
        addTenItem();
        recyclerView.loadCompleted();

    }

    private void addTenItem() {
        int size = itemList.size();
        for (int i = size; i < 15 + size; i++) {
            itemList.add(new Item(i));
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private PtrHandler ptrHandler = new PtrHandler() {
        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
            return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            itemList.clear();
            addTenItem();
            recyclerView.initLoadCompleted();
            mPtrFrame.refreshComplete();
        }
    };


    @Override
    public void loadMore() {
        int what = (int) (Math.random() * 3);
        int delay = what == 0 ? 2000 : 0;
        Log.i(TAG, "loadMore: what " + what + " delay " + delay);
        mHandler.sendEmptyMessageDelayed(what, delay);
    }


    private static class UIHandler extends Handler {

        private WeakReference<MainActivity> mReference;

        private UIHandler(MainActivity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mReference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case 0:
                    activity.addTenItem();
                    activity.recyclerView.loadCompleted();
                    break;
                case 1:
                    activity.recyclerView.loadFailed();
                    break;
                case 2:
                    activity.addTenItem();
                    activity.recyclerView.loadAllDataCompleted();
            }
            super.handleMessage(msg);
        }
    }

}
