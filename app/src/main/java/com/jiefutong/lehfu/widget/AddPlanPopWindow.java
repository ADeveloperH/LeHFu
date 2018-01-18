package com.jiefutong.lehfu.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.widget.wheelview.adapter.ArrayWheelAdapter;
import com.jiefutong.lehfu.widget.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.List;


/**
 * author：hj
 * time: 2017/7/13 0013 16:53
 */


public class AddPlanPopWindow implements View.OnClickListener {
    private List<String> dateList;
    private Context context;
    private PopupWindowProxy mPopupWindow;
    private WheelView wheelView;

    public AddPlanPopWindow(Context context) {
        this.context = context;
        dateList = new ArrayList<>(31);
        for (int i = 1; i < 31; i++) {
            dateList.add("每月" + i + "日");
        }
        initPopWindow(context);
    }

    private void initPopWindow(Context mContext) {
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_addplan, null);
        mPopupWindow = new PopupWindowProxy(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setAnimationStyle(R.style.bottom_menu_animation);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(false);

        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 19;
        style.textColor = Color.parseColor("#99333333");
        style.selectedTextColor = Color.parseColor("#333333");
        style.textSize = 15;

        wheelView = (WheelView) popupView.findViewById(R.id.wheelview);
        wheelView.setWheelSize(5);
        wheelView.setWheelAdapter(new ArrayWheelAdapter<String>(mContext));
        wheelView.setSkin(WheelView.Skin.Holo);
        wheelView.setWheelData(dateList);
        wheelView.setStyle(style);

        popupView.findViewById(R.id.btn_cancel).setOnClickListener(this);
        popupView.findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    public void show(View view) {
        mPopupWindow.setBackgroundDimAmount(0.5f);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void setCurPosition(int position) {
        if (wheelView != null) {
            int curPosition = wheelView.getCurrentPosition();
            if (curPosition != position) {
                wheelView.setSelection(position);
            }
        }
    }

    public void setOnOkClickListener(OnOkClickListener onOkClickListener) {
        this.onOkClickListener = onOkClickListener;
    }

    private OnOkClickListener onOkClickListener;

    public interface OnOkClickListener {
        void okClickLister(int selectPositoin, String selectStr);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel://取消
                dismiss();
                break;
            case R.id.btn_ok://确定
                if (wheelView != null) {
                    int position = wheelView.getCurrentPosition();
                    String selectStr = null;
                    if (position >= 0 && position < dateList.size()) {
                        selectStr = dateList.get(position);
                    }
                    if (onOkClickListener != null) {
                        onOkClickListener.okClickLister(position, selectStr);
                    }
                    dismiss();
                }
                break;
        }
    }
}
