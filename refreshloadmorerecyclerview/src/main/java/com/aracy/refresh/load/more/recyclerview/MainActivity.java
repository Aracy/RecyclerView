package com.aracy.refresh.load.more.recyclerview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;

import com.aracy.refresh.load.more.recyclerview.adapter.ItemAdapter;
import com.aracy.refresh.load.more.recyclerview.adapter.LoadMoreAdapter;
import com.aracy.refresh.load.more.recyclerview.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity implements LoadMoreAdapter.LoadMoreApi {

    private LoadMoreRecyclerView recyclerView;

    private PtrClassicFrameLayout mPtrFrame;

    private List<String> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {

        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.setLoadMoreApi(this);
        recyclerView.setShowAllLoadView(false);//默认false

        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.ptr_frame_layout);
        mPtrFrame.setLastUpdateTimeKey(getClass().getSimpleName());
        mPtrFrame.setPtrHandler(ptrHandler);

    }

    private void initData() {

        ItemAdapter itemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(itemAdapter);
        addTenItem();
        recyclerView.loadCompleted();

    }

    private void addTenItem() {
        int size = itemList.size();
        for (int i = size; i < 15 + size; i++) {
            itemList.add("第 " + (i + 1) + " 项Item");
        }
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
        handler.sendEmptyMessageDelayed((int) (Math.random() * 3), 1500);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    addTenItem();
                    recyclerView.loadCompleted();
                    break;
                case 1:
                    recyclerView.loadFailed();
                    break;
                case 2:
                    addTenItem();
                    recyclerView.loadAllDataCompleted();
            }
            super.handleMessage(msg);
        }
    };

}
