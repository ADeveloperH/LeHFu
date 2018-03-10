package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.adapter.CardPackagePagerAdapter;
import com.jiefutong.lehfu.base.BaseTitleActivity;
import com.jiefutong.lehfu.bean.CardListResultBean;
import com.jiefutong.lehfu.fragment.ChuXuCardFragment;
import com.jiefutong.lehfu.fragment.CreditCardFragment;
import com.jiefutong.lehfu.http.Http;
import com.jiefutong.lehfu.http.MyTextAsyncResponseHandler;
import com.jiefutong.lehfu.http.RequestParams;
import com.jiefutong.lehfu.utils.JsonUtil;
import com.jiefutong.lehfu.utils.ToastUtils;
import com.jiefutong.lehfu.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：hj
 * time: 2018/1/17 0017 23:18
 * description:
 */


public class MyCardPackageActivity extends BaseTitleActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    private ChuXuCardFragment chuXuCardFragment;
    private CreditCardFragment creditCardFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycardpackage);
        ButterKnife.bind(this);
        setTitle("我的卡包");

        initTabLayout();

        getCardList();
    }

    private void initTabLayout() {
        viewpager.setNoScroll(true);
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        chuXuCardFragment = ChuXuCardFragment.newInstance();
        fragmentList.add(chuXuCardFragment);
        titleList.add("储蓄卡");
        creditCardFragment = CreditCardFragment.newInstance();
        fragmentList.add(creditCardFragment);
        titleList.add("信用卡");

        FragmentPagerAdapter adapter = new CardPackagePagerAdapter(getSupportFragmentManager()
                , fragmentList, titleList);
        viewpager.setAdapter(adapter);

        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 获取卡片列表数据
     */
    private void getCardList() {
        RequestParams requestParams = new RequestParams();
        Http.get(Http.GET_CARD_LIST, requestParams,
                new MyTextAsyncResponseHandler(act, "请求中...") {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        CardListResultBean resultBean = JsonUtil.fromJson(content,
                                CardListResultBean.class);
                        if (resultBean.getStatus() == 1) {
                            refreshData(resultBean);
                        } else {
                            ToastUtils.showCenterShortToast("获取数据失败");
                        }
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        ToastUtils.showCenterShortToast("请求失败");
                    }
                });

    }

    private void refreshData(CardListResultBean resultBean) {
        if (resultBean != null && resultBean.getInfo() != null) {
            CardListResultBean.InfoEntity info = resultBean.getInfo();

            if (info.getBlist() != null && info.getBlist().size() > 0 ) {
                chuXuCardFragment.refreshData(info.getBlist());
            }

            if (info.getClist() != null && info.getClist().size() > 0 ) {
                creditCardFragment.refreshData(info.getClist());
            }
        }
    }
}
