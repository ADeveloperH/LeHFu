package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.adapter.TeamItemAdapter;
import com.jiefutong.lehfu.base.BaseTitleActivity;
import com.jiefutong.lehfu.bean.TeamListResultBean;
import com.jiefutong.lehfu.http.Http;
import com.jiefutong.lehfu.http.MyTextAsyncResponseHandler;
import com.jiefutong.lehfu.http.RequestParams;
import com.jiefutong.lehfu.utils.JsonUtil;
import com.jiefutong.lehfu.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：hj
 * time: 2018/1/17 0017 21:35
 * description:
 */


public class MyTeamActivity extends BaseTitleActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ll_group)
    LinearLayout llGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myteam);
        ButterKnife.bind(this);
        setTitle("我的团队");

        getTeamList();
    }

    private void getTeamList() {
        RequestParams requestParams = new RequestParams();
        Http.post(Http.GET_TEAM_LIST, requestParams,
                new MyTextAsyncResponseHandler(act, "请求中...") {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        try {
                            TeamListResultBean resultBean = JsonUtil.fromJson(content,
                                    TeamListResultBean.class);
                            if (resultBean.getStatus() == 1 && resultBean.getInfo() != null
                                    && resultBean.getInfo().getList() != null
                                    && resultBean.getInfo().getList().size() > 0) {
                                showTeamList(resultBean.getInfo().getList());
                            } else {
                                llGroup.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtils.showCenterShortToast("请求失败");
                        }
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        ToastUtils.showCenterShortToast("请求失败");
                    }
                });
    }

    private void showTeamList(List<TeamListResultBean.InfoEntity.ListEntity> list) {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(virtualLayoutManager);

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        recyclerview.setAdapter(delegateAdapter);

        LinearLayoutHelper layoutHelper = new LinearLayoutHelper();
        TeamItemAdapter adapter = new TeamItemAdapter(this, list, layoutHelper);
        delegateAdapter.addAdapter(adapter);
    }
}
