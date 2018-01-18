package com.jiefutong.lehfu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author：hj
 * time: 2017/12/7 0007 09:58
 */
public class CardPackagePagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList;
    private final List<String> titleList;

    public CardPackagePagerAdapter(FragmentManager fm, List<Fragment> fragmentList,
                                   List<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
