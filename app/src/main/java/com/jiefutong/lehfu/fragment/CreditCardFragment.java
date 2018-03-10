package com.jiefutong.lehfu.fragment;

import com.jiefutong.lehfu.base.BaseFragment;
import com.jiefutong.lehfu.bean.CardListResultBean;

import java.util.List;

/**
 * author：hj
 * time: 2018/1/18 0018 14:36
 * description:
 */


public class CreditCardFragment extends BaseFragment {

    public static CreditCardFragment newInstance() {
        CreditCardFragment fragment = new CreditCardFragment();
        return fragment;
    }



    public void refreshData(List<CardListResultBean.InfoEntity.ClistEntity> clist) {

    }
}
