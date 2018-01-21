package com.jiefutong.lehfu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.google.gson.reflect.TypeToken;
import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.adapter.HomeGridAdapter;
import com.jiefutong.lehfu.adapter.HomeTitleAdapter;
import com.jiefutong.lehfu.adapter.HomeTopAdapter;
import com.jiefutong.lehfu.adapter.HomeTouTiaoAdapter;
import com.jiefutong.lehfu.base.BaseFragment;
import com.jiefutong.lehfu.bean.HomeTouTiaoResultBean;
import com.jiefutong.lehfu.http.Http;
import com.jiefutong.lehfu.http.MyTextAsyncResponseHandler;
import com.jiefutong.lehfu.http.RequestParams;
import com.jiefutong.lehfu.utils.JsonUtil;
import com.jiefutong.lehfu.utils.ToastUtils;
import com.jiefutong.lehfu.utils.UIUtils;
import com.jiefutong.lehfu.widget.MyRecyclerView;
import com.jiefutong.lehfu.widget.listener.RecyclerViewScrollListener;
import com.jiefutong.lehfu.widget.listener.SwipeRefreshListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：hj
 * time: 2018/1/14 0014 17:53
 * description:
 */


public class HomeFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    MyRecyclerView mRecyclerview;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private View rootView;
    private List<HomeTouTiaoResultBean> touTiaoList = new ArrayList<>();
    private int curPage;
    private int pageCount = 10;
    private HomeTouTiaoAdapter touTiaoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
//        if (rootView != null) {
//            ViewGroup parent = ((ViewGroup) rootView.getParent());
//            if (parent != null) {
//                parent.removeView(rootView);
//            }
//            return rootView;
//        }
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRecyclerView();

        getTouTiaoData();
        setListener();

    }

    private void setListener() {
        mRecyclerview.setRefreshLayout(swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshListener(mRecyclerview) {
            @Override
            protected void refresh() {
                curPage = 0;
                getTouTiaoData();
            }
        });

        mRecyclerview.addOnScrollListener(new RecyclerViewScrollListener(mRecyclerview) {

            @Override
            protected void loadMore() {
                mRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        curPage++;
                        getTouTiaoData();
                    }
                }, 2000);
            }
        });
    }


    /**
     * 获取金融头条数据
     */
    private void getTouTiaoData() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("page", curPage + "");
        requestParams.put("size", pageCount + "");
        Http.post(Http.GET_TOUTIAO, requestParams,
                new MyTextAsyncResponseHandler(mActivity, null) {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        content = "[\n" +
                                "  {\n" +
                                "    \"id\": \"28\", \n" +
                                "    \"title\": \"如何才能真正的提高自己，成为一名出色的架构师？\", \n" +
                                "    \"create_time\": \"2018-01-20 11:43:23\", \n" +
                                "    \"cishu\": \"222\", \n" +
                                "    \"pic\": \"/Uploads/2018-01-20/1516419810.png\", \n" +
                                "    \"url\": \"\"\n" +
                                "  }, \n" +
                                "  {\n" +
                                "    \"id\": \"27\", \n" +
                                "    \"title\": \"范德萨范德萨\", \n" +
                                "    \"create_time\": \"2018-01-20 11:42:50\", \n" +
                                "    \"cishu\": \"22\", \n" +
                                "    \"pic\": \"/Uploads/2018-01-20/1516419783.png\", \n" +
                                "    \"url\": \"http://www.baidu.com\"\n" +
                                "  }\n" +
                                "]";
                        try {
                            List<HomeTouTiaoResultBean> resultList = JsonUtil.fromJson(content,
                                    new TypeToken<List<HomeTouTiaoResultBean>>() {
                                    }.getType());
                            if (resultList != null && resultList.size() > 0) {
                                if (curPage == 0) {
                                    touTiaoList.clear();
                                }
                                touTiaoList.addAll(resultList);
                                touTiaoAdapter.setDataList(touTiaoList);
                                touTiaoAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            getDataFinish();
                        }
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        getDataFinish();
                        ToastUtils.showCenterLongToast("获取数据失败");
                    }
                });

    }

    private void initRecyclerView() {
        mRecyclerview.setItemAnimator(null);
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mActivity);
        mRecyclerview.setLayoutManager(layoutManager);
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, false);
        mRecyclerview.setAdapter(delegateAdapter);

        adapters.add(new HomeTopAdapter(mActivity, new LinearLayoutHelper(), 1));

        LinearLayoutHelper titleLayoutHelper = new LinearLayoutHelper();
        titleLayoutHelper.setMargin(0, 0, 0, UIUtils.dip2px(1));
        adapters.add(new HomeTitleAdapter(mActivity, "立即办卡", titleLayoutHelper, 1));

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2, 4);
        gridLayoutHelper.setBgColor(0xfff0f0f0);
        gridLayoutHelper.setVGap(UIUtils.dip2px(1));
        gridLayoutHelper.setHGap(UIUtils.dip2px(1));
        adapters.add(new HomeGridAdapter(mActivity, gridLayoutHelper));

        LinearLayoutHelper titleLayoutHelper2 = new LinearLayoutHelper();
        titleLayoutHelper2.setMargin(0, UIUtils.dip2px(12), 0, UIUtils.dip2px(1));
        adapters.add(new HomeTitleAdapter(mActivity, "金融头条", titleLayoutHelper2, 1));


        LinearLayoutHelper touTiaoLayoutHelper = new LinearLayoutHelper();
        touTiaoLayoutHelper.setBgColor(0xfff0f0f0);
        touTiaoLayoutHelper.setDividerHeight(UIUtils.dip2px(1));
        touTiaoAdapter = new HomeTouTiaoAdapter(mActivity, touTiaoList, touTiaoLayoutHelper);
        adapters.add(touTiaoAdapter);

        delegateAdapter.setAdapters(adapters);

    }

    private void getDataFinish() {
        if (mRecyclerview != null) {
            mRecyclerview.setOnLoadMore(false);
            mRecyclerview.setOnRefresh(false);
        }
    }
}
