package com.hb.dialog.widget.autoloadListView;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by hanbin on 2018/6/1.
 */

public class SwipeAutoUtils {
    /**
     * 初始化刷新控件
     *
     * @param swipeLayout
     * @param autoListView
     * @param refreshListener
     * @param emptySwipeLayout
     */
    public static void initRefresh(SwipeRefreshLayout swipeLayout, AutoLoadListView autoListView,
                                   ZmRefreshListener refreshListener,
                                   SwipeRefreshLayout emptySwipeLayout) {
        onCreateSwipeToRefresh(swipeLayout, refreshListener);
        onCreateSwipeToRefresh(emptySwipeLayout, refreshListener);
        if (autoListView == null) {
            return;
        }
        autoListView.setOnLoadNextListener(refreshListener);
        if (emptySwipeLayout != null) {
            autoListView.setEmptyView(emptySwipeLayout);
        }
    }

    /**
     * 初始化刷新控件
     *
     * @param swipeLayout
     * @param autoListView
     * @param refreshListener
     */
    public static void initRefresh(SwipeRefreshLayout swipeLayout, AutoLoadListView autoListView,
                                   ZmRefreshListener refreshListener) {
        initRefresh(swipeLayout, autoListView, refreshListener, null);
    }

    /**
     * 设置下拉样式
     *
     * @param refreshLayout
     * @param refreshListener
     */
    private static void onCreateSwipeToRefresh(SwipeRefreshLayout refreshLayout,
                                               ZmRefreshListener refreshListener) {

        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light,
                android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(refreshListener);
    }

    public static void setSwipeRefreshStype(SwipeRefreshLayout refreshLayout) {
        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light,
                android.R.color.holo_red_light);
    }
}
