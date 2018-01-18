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
import com.jiefutong.lehfu.fragment.ChuXuCardFragment;
import com.jiefutong.lehfu.fragment.CreditCardFragment;
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
}
