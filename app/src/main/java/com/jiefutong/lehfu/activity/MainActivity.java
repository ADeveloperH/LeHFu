package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.adapter.FragmentAdapter;
import com.jiefutong.lehfu.base.BaseNotTitleActivity;
import com.jiefutong.lehfu.fragment.BillFragment;
import com.jiefutong.lehfu.fragment.CustomerFragment;
import com.jiefutong.lehfu.fragment.HomeFragment;
import com.jiefutong.lehfu.fragment.RepayPlanFragment;
import com.jiefutong.lehfu.widget.MainTabHost;
import com.jiefutong.lehfu.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseNotTitleActivity {

    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R.id.maintab)
    MainTabHost mainTabHost;

    private List<String> tabNameList = new ArrayList<>();
    private List<Integer> tabImageList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | localLayoutParams.flags);

        initData();

        setListener();
    }

    private void initData() {
        tabNameList.add("首页");
        tabImageList.add(R.drawable.selector_main_tab_home);
        mFragmentList.add(new HomeFragment());

        tabNameList.add("还款计划");
        tabImageList.add(R.drawable.selector_main_tab_home);
        mFragmentList.add(new RepayPlanFragment());

        //使用原生视频直播
        tabNameList.add("我的账单");
        tabImageList.add(R.drawable.selector_main_tab_home);
        mFragmentList.add(new BillFragment());

        tabNameList.add("我的");
        tabImageList.add(R.drawable.selector_main_tab_home);
        mFragmentList.add(new CustomerFragment());

        mainTabHost.initTabs(tabNameList, tabImageList);
        mFragmentAdapter = new FragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        viewpager.setAdapter(mFragmentAdapter);
        viewpager.setOffscreenPageLimit(2);
        viewpager.setCurrentItem(0, false);
        mainTabHost.setCurSelect(0);
    }


    private void setListener() {
        mainTabHost.setupViewPager(viewpager);
        mainTabHost.setOnClickItemListener(new MainTabHost.OnClickItemListener() {
            @Override
            public void onItemClick(int position) {
                viewpager.setCurrentItem(position, false);
            }
        });

    }
}
